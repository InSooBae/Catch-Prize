<template>
  <div class="game-status-home">
    <div class="guide">
      <Guide />
    </div>
    <div class="timer">
      <Timer />
    </div>
  </div>
</template>

<script>
import Guide from "./GameGuide.vue";
import Timer from "./Timer.vue";
export default {
  name: "game-staus-Home",
  components: {
    Guide,
    Timer,
  },
};
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
