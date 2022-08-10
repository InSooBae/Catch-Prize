import router from '../../router';
import { fetchRooms, fetchRoomById, createRoom, deleteRoom } from "../../util/api";

const room = {
  state: {
    room: [],
    room: {},
    ovToken: sessionStorage.getItem('ovtoken') || '',
    userId: sessionStorage.getItem('userId') || '',
    // session: sessionStorage.getItem('session') || '',
    gameinfo: {roomName: 'newroom', roomType:'HOBULHO', maxParticipants: 6},
    isWait: false,
  },
  mutations: {
    SET_ROOMS: (state, rooms) => state.rooms = rooms,
    SET_ROOM: (state, room) => state.room = room,
    SET_OVTOKEN: (state, ovtoken) => state.ovtoken = ovtoken,
    SET_GAMEINFO: (state, gameinfo) => state.gameinfo = gameinfo,
    SET_ISWAIT: (state, isWait) => state.isWait = isWait,
  },  
  getters: {
    rooms: state => state.rooms,
    gameinfo: state => state.gameinfo,
    room: state => state.room,
    isWait: state => state.isWait
  },
  actions: {

    fetchRooms({ commit, getters }) {
      fetchRooms(getters.authHeader)
      .then(res => {
        commit('SET_ROOMS', res.data)
      })
    },

    fetchRoom({ commit, getters }, roomId) {
      console.log('gettoken!')
      fetchRoomById(getters.authHeader, roomId)
      .then(res => {
        console.log(res.data)
        commit('SET_OVTOKEN', res.data)
        sessionStorage.setItem('ovtoken', res.data.token)
        sessionStorage.setItem('userId', res.data.userId)
      })
    },

    createRoom({ commit, getters, dispatch }, gameinfo) {
      createRoom(getters.authHeader, gameinfo)
        .then(res => {
          console.log(res.data)
          commit('SET_ROOM', res.data)
          sessionStorage.setItem('roomId', res.data.roomId)
          sessionStorage.setItem('session', res.data.session)
          dispatch('fetchRoom', res.data.roomId)
          router.push({
            name: 'gameroom',
            params: { roomId: res.data.roomId }
          })
        })
    },

    deleteRoom({ commit, getters }, roomId) {
      deleteRoom(getters.authHeader, roomId)
      .then(() => {
        commit('SET_GAMEINFO', {})
        router.push({ name: 'lobby' })
      })
    },

  }
};

export default room;