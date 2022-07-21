<template>
  <div class="left-container">
    <div><LargeScreen /></div>
    <div><MyCards /></div>
    <div><ItemShop /></div>
  </div>
</template>

<script>
import ItemShop from "./ItemShopBar.vue";
import LargeScreen from "./LargeScreen.vue";
import MyCards from "./mycards.vue";

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
  /* border: 1px solid white; */
  display: flex;
  flex-direction: column;
  border-radius: 20px;
  width: 500px;
  align-items: center;
  justify-content: center;
}
</style>
