<template>
  <div class="itemshop-container">
    <div>
      <img src="../assets/loginButton.png" alt="" />
    </div>
    <div>
      <img src="../assets/loginButton.png" alt="" />
    </div>
    <div>
      <img src="../assets/loginButton.png" alt="" />
    </div>
    <div>
      <img src="../assets/loginButton.png" alt="" />
    </div>
    <div>
      <img src="../assets/loginButton.png" alt="" />
    </div>
    <div>
      <img src="../assets/loginButton.png" alt="" />
    </div>
  </div>
</template>

<script>
export default {
  name: "itemshop",
  components: {},
};
</script>

<style>
.itemshop-container {
  display: flex;
  flex-direction: row;
  width: 100%;
  height: 100%;
}
.itemshop-container div{
  width: 16%;
}
.itemshop-container img {
  width: 60%;
  height: 60%;
}
</style>
