<template>
  <div class="players-container">
    <div class="left-grid">
      <div class="left-grid1"><Player :player="playersList[0]" /></div>
      <div class="left-grid2"><Player :player="playersList[1]"/></div>
      <div class="left-grid3"><Player :player="playersList[2]"/></div>
    </div>
    <div class="right-grid">
      <div class="right-grid1"><Player :player="playersList[3]"/></div>
      <div class="right-grid2"><Player :player="playersList[4]"/></div>
      <div class="right-grid3"><Player :player="playersList[5]"/></div>
    </div>
  </div>
</template>

<script setup>
import Player from "./Player.vue";

import { reactive, toRefs } from 'vue'


// 받아올 데이터
const players = reactive({
  playersList: [
    { name: 'player1', isDeath: false, fieldlist: [0,2,1,2,1,1], remain: 1},
    { name: 'player2', isDeath: true, fieldlist: [0,2,1,2,1,0], remain: 3},
    { name: 'player3', isDeath: false, fieldlist: [0,2,1,2,1,2], remain: 2},
    { name: 'player4', isDeath: true, fieldlist: [0,2,1,2,0,1], remain: 4},
    { name: 'player5', isDeath: false, fieldlist: [0,2,1,2,2,1], remain: 5},
    { name: 'player6', isDeath: true, fieldlist: [0,2,1,1,1,1], remain: 2}
  ],
})

const { playersList } = toRefs(players)
</script>

<style>
.players-container {
  /* border: 1px solid white; */
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
  height: 87%;
}
.left-grid {
  width: 50%;
  height: 100%;
  padding-right: 10px;
}
.right-grid {
  width: 50%;
  height: 100%;
  padding-right: 10px;
}
.left-grid1 {
  height: 27vh;
  margin-bottom: 5px;
}
.left-grid2 {
  height: 27vh;
  margin-bottom: 5px;
}
.left-grid3 {
  height: 27vh;
}
.right-grid1 {
  height: 27vh;
  margin-bottom: 5px;
}
.right-grid2 {
  height: 27vh;
  margin-bottom: 5px;
}
.right-grid3 {
  height: 27vh;
}
</style>
