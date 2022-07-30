<template>
  <div class="mycards-container">
    <div class="text">{player}님의 카드입니다.</div>
    <div class="mycards-wrapper">
      <div>
        <img src="../assets/cake.svg" alt="오이케이크" />
      </div>
      <div>
        <img src="../assets/chicken.svg" alt="신호등치킨" />
      </div>
      <div>
        <img src="../assets/durian.svg" alt="두리안" />
      </div>
      <div>
        <img src="../assets/eggplant.svg" alt="가지" />
      </div>
      <div>
        <img src="../assets/insect.svg" alt="곤충튀김" />
      </div>
      <div>
        <img src="../assets/mint.svg" alt="민트초코" />
      </div>
      <div>
        <img src="../assets/pizza.svg" alt="파인애플피자" />
      </div>
      <div>
        <img src="../assets/sol.svg" alt="솔의눈" />
      </div>
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
  position: relative;
  display: flex;
  /* flex-wrap: wrap; */
  flex-direction: column;
  /* align-items: center; */
  /* justify-content: center; */
}
.mycards-wrapper {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  height: 30vh;
  width: 20vw;
}
.mycards-wrapper div {
  width: 10%;
  height: 90%;
}
.mycards-wrapper img {
  height: 100%;
  /* width: 100%; */
}
.mycards-wrapper img:hover {
  transition: all ease 0.7s 0s;
  transform: scale(1.5) translateX(40px) translateY(-20px);
  z-index: 1;
}
.text {
  display: flex;
  justify-content: center;
  margin-bottom: 10px;
  margin-top: 10px;
  height: 10%;
  color: white;
  font-family: "PressStart2P";
}
</style>
