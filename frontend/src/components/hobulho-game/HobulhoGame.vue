<template>
  <div >
    <el-container class="el-header-container">
      <el-header height="100px"><HeaderHome/></el-header>
    </el-container>
      <el-container class="el-body-container">
        <el-aside class="game-aside-container" width="470px"><LeftHome/></el-aside>
        <el-main class="game-main-container" ><RightHome/></el-main>
    </el-container>
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
<style scoped>

.el-header-container {
  height: 100px;
}
.el-body-container {
  height: 90%;
}
.el-main {
  padding: 0px;
}
.el-aside {
  padding: 0px;
  margin-left: 20px;
}
.el-header {
  height: 100px;
  padding-top: 20px;
  padding-bottom: 1%;
}
</style>

