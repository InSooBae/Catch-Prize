<template>
  <div :class="[player.isDeath === true? 'player-container-death': 'player-container']">
    <div class="status-container">
      <img src="../assets/image 15.png" alt="" />
      <div class="player-text">
        <div class="nickname">{{player.name}}</div>
        <div class="remain-card">remain : {{player.remain}}</div>
      </div>
    </div>
    <div class="card-field">
      <div class="card">
        <div class="num">{{player.fieldlist[0]}}</div>
        <div class="card-img">
          <img src="../assets/cake.png" alt="케이크" />
        </div>
      </div>
      <div class="card">
        <div class="num">{{player.fieldlist[1]}}</div>
        <div class="card-img">
          <img src="../assets/chicken.png" alt="치킨" />
        </div>
      </div>
      <div class="card">
        <div class="num">{{player.fieldlist[2]}}</div>
        <div class="card-img">
          <img src="../assets/durian.png" alt="두리안" />
        </div>
      </div>
      <div class="card">
        <div class="num">{{player.fieldlist[3]}}</div>
        <div class="card-img">
          <img src="../assets/eggplant.png" alt="가지" />
        </div>
      </div>
      <div class="card">
        <div class="num">{{player.fieldlist[4]}}</div>
        <div class="card-img">
          <img src="../assets/insect.png" alt="곤충튀김" />
        </div>
      </div>
      <div class="card">
        <div class="num">{{player.fieldlist[5]}}</div>
        <div class="card-img">
          <img src="../assets/mint.png" alt="민트초코" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>

const props = defineProps({
  player: Object
})
</script>

<style>
.player-container{
  width: 100%;
  height: 100%;
  margin: 5px;
  padding-top: 20px;
  padding-bottom: 20px;
  background-color: #1B2130;
  border-radius: 20px;
}
.player-container-death{
  width: 100%;
  /* width: 100%; */
  height: 100%;
  margin: 5px;
  padding-top: 20px;
  padding-bottom: 20px;
  background-color: #1B2130;
  border-radius: 20px;
  opacity: 0.3;
}
.status-container {
  
  margin-left: 30px;
  padding-bottom: 10px;
  height: 40%;
  display: flex;
  flex-direction: row;
}
.card{
  margin: 5px;
  width: 13%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.card-field {
  height: 10vh;
  width: 30vw;
  /* margin-left: 30px; */
  display: flex;
  flex-direction: row;
  justify-content: center;
  /* border: 1px solid white; */
  margin-left: 1vw;
  margin-right: 1vw;
}
.card-img {
  width: 100%;
  height: 100%;
}
.card img{
  border-radius: 10%;
  width: 100%;
  height: 100%;
  border: 1px solid white;
}
.num{
  color: white;
}
.player-text{
  color: white;
  font-family: "PressStart2P";
  display: flex;
  flex-direction: column;
  /* align-items: center; */
  margin-left: 20px;
  justify-content: center;
}
</style>
