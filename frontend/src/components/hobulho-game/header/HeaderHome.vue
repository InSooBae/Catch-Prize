<template>
  <div class="header-home">
    <div class="ingame-profile">
      <InGameProfile />
    </div>
    <div class="status-home">
      <StatusHome />
    </div>
  </div>
</template>

<script>
import StatusHome from "./GameStatusHome.vue";
import InGameProfile from "./InGameProfile.vue";

export default {
  name: "header-Home",
  components: {
    StatusHome,
    InGameProfile,
  },
};
</script>

<style scoped>
.header-home {
  position: relative;
  /* background-size: cover; */
  /* position: relative; */
  display: flex;
  height: 80px;
  margin-top: 20px;
}
.status-home{
  position: relative;
  right: 0px;
  height: 100%;
  width: 100%;
  margin-left: 20px;
}
</style>
