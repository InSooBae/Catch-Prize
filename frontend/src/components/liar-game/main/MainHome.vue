<template>
  <!-- <div class="liar-main">
    <div v-for="i in 4">
      <Liarplayer />
    </div>
  </div> -->
  <Topic />
</template>

<script setup>
import Liarplayer from "./liarplayer.vue";
import Topic from "./Topic.vue";
</script>

<style scoped>
.liar-main {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
  /* border: 1px solid white; */
  height: 83vh;
  width: 90%;
}
</style>
