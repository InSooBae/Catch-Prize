import axios from "axios";
import { API_BASE_URL } from "../../constants";
import { fetchRooms, fetchRoomById, createRoom, deleteRoom } from "../../util/api";

const room = {
  state: {
    roomsList: [],
    room: {},
    ovToken: {},
    gameinfo: {roomName: 'newroom', roomType:'HOBULHO', maxParticipants: 6},
    isWait: false,
  },
  mutations: {
    SET_ROOMS: (state, roomsList) => state.roomsList = roomsList,
    SET_ROOM: (state, room) => state.room = room,
    SET_OVTOKEN: (state, ovtoken) => state.ovtoken = ovtoken,
    SET_GAMEINFO: (state, gameinfo) => state.gameinfo = gameinfo,
    SET_ISWAIT: (state, isWait) => state.isWait = isWait,
  },  
  getters: {
    roomsList: state => state.roomsList,
    gameinfo: state => state.gameinfo,
    room: state => state.room,
    ovtoken: state => state.ovtoken,
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
        
        commit('SET_OVTOKEN', res.data)
  
      })
    },
    createRoom({ commit, getters }, gameinfo) {
      createRoom(getters.authHeader, gameinfo)
        .then(res => {
          commit('SET_ROOM', res)
          // router.push({
          //   name: 'gameroom',
          //   params: { roomId: getters.room.roomId }
          // })
        })
    },
    deleteRoom({ commit, getters }, roomId) {
      deleteRoom(getters.authHeader, roomId)
      .then(() => {
        commit('SET_GAMEINFO', {})
        router.push({ name: 'lobby' })
      })

    }

  }
};

export default room;