<template>
  <div class="mycards-container">
    <div class="text">{player}님의 카드입니다.</div>
    <div class="mycards-wrapper">
      <img src="../assets/cake.svg" alt="오이케이크" />
      <img src="../assets/chicken.svg" alt="신호등치킨" />
      <img src="../assets/durian.svg" alt="두리안" />
      <img src="../assets/eggplant.svg" alt="가지" />
      <img src="../assets/insect.svg" alt="곤충튀김" />
      <img src="../assets/mint.svg" alt="민트초코" />
      <img src="../assets/pizza.svg" alt="파인애플피자" />
      <img src="../assets/sol.svg" alt="솔의눈" />
    </div>
  </div>
</template>

<script>
export default {
  name: "mycards",
  components: {},
};
</script>

<style>
.mycards-container {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.mycards-wrapper{
  margin-left: 10px;
  margin-bottom: 10px;
}
.text {
  margin-bottom: 10px;
  margin-top: 10px;
  color: white;
  font-family: "PressStart2P";
}
</style>
