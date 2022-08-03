<template lang="">
  <div class="defend-container">
    <div class="tf-btn">
      <button class="btn true-btn" @click="defendJudge(true)">True</button>
      <button class="btn false-btn" @click="defendJudge(false)">False</button>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted, inject, reactive } from "vue";
const $hobulhoSocket = inject("$hobulhoSocket");
const $hobulhostate = inject("$hobulhostate");

const defendjudge = reactive({
  defenderId: "1234",
  attackerId: "123",
  judge: { declaration: false },
  truth: "",
});
if ($hobulhostate.selectedcard == $hobulhostate.declaredcard) {
  defendJudge.truth = true;
} else {
  defendJudge.truth = false;
}

// 답에 따라서 TF 리턴하는 함수
function defendJudge(answer) {
  console.log("truth:" + defendJudge.truth);
  console.log("answer:" + answer);
  if (defendJudge.truth === answer) {
    //판단성공
    $hobulhostate.gamestate = "success";
    setTimeout(() => {
      $hobulhoSocket.emit("judge-success", $hobulhostate.selectedcard);
      console.log($hobulhostate.gamestate);
    }, 3000);
  } else {
    //판단실패
    $hobulhostate.gamestate = "fail";
    setTimeout(() => {
      $hobulhoSocket.emit("judge-fail", $hobulhostate.selectedcard);
      console.log($hobulhostate.gamestate);
    }, 3000);
  }
  console.log($hobulhostate.gamestate);
}
</script>
<style>
.tf-btn {
  /* layout */
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  /* box */
  min-height: 80vh;
  max-width: 100vw;
  /* backgournd */
  /* font */
  /* etc */
}
.true-btn {
  /* layout */
  /* box */
  /* backgournd */
  /* font */
  /* etc */
  background-color: rgba(23, 29, 43, 0.8);
  border: 7px;
  border-style: solid;
  border-color: #2572cc;
  color: white;
}
.true-btn:hover {
  background-color: #2572cc;
  color: white;
  font-size: 1rem;
  /* border-radius: 30%; */
}
.false-btn {
  /* layout */
  /* box */
  border: 7px;
  border-style: solid;
  border-color: #f24822;
  /* backgournd */
  background-color: rgba(23, 29, 43, 0.8);
  /* font */
  color: white;
  /* etc */
}
.false-btn:hover {
  color: white;
  background-color: #f24822;
  font-size: 1rem;
}
.btn {
  /* layout */
  display: inline-block;
  /* box */
  border-radius: 2em;
  height: 13em;
  width: 13em;
  margin: 70px;
  /* background */
  /* background-color: rgba(23,29,43,0.8); */
  /* font */
  font-family: "PressStart2P";
  /* etc */
  transition: all 0.3s;
  cursor: pointer;
}
/* layout */
/* box */
/* backgournd */
/* font */
/* etc */
</style>
