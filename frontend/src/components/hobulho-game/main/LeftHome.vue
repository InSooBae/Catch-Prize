<template>
  <div class="left-container">
    <div class="large-face-cam-wrapper"><LargeScreen /></div>
    <div class="mycard-list"><MyCards /></div>
    <div class="item-shop"><ItemShop /></div>
  </div>
</template>

<script>
import ItemShop from "./ItemShopBar.vue";
import LargeScreen from "./LargeScreen.vue";
import MyCards from "./Mycards.vue";

export default {
  name: "left-home",
  components: {
    ItemShop,
    LargeScreen,
    MyCards,
  },
};
</script>

<style>
.left-container {
  padding-top: 20px;
  padding-bottom: 10px;
  background-color: #1b2130;
  display: flex;
  flex-direction: column;
  border-radius: 20px;
  width: 100%;
  height: 87%;
  align-items: center;
  justify-content: center;
}
.large-face-cam-wrapper{
  height: 40%;
  width: 80%;
}
.mycard-list{
  width: 90%;
  height: 45%;
}
.item-shop{
  width: 90%;
  height: 5%;
  margin-top: 20px;
}
</style>
