<template>
  <div class="mycards-container">
    <div class="text">
      <div class="current-player-text">{{ $clientstate.myid }}&nbsp;</div>
      <div>님의 카드입니다.</div>
    </div>
    <div class="mycards-wrapper">
      <div
        v-for="(myHand, i) in myHandcardsList"
        :key="i"
        v-on:click="onClick(myHand)"
        ref="asd"
        class="asd"
      >
        <img v-if="myHand === 'cake'" :src="cakeimgsrc" :alt="`${myHand}`" />
        <img
          v-if="myHand === 'durian'"
          :src="durianimgsrc"
          :alt="`${myHand}`"
        />
        <img
          v-if="myHand === 'eggplant'"
          :src="eggplantimgsrc"
          :alt="`${myHand}`"
        />
        <img
          v-if="myHand === 'insect'"
          :src="insectimgsrc"
          :alt="`${myHand}`"
        />
        <img v-if="myHand === 'mint'" :src="mintimgsrc" :alt="`${myHand}`" />
        <img v-if="myHand === 'pizza'" :src="pizzaimgsrc" :alt="`${myHand}`" />
      </div>
    </div>
  </div>
</template>

<script setup>
import cakesrc from "../assets/cake.svg";
import duriansrc from "../assets/durian.svg";
import eggplantsrc from "../assets/eggplant.svg";
import insectsrc from "../assets/insect.svg";
import mintsrc from "../assets/mint.svg";
import pizzasrc from "../assets/pizza.svg";
import { ref, onMounted, inject, computed, reactive, toRefs } from "vue";

const asd = ref(null);
const $hobulhoSocket = inject("$hobulhoSocket");
const $clientstate = inject("$clientstate");
const $state = inject("$state");
const $attackstate = inject("$attackstate");
const myHandcards = reactive([
  {
    name: "cake",
    num: 2,
  },
  {
    name: "durian",
    num: 2,
  },
  {
    name: "eggplant",
    num: 1,
  },
  {
    name: "insect",
    num: 1,
  },
  {
    name: "mint",
    num: 1,
  },
  {
    name: "pizza",
    num: 1,
  },
]);
let Hand = reactive({
  myHandcardsList: [],
});
const myHandupdate = () => {
  let list = [];
  //내아이디와 같은 핸드를 푸쉬
  for (let t = 0; t < 6; t++) {
    if ($state.players[t].playerId === $clientstate.myid) {
      myHandcards[0].num = $state.players[t].cards.hand.cake;
      myHandcards[1].num = $state.players[t].cards.hand.durian;
      myHandcards[2].num = $state.players[t].cards.hand.eggplant;
      myHandcards[3].num = $state.players[t].cards.hand.insect;
      myHandcards[4].num = $state.players[t].cards.hand.mint;
      myHandcards[5].num = $state.players[t].cards.hand.pizza;
    }
  }
  for (let t = 0; t < 6; t++) {
    for (let i = 0; i < myHandcards[t].num; i++) {
      list.push(myHandcards[t].name);
    }
  }
  Hand.myHandcardsList = list;
};
const cakeimgsrc = computed(() => {
  return cakesrc;
});
const durianimgsrc = computed(() => {
  return duriansrc;
});
const eggplantimgsrc = computed(() => {
  return eggplantsrc;
});
const insectimgsrc = computed(() => {
  return insectsrc;
});
const mintimgsrc = computed(() => {
  return mintsrc;
});
const pizzaimgsrc = computed(() => {
  return pizzasrc;
});
$hobulhoSocket.on("hobulho-start-card", function (data) {
  myHandupdate();
});



function onClick(myHand) {
  if ($state.gamestate === "select") {
    const cardname = myHand;
    //카드를 선택하면 카드이름과 내 아이디를 보냄
    $hobulhoSocket.emit("card-click", cardname, $clientstate.myid);
    $hobulhoSocket.on("players-refresh", function () {
      //gamestate attack으로 변경
      myHandupdate();
    });
  }
}

const { myHandcardsList } = toRefs(Hand);
</script>

<style scoped>
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
  margin-left: 0.5vw;
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
  transform: scale(1.4) translateX(1.4vw) translateY(-2vw);
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
.current-player-text {
  color: #00bd9d;
}
</style>
