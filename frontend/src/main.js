import { createApp, reactive } from "vue";
// import { useRoute } from "vue-router";
import App from "./App.vue";
import router from "./router";
import store from "./store";
// element UI import
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import "element-plus/theme-chalk/display.css";

import "./assets/main.css";

import io from "socket.io-client";
const hobulhoSocket = io("http://localhost:8081/hobulhoSocket", {
  transports: ["websocket"],
});
const app = createApp(App);
const clientstate = reactive({
  myid: "",
});
const attackstate = reactive({
  attackerId: "",
  defenderId: "",
  selectedcard: "",
  declaredcard: "",
  declaration: true,
});
const state = reactive({
  response: {
    success: "false",
    error: {
      code: "7",
      message: "More players are required",
    },
  },
  gamestate: "",
  deathplayer: "",
  winner: "",
  players: [
    {
      playerId: "player1",
      cards: {
        hand: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
        remain: 8,
        board: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
      },
      isAlive: false,
    },
    {
      playerId: "player2",
      cards: {
        hand: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
        remain: 8,
        board: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
      },
      isAlive: false,
    },
    {
      playerId: "player3",
      cards: {
        hand: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
        remain: 8,
        board: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
      },
      isAlive: false,
    },
    {
      playerId: "player4",
      cards: {
        hand: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
        remain: 8,
        board: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
      },
      isAlive: false,
    },
    {
      playerId: "player5",
      cards: {
        hand: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
        remain: 8,
        board: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
      },
      isAlive: false,
    },
    {
      playerId: "player6",
      cards: {
        hand: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
        remain: 8,
        board: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
      },
      isAlive: false,
    },
  ],
});
hobulhoSocket.on("data-refresh", function (data) {
  state.players = data.players;
  state.gamestate = data.gamestate;
  state.deathplayer = data.deathplayer;
  state.winner = data.winner;
});
hobulhoSocket.on("attackdata-refresh", function (data) {
  attackstate.attackerId = data.attackerId;
  attackstate.defenderId = data.defenderId;
  attackstate.selectedcard = data.selectedcard;
  attackstate.declaredcard = data.declaredcard;
  attackstate.declaration = data.declaration;
});

app.provide("$hobulhoSocket", hobulhoSocket);
app.provide("$clientstate", clientstate);
app.provide("$state", state);
app.provide("$attackstate", attackstate);
app.use(router);
app.use(ElementPlus);
app.use(store);
app.mount("#app");
