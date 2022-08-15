<template>
  <div class="liar-player-container">
    <div>
      <img src="../assets/image15.png" alt="" />
    </div>
    <div class="liar-player-profile">
      <div>{name}</div>
      <div>point : 00</div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.liar-player-container {
  position: relative;
  border: 5px solid #7608d3;
  border-radius: 20px;
  margin: 2rem;
  width: 30vw;
  height: 35vh;
}
.liar-player-container img {
  width: 100%;
  height: 100%;
}
.liar-player-profile {
  position: absolute;
  left: 0px;
  top: 0px;
  background-color: #1b2130;
  opacity: 90%;
  border-radius: 10px;
}
.liar-player-profile div {
  margin: 5px;
  color: white;
  font-family: "Galmuri9";
}
</style>
