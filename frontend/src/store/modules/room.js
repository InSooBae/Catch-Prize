import router from '../../router';
import { fetchRooms, createRoom, removeUser } from "../../util/api";

const room = {
  state: {
    rooms: [],
    room: {},
    ov: {},
    sessionId: sessionStorage.getItem('sessionId') || '',
    gameinfo: {roomName: 'newroom', roomType:'HOBULHO', maxParticipants: 6},
    isWait: false,
  },

  getters: {
    rooms: state => state.rooms,
    room: state => state.room,
    ov: state => state.ov,
    sessionId: state => state.sessionId,
    gameinfo: state => state.gameinfo,
    isWait: state => state.isWait
  },

  mutations: {
    SET_ROOMS: (state, rooms) => state.rooms = rooms,
    SET_ROOM: (state, room) => state.room = room,
    SET_OV: (state, ov) => state.ov = ov,
    SET_SESSIONID: (state, sessionId) => state.sessionId = sessionId,
    SET_GAMEINFO: (state, gameinfo) => state.gameinfo = gameinfo,
    SET_ISWAIT: (state, isWait) => state.isWait = isWait,
  },  

  actions: {
    fetchRooms({ commit, getters }) {
      fetchRooms(getters.authHeader)
      .then(res => {
        commit('SET_ROOMS', res.data)
      })
    },

    createRoom({ commit, getters }, gameinfo) {
      createRoom(getters.authHeader, gameinfo)
        .then(res => {
          console.log(res)
          commit('SET_ROOM', res.data)
          sessionStorage.setItem('roomId', res.data.roomId)
          sessionStorage.setItem('sessionId', 'ses_' + res.data.roomId)
          router.push({
            name: 'gameroom',
            params: { roomId: res.data.roomId }
          })
        })
    },

    removeUser({ getters }, {roomId, ovdata}) {
      console.log(roomId, ovdata)
      console.log('22')
      removeUser(getters.authHeader, roomId, ovdata)
      .then(res => {
        sessionStorage.setItem('ovdata','')
        sessionStorage.setItem('roomId','')
        sessionStorage.setItem('sessionId','')
      })
    }

  }
};

export default room;