<template>
  <div class="players-container">
    <div><Player/></div>
    <div><Player/></div>
    <div><Player/></div>
    <div><Player/></div>
    <div><Player/></div>
    <div><Player/></div>
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
.players-container{
  
  margin-left: 20px;
  display: flex;
  flex-wrap: wrap;
  /* border: 1px solid white; */
  align-items: center;
  justify-content: center;
}
</style>
