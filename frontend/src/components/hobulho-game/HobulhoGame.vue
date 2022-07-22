<template>
  <div class="hobulho-container">
    <header class="header-container">
      <HeaderHome />
    </header>
    <body class="main-container">
      <div class="left-home"><LeftHome /></div>
      <div class="right-home">
        <RightHome />
      </div>
    </body>
  </div>
</template>

<script>
import HeaderHome from "./header/HeaderHome.vue";
import LeftHome from "./main/LeftHome.vue";
import RightHome from "./main/PlayersHome.vue";

export default {
  name: "hobulho-game",

  components: {
    HeaderHome,
    LeftHome,
    RightHome,
  },
};
</script>

<style>
.hobulho-container {
  margin-left: 20px;
  margin-right: 20px;
  position: relative;
  display: flex;
  flex-direction: column;
  
}
.header-container{
  height: 100%;
  width: 100%;
  margin-bottom: 20px;
}
.main-container{
  display: flex;
  flex-direction: row;
}
.left-home{
  width: 30%;
  min-width: 400px;
  min-height: 800px;
}
.right-home{
  width: 70%;
  margin-left: 20px;
  min-height: 800px;
}
</style>
