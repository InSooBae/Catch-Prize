<template>
  <div class="liar-hobulho-container">
    <header class="liar-header-container">
      <HeaderHome />
    </header>
    <body class="liar-main-container">
      <MainHome />
    </body>
  </div>
</template>

<script setup>
import HeaderHome from "./header/HeaderHome.vue";
import MainHome from "./main/MainHome.vue";
import { reactive, toRefs, inject } from "vue";
import { useRoute } from "vue-router";

// const store = useStore();
const $clientstate = inject("$clientstate");
const $hobulhoSocket = inject("$hobulhoSocket");
const $dataBox = inject("$dataBox");

const route = useRoute();
const myid = route.params.myid;
const roomid = route.params.roomid;
$clientstate.myid = myid;
$clientstate.roomid = roomid;
</script>

<style scoped>
.liar-hobulho-container {
  margin-left: 20px;
  margin-right: 20px;
  position: relative;
  display: flex;
  flex-direction: column;
}
.liar-header-container {
  height: 100%;
  width: 100%;
  /* margin-bottom: 20px; */
}
.liar-main-container {
  /* border: 1px solid red; */
  height: calc(100vh - 100px);
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
}
</style>
