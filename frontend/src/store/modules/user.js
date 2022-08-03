import axios from "axios";
import { API_BASE_URL } from "../../constants";

const user = {
  state: {
    token: sessionStorage.getItem('token') || '' ,
    currentUser: {},
    friendsList: [],
    isAdmin: false,
    authError: null,
  },
  mutations: {
    SET_TOKEN: (state, token) => state.token = token,
    SET_CURRENT_USER: (state, user) => state.currentUser = user,
    SET_FRIENDS: (state, friendsList) => state.friendsList = friendsList,
    SET_AUTH_ERROR: (state, error) => state.authError = error
  },
  getters: {
    isLoggedIn: state => !!state.token,
    currentUser: state => state.currentUser,
    friendsList: state => state.friendsList,
    authError: state => state.authError,
    authHeader: state => ({ Authorization: `Bearer ${state.token}`}),
    isAdmin: state => state.isAdmin,
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
    fetchFriendsList({ commit }) {
      commit('SET_FRIENDS', [
        { id: 1, profile: '100', name: '황태희' },
        { id: 2, profile: '100', name: '황태희' },
        { id: 3, profile: '300', name: '이태희' },
        { id: 4, profile: '123', name: '박태희' },
        { id: 5, profile: '124', name: '고태희' },
        { id: 6, profile: '125', name: '스탑태희' },
        { id: 7, profile: '126', name: '돈스탑태희' },
        { id: 8, profile: '100', name: '황태희' },
        { id: 9, profile: '200', name: '김태희' },
        { id: 10, profile: '300', name: '이태희' },
      ])
    }
  },

};

export default user;