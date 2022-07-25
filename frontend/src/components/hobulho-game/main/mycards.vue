<template>
  <div class="mycards-container">
    <div class="text">{player}님의 카드입니다.</div>
    <div class="mycards-wrapper">
      <div class="up-cards-wrapper">
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
      </div>
      <div class="down-cards-wrapper">
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
.mycards-wrapper {
  margin-bottom: 10px;
  width: 40vw;
  height: 40vh;
  
}
.up-cards-wrapper{
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
}
.up-cards-wrapper div{
  width: 25%;
  height: 100%;
}

.up-cards-wrapper img{
  width: 90%;
  height: 100%;
  transition: all 0.2s linear;
}

.up-cards-wrapper img:hover{
  transform: scale(2.5) translateX(30px) translateY(-30px);
  z-index: 1;
}

.down-cards-wrapper{
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.down-cards-wrapper div{
  width: 25%;
  height: 100%;
}
.down-cards-wrapper img{
  width: 90%;
  height: 100%;
  transition: all 0.2s linear;
}
.down-cards-wrapper img:hover{
  transform: scale(2.5) translateX(30px) translateY(-30px);
  z-index: 1;
}
.text {
  margin-bottom: 10px;
  margin-top: 10px;
  height: 10%;
  color: white;
  font-family: "PressStart2P";
}
</style>
