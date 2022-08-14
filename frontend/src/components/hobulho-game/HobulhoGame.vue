<template>
  <div class="hobulho-container">
    <header class="header-container">
      <HeaderHome />
    </header>
    <body class="main-container">
      <div class="left-home"><LeftHome /></div>
      <div class="right-home">
        <RightHome />
      </div>
    </body>
  </div>
</template>

<script setup>
import { reactive, toRefs, inject } from "vue";
import { useRoute } from "vue-router";
import HeaderHome from "./header/HeaderHome.vue";
import LeftHome from "./main/LeftHome.vue";
import RightHome from "./main/PlayersHome.vue";
import { useStore } from "vuex";
import { Data } from "phaser";

// const store = useStore();
const $clientstate = inject("$clientstate");
const $hobulhoSocket = inject("$hobulhoSocket");
const $dataBox = inject("$dataBox");

const route = useRoute();
const myid = route.params.myid;
const roomid = route.params.roomid;
$clientstate.myid = myid;
$clientstate.roomid = roomid;

$hobulhoSocket.emit("server-get-roomid", $clientstate.roomid);

function whichData(roomid) {
  for (let t = 0; t < $dataBox.length; t++) {
    if ($dataBox[t].controlstate.roomId === roomid) {
      return t;
    }
  }
}

//현재상태가 loading 일때 게임 시작 요청
const gameStart = () => {
  let boxnum = whichData($clientstate.roomid);
  if ($dataBox[boxnum].controlstate.gamestate === "loading") {
    setTimeout(() => {
      $hobulhoSocket.emit("hobulho-start-req", $clientstate.roomid);
    }, 3000);
  }
};
$hobulhoSocket.on("game-start-ready", function () {
  if ($clientstate.roomid != "") {
    gameStart();
  }
});

$hobulhoSocket.on("whose-turn", function () {
  let boxnum = whichData($clientstate.roomid);
  
  if ($dataBox[boxnum].attackstate.attackerId === $clientstate.myid) {
    $clientstate.gamestate = "select";
  } else {
    $clientstate.gamestate = "turn";
  }
});
$hobulhoSocket.on("whose-attack", function () {
  let boxnum = whichData($clientstate.roomid);

  if ($dataBox[boxnum].attackstate.attackerId === $clientstate.myid) {
    $clientstate.gamestate = "attack";
  } else {
    $clientstate.gamestate = "turn";
  }
});
$hobulhoSocket.on("whose-declare", function () {
  let boxnum = whichData($clientstate.roomid);

  if ($dataBox[boxnum].attackstate.attackerId === $clientstate.myid) {
    $clientstate.gamestate = "declare";
  } else {
    $clientstate.gamestate = "declare-turn";
  }
});
$hobulhoSocket.on("whose-judge", function () {
  let boxnum = whichData($clientstate.roomid);

  if ($dataBox[boxnum].attackstate.defenderId === $clientstate.myid) {
    $clientstate.gamestate = "judge";
  } else {
    $clientstate.gamestate = "judge-turn";
  }
});
</script>

<style>
.hobulho-container {
  margin-left: 20px;
  margin-right: 20px;
  position: relative;
  display: flex;
  flex-direction: column;
}
.header-container {
  height: 100%;
  width: 100%;
  margin-bottom: 20px;
}
.main-container {
  height: calc(100vh - 100px);
  display: flex;
  flex-direction: row;
}
.left-home {
  width: 30%;
  min-width: 350px;
  min-height: 600px;
}
.right-home {
  width: 70%;
  margin-left: 20px;
  min-height: 600px;
}
</style>
