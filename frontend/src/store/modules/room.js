import axios from "axios";
import { API_BASE_URL } from "../../constants";

const room = {
  state: {
    roomsList: []
  },
  mutations: {
    SET_ROOMS: (state, roomsList) => state.roomsList = roomsList,
  },  
  getters: {
    roomsList: state => state.roomsList,
  },
  actions: {
    fetchRooms({commit}) {
      commit('SET_ROOMS', [
        { name: 'GameRoom1', isPlaying: false, type: 'Poker', maxParti: 6, nowParti: 4},
        { name: 'GameRoom2', isPlaying: true, type: 'Soccer', maxParti: 6, nowParti: 6},
        { name: 'GameRoom3', isPlaying: false, type: 'Game', maxParti: 6, nowParti: 2},
        { name: 'GameRoom4', isPlaying: true, type: 'Poker', maxParti: 6, nowParti: 6},
        { name: 'GameRoom5', isPlaying: false, type: 'Poker', maxParti: 6, nowParti: 4},
        { name: 'GameRoom6', isPlaying: true, type: 'Poker', maxParti: 6, nowParti: 6},
        { name: 'GameRoom7', isPlaying: true, type: 'Poker', maxParti: 6, nowParti: 6},
        { name: 'GameRoom8', isPlaying: false, type: 'Poker', maxParti: 6, nowParti: 3},
      ])
    },
  }
};

export default room;