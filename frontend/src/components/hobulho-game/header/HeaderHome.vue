<template>
  <div class="header-home">
    <div class="ingame-profile-a">
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
.ingame-profile-a{
  /* min-width: 500px; */
  width: 30%;
  min-width: 400px;
}

.header-home {
  position: relative;
  display: flex;
  height: 80px;
  margin-top: 20px;
  width: 100%;
}
.status-home{
  position: relative;
  /* right: 0px; */
  height: 100%;
  width: 70%;
  margin-left: 20px;
}
</style>
