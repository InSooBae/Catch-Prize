<template>
  <div class="mycards-container">
    <div class="text">{{ $hobulhostate.attacker }} 님의 카드입니다.</div>
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
// import io from "socket.io-client";
// const socket = io("http://localhost:8081",{ transports : ['websocket'] });
import { ref, onMounted, inject, computed, reactive, toRefs } from "vue";

const asd = ref(null);
const $hobulhoSocket = inject("$hobulhoSocket");
const $hobulhostate = inject("$hobulhostate");
// const myHandcards = {
//   cake: 2,
//   durian: 2,
//   eggplant: 1,
//   insect: 1,
//   mint: 1,
//   pizza: 1,
//   remain: 8,
// };
// const remain = 8;
const myHandcards = reactive([
  {
    name: "cake",
    num: 2,
    // imgsrc: computed(() => {
    //   return cakesrc;
    // }),
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
// var myHandcardsList = [];
let Hand = reactive({
  myHandcardsList: [],
});
const myHandupdate = () => {
  let list = [];
  for (var t = 0; t < 6; t++) {
    for (var i = 0; i < myHandcards[t].num; i++) {
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
// const btn = document.querySelector("#btn1");
function onClick(myHand) {
  if ($hobulhostate.gamestate === "select") {
    console.log("click");
    const cardname = myHand;
    $hobulhoSocket.emit("card-click", cardname);
    $hobulhostate.selectedcard = cardname;
    $hobulhostate.gamestate = "attack";
    for (var t = 0; t < 6; t++) {
      if (myHandcards[t].name === myHand) {
        myHandcards[t].num--;
        $hobulhostate.selectedcardnum = t;
      }
    }
    myHandupdate();
    console.log(myHandcards);
    console.log(myHandcardsList);
  }
}

$hobulhoSocket.on("rec-test", (temp) => {
  console.log(`You ${temp} has created the room!`);
});
myHandupdate();
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
  transform: scale(1.4) translateX(1.7vw) translateY(-2vw);
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
