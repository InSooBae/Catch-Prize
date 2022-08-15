<template>
  <div class="topic-cintainer">
    <div v-for="i in 10">주제</div>
  </div>
</template>

<script setup></script>

<style scoped>
.topic-cintainer {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
}
.topic-cintainer div {
  border: 3px solid #7608d3;
  width: 10vw;
  height: 10vh;
  margin: 5vh;
  border-radius: 20px;
  background-color: #1b2130;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-family: "galmuri9";
  font-size: 1.5rem;
}
</style>
