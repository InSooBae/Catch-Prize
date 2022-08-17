<template>
  <StarBackground />
  <Game />
</template>

<script setup>
import Game from "../components/hobulho-game/HobulhoGame.vue";
import StarBackground from "../components/StarBackground.vue";
import { inject } from "vue";
import { useRoute, useRouter } from "vue-router";
import jwt_decode from "jwt-decode";
import io from "socket.io-client";

const route = useRoute();
const router = useRouter();
const $hobulhoSocket = inject("$hobulhoSocket");
const username = jwt_decode(sessionStorage.getItem("token")).username;
const $clientstate = inject("$clientstate");
$clientstate.myid = username;
// const dataSocket = io("http://localhost:8081/dataSocket", {
//   transports: ["websocket"],
// });

if (route.query.isHost) {
  const gameData = {
    roomid: route.params.roomid,
    users: route.query.users,
  };

  console.log(gameData);
  console.log("2123123123123123123");
  $hobulhoSocket.emit("start-data-set", gameData);
}

router.push({
  name: "gameplayroom",
  params: { roomid: route.params.roomid, myid: username },
});
</script>
