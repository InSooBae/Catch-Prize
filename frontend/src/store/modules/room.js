import axios from "axios";
import { API_BASE_URL } from "../../constants";
import { fetchRooms, fetchRoomById, createRoom, deleteRoom } from "../../util/api";

const room = {
  state: {
    roomsList: [],
    room: {},
    gameinfo: {},
    isWait: false,
  },
  mutations: {
    SET_ROOMS: (state, roomsList) => state.roomsList = roomsList,
    SET_ROOM: (state, room) => state.room = room,
    SET_GAMEINFO: (state, gameinfo) => state.gameinfo = gameinfo,
    SET_ISWAIT: (state, isWait) => state.isWait = isWait,
  },  
  getters: {
    roomsList: state => state.roomsList,
    gameinfo: state => state.gameinfo,
    room: state => state.room,
    isWait: state => state.isWait
  },
  actions: {
    fetchRooms({ commit, getters }) {
      fetchRooms(getters.authHeader)
      .then(res => {
        console.log(res.data)
        commit('SET_ROOMS', res.data)
      })
      // commit('SET_ROOMS', [
      //   { name: 'GameRoom1ddddddddddddddddd', isPlaying: false, type: 'Poker', maxParti: 6, nowParti: 4},
      //   { name: 'GameRoom2', isPlaying: true, type: 'Soccer', maxParti: 6, nowParti: 6},
      //   { name: 'GameRoom3', isPlaying: false, type: 'Game', maxParti: 6, nowParti: 2},
      //   { name: 'GameRoom4', isPlaying: true, type: 'Poker', maxParti: 6, nowParti: 6},
      //   { name: 'GameRoom5', isPlaying: false, type: 'Poker', maxParti: 6, nowParti: 4},
      //   { name: 'GameRoom6', isPlaying: true, type: 'Poker', maxParti: 6, nowParti: 6},
      //   { name: 'GameRoom7', isPlaying: true, type: 'Poker', maxParti: 6, nowParti: 6},
      //   { name: 'GameRoom8', isPlaying: false, type: 'Poker', maxParti: 6, nowParti: 3},
      // ])
    },
    fetchRoom({ commit, getters }, roomId) {
      fetchRoomById(getters.authHeader, roomId)
      .then(res => {
        commit('SET_ROOM', res.data)
      })
    },
    createRoom({ commit, getters }, gameinfo) {
      createRoom(getters.authHeader, gameinfo)
        .then(res => {
          commit('SET_ROOM', res.data)
          router.push({
            name: 'gameroom',
            params: { roomId: getters.room }
          })
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