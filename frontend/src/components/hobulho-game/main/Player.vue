<template>
  <div
    :class="[
      player.isAlive === true ? 'player-container' : 'player-container-death',
    ]"
  >
    <div class="status-container">
      <img src="../assets/image15.png" alt="" />
      <div class="player-text">
        <div
          v-on:click="attackTo"
          ref="nicknameRef"
          class="nickname-else"
          :class="{ nickname: $state.gamestate === 'attack' }"
        >
          {{ player.name }}
        </div>
        <div class="remain-card">remain : {{ player.remain }}</div>
      </div>
    </div>
    <div class="card-field">
      <div class="card">
        <div class="num">{{ player.fieldlist[0] }}</div>
        <div class="card-img">
          <img src="../assets/cake.png" alt="케이크" />
        </div>
      </div>
      <div class="card">
        <div class="num">{{ player.fieldlist[1] }}</div>
        <div class="card-img">
          <img src="../assets/durian.png" alt="두리안" />
        </div>
      </div>
      <div class="card">
        <div class="num">{{ player.fieldlist[2] }}</div>
        <div class="card-img">
          <img src="../assets/eggplant.png" alt="가지" />
        </div>
      </div>
      <div class="card">
        <div class="num">{{ player.fieldlist[3] }}</div>
        <div class="card-img">
          <img src="../assets/insect.png" alt="곤충튀김" />
        </div>
      </div>
      <div class="card">
        <div class="num">{{ player.fieldlist[4] }}</div>
        <div class="card-img">
          <img src="../assets/mint.png" alt="민트초코" />
        </div>
      </div>
      <div class="card">
        <div class="num">{{ player.fieldlist[5] }}</div>
        <div class="card-img">
          <img src="../assets/pizza.png" alt="피자" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, inject } from "vue";

const props = defineProps({
  player: Object,
});
const nicknameRef = ref(null);
const $clientstate = inject("$clientstate");
const $hobulhoSocket = inject("$hobulhoSocket");
const $state = inject("$state");

//공격할사람 클릭하면 함수 실행
function attackTo() {
  if ($state.gamestate === "attack") {
    console.log("player-click");
    const defender = nicknameRef.value.textContent;
    console.log(defender);
    $hobulhoSocket.emit("player-click", defender);
  }
}
</script>

<style scoped>
.player-container {
  width: 100%;
  height: 100%;
  margin: 5px;
  padding-top: 20px;
  padding-bottom: 20px;
  background-color: #1b2130;
  border-radius: 20px;
}
.player-container-death {
  width: 100%;
  /* width: 100%; */
  height: 100%;
  margin: 5px;
  padding-top: 20px;
  padding-bottom: 20px;
  background-color: #1b2130;
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
.card {
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
.card img {
  border-radius: 10%;
  width: 100%;
  height: 100%;
  border: 1px solid white;
}
.num {
  color: white;
}
.player-text {
  color: white;
  font-family: "PressStart2P";
  display: flex;
  flex-direction: column;
  /* align-items: center; */
  margin-left: 20px;
  justify-content: center;
}

.nickname-else {
  color: #00bd9d;
}

.nickname:hover {
  opacity: 20%;
  cursor: pointer;
  color: #00bd9d;
}
</style>
