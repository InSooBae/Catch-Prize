<template>
  <transition name="slide-fade">
    <div
      v-if="
        $state.gamestate === 'declare' &&
        $attackstate.attackerId === $clientstate.myid
      "
    >
      <AttackCardDeclare />
    </div>
    <div v-else class="players-container">
      <div class="left-grid">
        <div class="left-grid1"><Player :player="playersList[0]" /></div>
        <div class="left-grid2">
          <Player :player="playersList[1]" />
        </div>
        <div class="left-grid3"><Player :player="playersList[2]" /></div>
      </div>
      <div class="right-grid">
        <div class="right-grid1"><Player :player="playersList[3]" /></div>
        <div class="right-grid2"><Player :player="playersList[4]" /></div>
        <div class="right-grid3"><Player :player="playersList[5]" /></div>
      </div>
    </div>
  </transition>
  <DefendJudge
    v-if="
      $state.gamestate === 'judge' &&
      $attackstate.defenderId === $clientstate.myid
    "
  />
</template>

<script setup>
import Player from "./Player.vue";
import AttackCardDeclare from "../select/AttackCardDeclare.vue";
import DefendJudge from "../select/DefendJudge.vue";

import { reactive, toRefs, inject, ref } from "vue";

const $clientstate = inject("$clientstate");
const $hobulhoSocket = inject("$hobulhoSocket");
const $state = inject("$state");
const $attackstate = inject("$attackstate");
const nowstate = $state.gamestate;

// 받아올 데이터
const players = reactive({
  playersList: [
    {
      name: "player1",
      isAlive: false,
      fieldlist: [0, 0, 0, 0, 0, 0],
      remain: 0,
    },
    {
      name: "player2",
      isAlive: false,
      fieldlist: [0, 0, 0, 0, 0, 0],
      remain: 0,
    },
    {
      name: "player3",
      isAlive: false,
      fieldlist: [0, 0, 0, 0, 0, 0],
      remain: 0,
    },
    {
      name: "player4",
      isAlive: false,
      fieldlist: [0, 0, 0, 0, 0, 0],
      remain: 0,
    },
    {
      name: "player5",
      isAlive: false,
      fieldlist: [0, 0, 0, 0, 0, 0],
      remain: 0,
    },
    {
      name: "player6",
      isAlive: false,
      fieldlist: [0, 0, 0, 0, 0, 0],
      remain: 0,
    },
  ],
});
//위의 players를 세팅하는 함수
function playersSetting() {
  for (let t = 0; t < 6; t++) {
    players.playersList[t].name = $state.players[t].playerId;
    players.playersList[t].isAlive = $state.players[t].isAlive;
    players.playersList[t].remain = $state.players[t].cards.remain;
    players.playersList[t].fieldlist[0] = $state.players[t].cards.board.cake;
    players.playersList[t].fieldlist[1] = $state.players[t].cards.board.durian;
    players.playersList[t].fieldlist[2] =
      $state.players[t].cards.board.eggplant;
    players.playersList[t].fieldlist[3] = $state.players[t].cards.board.insect;
    players.playersList[t].fieldlist[4] = $state.players[t].cards.board.mint;
    players.playersList[t].fieldlist[5] = $state.players[t].cards.board.pizza;
  }
}
const { playersList } = toRefs(players);

//처음 게임이 시작하고 첫 공격자 함수
function firstattacker() {
  if ($state.gamestate === "start") {
    setTimeout(() => {
      //카드 선택 준비
      $hobulhoSocket.emit("select-ready-req", $clientstate.myid);
    }, 3000);
  }
}

//첫공격
$hobulhoSocket.on("first-attack", function () {
  playersSetting();
  firstattacker();
});
//새로고침
$hobulhoSocket.on("players-refresh", function () {
  playersSetting();
});
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
.slide-fade-enter-active {
  transition: all 0.8s ease;
}

.slide-fade-enter-from {
  transform: translateX(100px);
  opacity: 0;
}
</style>
