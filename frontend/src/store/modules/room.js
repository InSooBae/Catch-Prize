import router from '../../router';
import { fetchRooms, createRoom, removeUser, fetchRoomById } from "../../util/api";
import * as _ from "lodash"
import jwt_decode from "jwt-decode";

const room = {
  state: {
    rooms: [],
    room: {},
    roomMessages: [],
    ov: {},
    sessionId: sessionStorage.getItem('sessionId') || '',
    gameinfo: {roomName: '', roomType:'HOBULHO', maxParticipants: 6},
    isWait: false,
    roomData: {},
  },

  getters: {
    rooms: state => state.rooms,
    room: state => state.room,
    roomMessages: state => state.roomMessages,
    ov: state => state.ov,
    sessionId: state => state.sessionId,
    gameinfo: state => state.gameinfo,
    isWait: state => state.isWait,
    roomData: state => state.roomData,
  },

  mutations: {
    SET_ROOMS: (state, rooms) => state.rooms = rooms,
    SET_ROOM: (state, room) => state.room = room,
    SET_ROOM_MESSAGES: (state, roomMessages) => state.roomMessages = roomMessages,
    SET_OV: (state, ov) => state.ov = ov,
    SET_SESSIONID: (state, sessionId) => state.sessionId = sessionId,
    SET_GAMEINFO: (state, gameinfo) => state.gameinfo = gameinfo,
    SET_ISWAIT: (state, isWait) => state.isWait = isWait,
    SET_ROOM_DATA: (state, roomData) => state.roomData = roomData
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
          console.log(res.data)
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
        sessionStorage.setItem('roomData', res.data)
        router.push({
          name: 'gameroom',
          params: { roomId: roomId }
        })
      })
    },

    removeUser({ getters }, data) {
      removeUser(getters.authHeader, data.roomId, data.ovdata)
      .then(res => {
        sessionStorage.setItem('roomData', '')
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
        if (event.data[0] === '{') {
          console.log(event.data)
          const data = JSON.parse(event.data);
          if (data.state == 'WAIT') {
            commit('SET_ROOM_MESSAGES', Object.keys(data.playerMap))

            const username = jwt_decode(getters.token).username
            let isHost = false

            if (getters.roomMessages.length >= 2){
              alert("게임 시작")
              if (username == data.hostName) {
                isHost = true
                router.push({
                  name: 'game',
                  params: { roomid: data.roomId },
                  query : { myid: username, isHost: isHost, users: getters.roomMessages }
                })
              } else {
                router.push({
                  name: 'game',
                  params: { roomid: data.roomId },
                  query : { myid: username, isHost: isHost }
                })
              }
              
            }
          } else {
            // const username = jwt_decode(getters.token).username
            // if (username == data.hostName) {
            //   if (getters.roomMessages.length >= 2){
            //     const $hobulhoSocket = inject("$hobulhosocket");
            //     const gameData = {
            //       roomid: data.roomId,
            //       users: getters.roomMessages
            //     }
            //     $hobulhoSocket.emit("start-data-set", gameData)
            //   }}
            //   router.push({
            //     name: 'gameplayroom',
            //     params: { roomid: data.roomId, myid: username }
            // })
          }
        }
      })
      commit('SET_EVENT_SOURCE', eventSource)
    },
  }
};

export default room;