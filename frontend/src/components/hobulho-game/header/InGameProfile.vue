<template>
  <div class="ingame-profile">
    <div class="left-container">
      <img src="../assets/Avater.png" alt="프로필사진" />
      <div class="status-text">
        <div class="nickname">Richard</div>
        <div class="mypoint">mypoint : 100</div>
      </div>
    </div>
    <div class="right-container">
      <img src="../assets/loginButton.png" alt="아이탬">
      <img src="../assets/loginButton.png" alt="아이탬">
      <img src="../assets/loginButton.png" alt="아이탬">
      <img src="../assets/loginButton.png" alt="ㅁ">
    </div>
  </div>
</template>

<script>
export default {
  name: "ingame-pofile",
};
</script>

<style scoped>
.ingame-profile{
  
  height: 100%;
  width: 100%;
  display: flex;
  background-color: #1B2130;
  border-radius: 20px;
}
.left-container{
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
  display: flex;
  padding: 25px;
}
</style>
