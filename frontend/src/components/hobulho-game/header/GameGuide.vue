<template>
  <div class="game-guide">
    <v-typical
      v-if="$hobulhostate.gamestate === 'turn'"
      class="blink"
      :steps="[$hobulhostate.attacker + '님의 차례입니다.', 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$hobulhostate.gamestate === 'select'"
      class="blink"
      :steps="[guide.guideText, 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$hobulhostate.gamestate === 'start'"
      class="blink"
      :steps="[guide.guideText, 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$hobulhostate.gamestate === 'success'"
      class="blink"
      :steps="[guide.guideText, 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$hobulhostate.gamestate === 'fail'"
      class="blink"
      :steps="[guide.guideText, 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$hobulhostate.gamestate === 'declare'"
      class="blink"
      :steps="[guide.guideText, 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$hobulhostate.gamestate === 'judge'"
      class="blink"
      :steps="[guide.guideText, 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$hobulhostate.gamestate === 'attack'"
      class="blink"
      :steps="[guide.guideText, 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$hobulhostate.gamestate === 'death'"
      class="blink"
      :steps="[$hobulhostate.deathplayer + '님이 죽었습니다...', 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$hobulhostate.gamestate === 'win'"
      class="blink"
      :steps="[$hobulhostate.winner + '님의 승리입니다!', 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
  </div>
</template>

<script setup>
import VTypical from "vue-typical";
// export default {
//   name: "game-guide",
// };
import { reactive, toRefs, inject } from "vue";
const $hobulhostate = inject("$hobulhostate");
const props = defineProps({
  guide: Object,
});
</script>

<style scoped>
.game-guide {
  color: white;
  font-family: "Galmuri9";
  font-size: 10px;
  width: 100%;
}

.blink::after {
  /* content: '|'; */
  animation: blink 1s infinite step-start;
}
@keyframes blink {
  50% {
    opacity: 0;
  }
}
</style>
