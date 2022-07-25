<template>
  <div class="ingame-profile">
    <div class="left-container">
      <img src="../assets/Avater.png" alt="프로필사진" />
      <div class="status-text">
        <div class="nickname">{{IngameMyProfile.name}}</div>
        <div class="mypoint">mypoint : {{IngameMyProfile.point}}</div>
      </div>
    </div>
    <div class="right-container">
      <img src="../assets/loginButton.png" alt="아이탬">
      <img src="../assets/loginButton.png" alt="아이탬">
      <img src="../assets/loginButton.png" alt="아이탬">
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  IngameMyProfile: Object
})
</script>

<style scoped>
.ingame-profile{
  
  height: 10vh;
  width: 100%;
  display: flex;
  background-color: #1B2130;
  border-radius: 20px;
}
.left-container{
  height: 10vh;
  border: none;
  padding: 20px;
  display: flex;
  flex-direction: row;

}
.status-text{
  color: white;
  display: flex;
  flex-direction: column;
  margin-left: 10px;
}
.right-container{
  height: 10vh;
  display: flex;
  padding: 25px;
}
</style>
