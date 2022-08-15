<template>
  <div class="header-home">
    <div
      v-for="(IngameMyProfile, i) in profilesList.filter(
        (c) => c.name === $clientstate.myid
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

import { reactive, toRefs, inject } from "vue";
const $clientstate = inject("$clientstate");
const $dataBox = inject("$dataBox");
const $hobulhoSocket = inject("$hobulhoSocket");
// 받아올 데이터
const profiles = reactive({
  profilesList: [
    { name: "player11", items: [1, 2, 3], profileImg: "a", point: 10 },
    { name: "player21", items: [4, 5, 6], profileImg: "b", point: 30 },
    { name: "player31", items: [7, 8, 9], profileImg: "c", point: 40 },
    { name: "player41", items: [9, 8, 7], profileImg: "d", point: 20 },
    { name: "player51", items: [6, 5, 4], profileImg: "e", point: 50 },
    { name: "player61", items: [3, 2, 1], profileImg: "f", point: 70 },
  ],
});

function whichData(roomid) {
  for (let t = 0; t < $dataBox.length; t++) {
    if ($dataBox[t].controlstate.roomId === roomid) {
      return t;
    }
  }
}

function playerSetting() {
  console.log($dataBox);
  console.log("122222");
  let datanum = whichData($clientstate.roomid);
  console.log(datanum);
  for (let t = 0; t < 6; t++) {
    profiles.profilesList[t].name = $dataBox[datanum].players[t].playerId;
    profiles.profilesList[t].point = $dataBox[datanum].players[t].point;
  }
  console.log(profiles);
}

$hobulhoSocket.on("players-profile-setting", function () {
  playerSetting();
});

const { profilesList } = toRefs(profiles);
</script>

<style scoped>
.ingame-profile-a {
  height: 100%;
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
