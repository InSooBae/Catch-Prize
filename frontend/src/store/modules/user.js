const user = {
  state: {
    token: sessionStorage.getItem('token') || '' ,
    currentUser: {},
    profile: {},
    authError: null,
  },
  mutations: {
    SET_TOKEN: (state, token) => state.token = token,
    SET_CURRENT_USER: (state, user) => state.currentUser = user,
    SET_PROFILE: (state, profile) => state.profile = profile,
    SET_AUTH_ERROR: (state, error) => state.authError = error
  },
  getters: {
    isLoggedIn: state => !!state.token,
    currentUser: state => state.currentUser,
    profile: state => state.profile,
    authError: state => state.authError,
    authHeader: state => ({ Authorization: `Bearer ${state.token}`})
  },
  actions: {
    saveToken({ commit }, token) {s
      commit('SET_TOKEN', token)
      localStorage.setItem('token', '')
      sessionStorage.setItem('token', token)
    },
    removeToken({ commit }) {
      commit('SET_TOKEN', '')
      sessionStorage.setItem('token', '')
    },
  }
};

export default user;