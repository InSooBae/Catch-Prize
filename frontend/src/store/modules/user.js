import axios from "axios";
import { EventSourcePolyfill } from 'event-source-polyfill';
import { API_BASE_URL } from "../../constants";
import jwt_decode from "jwt-decode";
import router from "../../router";

const user = {
  state: {
    token: sessionStorage.getItem('token') || '',
    currentUser: {},
    friendsList: { 'pending': {}, 'online': {}, 'offline': {} },
    isAdmin: false,
    authError: null,
  },
  mutations: {
    SET_TOKEN: (state, token) => state.token = token,
    SET_CURRENT_USER: (state, user) => state.currentUser = user,
    SET_FRIENDS: (state, friendsList) => state.friendsList = friendsList,
    SET_AUTH_ERROR: (state, error) => state.authError = error,
    SET_IS_ADMIN: (state, role) => state.isAdmin = (role === 'ADMIN'),
  },
  getters: {
    token: state => state.token,
    isLoggedIn: state => !!state.token,
    currentUser: state => state.currentUser,
    friendsList: state => state.friendsList,
    authError: state => state.authError,
    authHeader: state => ({ Authorization: `Bearer ${state.token}` }),
    isAdmin: state => state.isAdmin,
  },
  actions: {
    saveToken({ commit }, token) {
      commit('SET_TOKEN', token)
      localStorage.setItem('token', '')
      sessionStorage.setItem('token', token)
    },
    logout({ commit, getters }) {
      router.push({ name: 'home' })
      if (getters.isLoggedIn) {
        axios({
          url: API_BASE_URL + '/auth/logout',
          method: 'post',
          data: {},
          headers: getters.authHeader,
        })
          .then(res => {
            commit('SET_TOKEN', '')
            sessionStorage.setItem('token', '')
          })
          .catch(err => {
            console.log(err.response.data)
          })
      }
    },
    fetchCurrentUser({ commit, getters, dispatch }) {
      if (getters.isLoggedIn) {
        axios({
          url: API_BASE_URL + '/user/me',
          method: 'get',
          headers: getters.authHeader,
        })
          .then(res => {
            commit('SET_CURRENT_USER', res.data)
            const role = jwt_decode(getters.token).role
            commit('SET_IS_ADMIN', role)
          })
          .catch(err => {
            if (err.response.status === 401) {
              dispatch('logout')
            }
          })
      }
    },
    fetchFriendsList({ commit, getters }) {
      if (getters.isLoggedIn) {
        axios({
          url: API_BASE_URL + '/friend',
          method: 'get',
          headers: getters.authHeader,
        })
          .then(res => {
            const friendsList = { 'pending': {}, 'online': {}, 'offline': {} }
            res.data.forEach(friend => {
              updateFriend(friend, friendsList)
            });
            commit('SET_FRIENDS', friendsList)
          })
          .catch(err => {
            console.log(err)
          })
      }
    },
    subscribeFriends({ commit, getters }, method) {
      console.log("변동 알림 보내기");
      const eventSource = new EventSourcePolyfill(`${API_BASE_URL}/friend/subscribe`, { headers: getters.authHeader });
      eventSource.addEventListener("sse", function (event) {
        console.log(event.data)
        if (event.data[0] === '{') {
          const friendsList = getters.friendsList;
          const friend = JSON.parse(event.data);
          delete friendsList.offline[friend.id]
          delete friendsList.online[friend.id]
          delete friendsList.pending[friend.id]
          updateFriend(friend, friendsList)
          commit('SET_FRIENDS', friendsList)
        }
      })
    },
    acceptFriend({ dispatch, getters }, friendNickname) {
      console.log(`acceptFriend ${friendNickname}`)
      axios({
        url: API_BASE_URL + `/friend/${friendNickname}`,
        method: 'put',
        headers: getters.authHeader,
      })
        .then(res => {
          dispatch('fetchFriendsList');
        })
        .catch(err => {
          console.log(err.response.data)
        })
    },
    addFriend({ dispatch, getters }, friendNickname) {
      console.log(`addFriend ${friendNickname}`)
      axios({
        url: API_BASE_URL + `/friend/${friendNickname}`,
        method: 'post',
        headers: getters.authHeader,
      })
        .then(res => {
          dispatch('fetchFriendsList');
        })
        .catch(err => {
          console.log(err.response.data)
        })
    },
    deleteFriend({ dispatch, getters }, friendNickname) {
      axios({
        url: API_BASE_URL + `/friend/${friendNickname}`,
        method: 'delete',
        headers: getters.authHeader,
      })
        .then(res => {
          dispatch('fetchFriendsList');
        })
        .catch(err => {
          console.log(err.response.data)
        })
    }
  },
};

const updateFriend = (friend, friendsList) => {
  if (friend.friend) {
    if (friend.online) {
      friendsList.online[friend.id] = {
        'friendNickname': friend.friendNickname
      }
    }
    else {
      friendsList.offline[friend.id] = {
        'friendNickname': friend.friendNickname
      }
    }
  }
  else if (friend.pending) {
    friendsList.pending[friend.id] = {
      'friendNickname': friend.friendNickname
    }
  }
}

export default user;