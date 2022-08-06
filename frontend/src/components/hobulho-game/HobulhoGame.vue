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
import HeaderHome from "./header/HeaderHome.vue";
import LeftHome from "./main/LeftHome.vue";
import RightHome from "./main/PlayersHome.vue";
import { useStore } from "vuex";

// const store = useStore();
const $clientstate = inject("$clientstate");
const $hobulhoSocket = inject("$hobulhoSocket");
const $state = inject("$state");

const nowstate = $state.gamestate;

//현재상태가 loading 일때 게임 시작 요청
const gameStart = () => {
  if (nowstate === "loading") {
    setTimeout(() => {
      $hobulhoSocket.emit("hobulho-start-req", $clientstate.myid);
    }, 3000);
  }
};
gameStart();
console.log($state);
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
