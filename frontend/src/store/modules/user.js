import axios from "axios";
import { API_BASE_URL } from "../../constants";

const user = {
  state: {
    token: sessionStorage.getItem('token') || '' ,
    currentUser: {},
    friendsList: [],
    friendsListPagenation: [],
    authError: null,
  },
  mutations: {
    SET_TOKEN: (state, token) => state.token = token,
    SET_CURRENT_USER: (state, user) => state.currentUser = user,
    SET_FRIENDS: (state, friendsList) => state.friendsList += friendsList,
    SET_AUTH_ERROR: (state, error) => state.authError = error
  },
  getters: {
    isLoggedIn: state => !!state.token,
    currentUser: state => state.currentUser,
    friendsList: state => state.friendsList,
    authError: state => state.authError,
    authHeader: state => ({ Authorization: `Bearer ${state.token}`})
  },
  actions: {
    saveToken({ commit }, token) {
      commit('SET_TOKEN', token)
      localStorage.setItem('token', '')
      sessionStorage.setItem('token', token)
    },
    removeToken({ commit }) {
      commit('SET_TOKEN', '')
      sessionStorage.setItem('token', '')
    },
    fetchCurrentUser({ commit, getters, dispatch }) {
      if (getters.isLoggedIn) {
        axios.get(API_BASE_URL + '/user/me', {headers: getters.authHeader})
          .then(res => {
            commit('SET_CURRENT_USER', res.data)
        })
          .catch(err => {
            if (err.response.status === 401) {
              dispatch('removeToken')
              router.push({ name: 'home' })
            }
          })
      }
    },
    fetchFriendsList({ commit, state }) {
      console.log(state.friendsList)
      commit('SET_FRIENDS', [
        { profile: '100', name: '황태희' },
        { profile: '200', name: '김태희' },
        { profile: '300', name: '이태희' },
        { profile: '123', name: '박태희' },
        { profile: '124', name: '고태희' },
        { profile: '125', name: '스탑태희' },
        { profile: '126', name: '돈스탑태희' },
      ])
    }
  },

};

export default user;