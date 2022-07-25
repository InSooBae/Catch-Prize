<template>
  <div class="header-home">
    <div
      v-for="(IngameMyProfile, i) in profilesList.filter(
        (c) => c.name === 'player3'
      )"
      :key="i"
      class="ingame-profile-a"
    >
      <InGameProfile :IngameMyProfile="IngameMyProfile" />
    </div>
    <div class="status-home">
      <StatusHome />
    </div>
  </div>
</template>

<script setup>
import StatusHome from "./GameStatusHome.vue";
import InGameProfile from "./InGameProfile.vue";

import { reactive, toRefs } from "vue";
// export default {
//   name: "header-Home",
//   components: {
//     StatusHome,
//     InGameProfile,
//   },
// };

// 받아올 데이터
const profiles = reactive({
  profilesList: [
    { name: "player1", items: [1, 2, 3], profileImg: "a", point: 10 },
    { name: "player2", items: [4, 5, 6], profileImg: "b", point: 30 },
    { name: "player3", items: [7, 8, 9], profileImg: "c", point: 40 },
    { name: "player4", items: [9, 8, 7], profileImg: "d", point: 20 },
    { name: "player5", items: [6, 5, 4], profileImg: "e", point: 50 },
    { name: "player6", items: [3, 2, 1], profileImg: "f", point: 70 },
  ],
});



const { profilesList } = toRefs(profiles);
</script>

<style scoped>
.ingame-profile-a {
  width: 30%;
  min-width: 350px;
}

.header-home {
  position: relative;
  display: flex;
  height: 75px;
  margin-top: 20px;
  width: 100%;
}
.status-home {
  position: relative;
  /* right: 0px; */
  height: 100%;
  width: 70%;
  margin-left: 20px;
}
</style>
