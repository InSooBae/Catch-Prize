<template>
  <div class="game-status-home">
    <div
      v-for="(guide, i) in statuseList.filter(
        (c) => c.name === $state.gamestate
      )"
      :key="i"
      class="guide"
    >
      <Guide :guide="guide" />
    </div>
    <div class="timer">
      <Timer />
    </div>
  </div>
</template>

<script setup>
import Guide from "./GameGuide.vue";
import Timer from "./Timer.vue";
import VTypical from "vue-typical";

import { reactive, toRefs, inject } from "vue";

const $clientstate = inject("$clientstate");
const $hobulhoSocket = inject("$hobulhoSocket");
const $attackstate = inject("$attackstate");
const $state = inject("$state");

const guides = reactive({
  statuseList: [
    { name: "loading", guideText: "딜러가 카드를 섞는 중..." },
    {
      name: "start",
      guideText: "게임이 시작 되었습니다! 첫번째 사람부터 시작합니다.",
    },
    { name: "select", guideText: "공격할 카드를 고르세요!" },
    { name: "attack", guideText: "공격할 사람의 이름을 클릭하세요!" },
    { name: "declare", guideText: "어떤 카드라고 하시겠습니까?" },
    { name: "judge", guideText: "진실을 파헤치세요!" },
    { name: "success", guideText: "정답입니다!" },
    { name: "fail", guideText: "틀렸습니다!" },
    { name: "death", guideText: `${$state.deathplayer} 님이 죽었습니다...` },
    { name: "win", guideText: `${$state.winner} 님의 승리입니다!` },
    { name: "turn", guideText: `${$attackstate.attackerId} 님의 차례입니다.` },
    {
      name: "judge-turn",
      guideText: `${$attackstate.defenderId} 님이 진실을 파헤치는중..`,
    },
  ],
});
const { statuseList } = toRefs(guides);
</script>

<style scoped>
.game-status-home {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: row;
  padding-left: 20px;
  padding-right: 20px;
  /* border: 1px solid white; */
  background-color: #1b2130;
  border-radius: 20px;
  height: 100%;
}

.timer {
  width: 20%;
  position: absolute;
  right: 10%;
}
.guide {
  width: 60%;
  position: absolute;
  left: 10%;
}
</style>
