import router from '../../router';
import { fetchRooms, createRoom, removeUser, fetchRoomById } from "../../util/api";
import * as _ from "lodash"

const room = {
  state: {
    rooms: [],
    room: {},
    roomMessage: [],
    ov: {},
    sessionId: sessionStorage.getItem('sessionId') || '',
    gameinfo: {roomName: 'newroom', roomType:'HOBULHO', maxParticipants: 6},
    isWait: false,
  },

  getters: {
    rooms: state => state.rooms,
    room: state => state.room,
    roomMessage: state => state.roomMessage,
    ov: state => state.ov,
    sessionId: state => state.sessionId,
    gameinfo: state => state.gameinfo,
    isWait: state => state.isWait
  },

  mutations: {
    SET_ROOMS: (state, rooms) => state.rooms = rooms,
    SET_ROOM: (state, room) => state.room = room,
    SET_ROOM_MESSAGE: (state, roomMessage) => state.roomMessage,
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

    createRoom({ commit, getters, dispatch }, gameinfo) {
      createRoom(getters.authHeader, gameinfo)
        .then(res => {
          console.log(res)
          commit('SET_ROOM', res.data)
          dispatch('subscribeRoom')
          sessionStorage.setItem('roomId', res.data.roomId)
          sessionStorage.setItem('sessionId', 'ses_' + res.data.roomId)
          router.push({
            name: 'gameroom',
            params: { roomId: res.data.roomId }
          })
        })
    },

    enterRoom({ commit, getters, dispatch }, roomId) {
      fetchRoomById(getters.authHeader, roomId)
      .then(res => {
        console.log(res)
        commit('SET_ROOM', res.data)
        dispatch('subscribeRoom')
        sessionStorage.setItem('roomToken', res.data.token)
        router.push({
          name: 'gameroom',
          params: { roomId: roomId }
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
    },

    subscribeRoom({ commit, getters }, method) {
      console.log("방 변동 알림 보내기");
      let eventSource = {};
      if (_.isEmpty(getters.eventSource)) {
        eventSource = new EventSourcePolyfill(`${API_BASE_URL}/friend/subscribe`, { headers: getters.authHeader });
      } else {
        eventSource = getters.eventSource;
      }
      eventSource.addEventListener("sse-room", function (event) {
        console.log(event)
        // if (event.data[0] === '{') {

        // }
        // event.data.forEach(element => {

        // });
      })
      commit('SET_EVENT_SOURCE', eventSource)
    },
  }
};

export default room;