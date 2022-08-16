<template>
  <div class="itemshop-container">
    <div>
      <el-button @click="VideoRotationItem()">화면 뒤집기</el-button>
    </div>
    <div>
      <el-button @click="PitchChangeItem()">음성 변조</el-button>
    </div>
    <div>
      <el-button @click="VideoControlItem()">화면 변조</el-button>
    </div>
    <div>
      <el-button @click="FaceEmotionItem()">감정 분석</el-button>
    </div>
    <div>
      <el-button @click="MuteItem()">음소거</el-button>
    </div>
    <div>
      <el-button @click="VideoFillItem()">화면 뒤덮기</el-button>
    </div>
  </div>
</template>

<script setup>
import { useStore, inject } from "vuex";
const $hobulhoSocket = inject("$hobulhosocket");

//화면 뒤집기
//나한테 적용
function VideoRotationItem() {
  sendFilter("abc", "Rotation");
}
//음성 변조
//나한테 적용
function PitchChangeItem() {
  sendFilter("abc", "Pitch");
}
//화면 변조
//나한테 적용
function VideoControlItem() {
  sendFilter("abc", "Chroma");
}
//감정분석
//사용할 사람 고르기
function FaceEmotionItem() {
  //룸아이디랑 사용할 사람 닉네임 보내기
  //감정분석후 전체에 뿌려줌
  $hobulhoSocket.emit("use-face-api-item", roomid, playerid);
  sendFilter("abc", "Rotation");
}
//음소거
//사용할 사람 고르기
function MuteItem() {
  //룸아이디랑 음소거 할 사람을 보냄
  $hobulhoSocket.emit("use-mute-item", roomid, playerid);
  sendFilter("abc", "Rotation");
}
//화면 뒤덮기
//사용할 사람 고르기
function VideoFillItem() {
  
  $hobulhoSocket.emit("use-videofill-item", roomid, playerid);
  sendFilter("abc", "Rotation");
}

const store = useStore();
const sendFilter = () => store.dispatch("sendFilter");
</script>

<style scoped>
.itemshop-container {
  display: flex;
  flex-direction: row;
  width: 100%;
  height: 100%;
}
.itemshop-container div {
  width: 16%;
}
.itemshop-container img {
  width: 60%;
  height: 60%;
}
</style>
