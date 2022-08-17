
const faceapi = {
  state: {
    filter: { type: '', options: {} },

  },
  mutation: {
    filter: state => state.filter,

  },
  getters: {
    SET_FILTER: (state, filter) => state.filter = filter,

  },
  actions: {

    

  }
}

export default faceapi