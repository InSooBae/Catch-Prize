<template>
  <div class="game-guide">
    <h1>{{guide.guideText}}</h1>
  </div>
</template>

<script setup>
// export default {
//   name: "game-guide",
// };

const props = defineProps({
  guide: Object
})
</script>

<style scoped>
.game-guide {
  color: white;
  font-family: "Galmuri9";
  font-size: 10px;
  width: 100%;
}
</style>
