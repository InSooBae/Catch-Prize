<template>
  <div class="game-status-home">
    <div v-for="(guide, i) in statuseList.filter(
        (c) => c.name === 'attack'
      )"
      :key="i" class="guide">
      <Guide :guide="guide"/>
    </div>
    <div class="timer">
      <Timer />
    </div>
  </div>
</template>

<script setup>
import Guide from "./GameGuide.vue";
import Timer from "./Timer.vue";

import { reactive, toRefs } from "vue";
// export default {
//   name: "game-staus-Home",
//   components: {
//     Guide,
//     Timer,
//   },
// };

const guides = reactive({
  statuseList: [
    { name: "select", guideText:"공격할 카드를 고르세요!"},
    { name: "attack", guideText:"공격할 사람을 선택하세요!"},
    { name: "declare", guideText:"어떤 카드라고 하시겠습니까?"},
    { name: "judge", guideText:"진실을 파헤치세요!"},
    { name: "success", guideText:"정답입니다!"},
    { name: "fail", guideText:"틀렸습니다!"},
    { name: "death", guideText:"죽었습니다.."},
  ]
})

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
  background-color: #1B2130;
  border-radius: 20px;
  height: 100%;
}

.timer{
  width: 20%;
  position: absolute;
  right: 10%;
}
.guide{
  width: 60%;
  position: absolute;
  left: 10%;
}
</style>
