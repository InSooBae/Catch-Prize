<template>
  <div class="game-guide">
    <v-typical
      v-if="$state.gamestate === 'turn'"
      class="blink"
      :steps="[$attackstate.attackerId + ' 님의 차례입니다.', 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-if="$state.gamestate === 'judge-turn'"
      class="blink"
      :steps="[$attackstate.defenderId + ' 님이 진실을 파헤치는중..', 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$state.gamestate === 'select'"
      class="blink"
      :steps="[guide.guideText, 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$state.gamestate === 'start'"
      class="blink"
      :steps="[guide.guideText, 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$state.gamestate === 'success'"
      class="blink"
      :steps="[guide.guideText, 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$state.gamestate === 'fail'"
      class="blink"
      :steps="[guide.guideText, 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$state.gamestate === 'declare'"
      class="blink"
      :steps="[guide.guideText, 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$state.gamestate === 'judge'"
      class="blink"
      :steps="[guide.guideText, 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="
        $state.gamestate === 'attack' &&
        $attackstate.attackerId === $clientstate.myid
      "
      class="blink"
      :steps="[guide.guideText, 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$state.gamestate === 'death'"
      class="blink"
      :steps="[$state.deathplayer + ' 님이 죽었습니다...', 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$state.gamestate === 'win'"
      class="blink"
      :steps="[$state.winner + ' 님의 승리입니다!', 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
    <v-typical
      v-else-if="$state.gamestate === 'loading'"
      class="blink"
      :steps="[guide.guideText, 1000]"
      :loop="1"
      :wrapper="'h1'"
    ></v-typical>
  </div>
</template>

<script setup>
import VTypical from "vue-typical";
import { reactive, toRefs, inject } from "vue";
const $clientstate = inject("$clientstate");
const $state = inject("$state");
const $attackstate = inject("$attackstate");
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
