<template>
  <StarBackground />
  <Game />
  <button @click="getData">sdsdsd</button>
</template>

<script setup>
import Game from "../components/hobulho-game/HobulhoGame.vue"
import StarBackground from "../components/StarBackground.vue";
import { inject } from "vue";
import { useRoute, useRouter } from "vue-router";
import jwt_decode from "jwt-decode";

const route = useRoute()
const router = useRouter()
const $hobulhoSocket = inject("$hobulhoSocket");
const username = jwt_decode(sessionStorage.getItem('token')).username

if (route.query.isHost) {
  const gameData = {
    roomid: route.params.roomid,
    users: route.query.users
  }
  $hobulhoSocket.emit("start-data-set", gameData)
}

router.push({
  name: 'gameplayroom',
  params: { roomid: route.params.roomid, myid: username }
})
</script>
