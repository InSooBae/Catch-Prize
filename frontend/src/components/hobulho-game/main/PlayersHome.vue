<template>
  <div class="players-container">
    <div class="left-grid">
      <div class="left-grid1"><Player /></div>
      <div class="left-grid2"><Player /></div>
      <div class="left-grid3"><Player /></div>
    </div>
    <div class="right-grid">
      <div class="right-grid1"><Player /></div>
      <div class="right-grid2"><Player /></div>
      <div class="right-grid3"><Player /></div>
    </div>
  </div>
</template>

<script>
import Player from "./Player.vue";

export default {
  name: "players-home",
  components: {
    Player,
  },
};
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
  height: 33%;
  margin-bottom: 5px;
}
.left-grid2 {
  height: 33%;
  margin-bottom: 5px;
}
.left-grid3 {
  height: 33%;
}
.right-grid1 {
  height: 33%;
  margin-bottom: 5px;
}
.right-grid2 {
  height: 33%;
  margin-bottom: 5px;
}
.right-grid3 {
  height: 33%;
}
</style>
