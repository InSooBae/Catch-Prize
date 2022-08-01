<template>
  <ul class="d-flex flex-wrap room-list">
    <li v-for="room in roomsList" >
      <RoomItem :room="room" />
    </li>
  </ul>
</template>

<script setup>
import RoomItem from '@/components/lobby/RoomItem.vue'

import { reactive, toRefs } from 'vue'

// 받아올 데이터
const roomsList = [
    { name: 'GameRoom1', isPlaying: false, type: 'Poker', maxParti: 6, nowParti: 4},
    { name: 'GameRoom2', isPlaying: true, type: 'Soccer', maxParti: 6, nowParti: 6},
    { name: 'GameRoom3', isPlaying: false, type: 'Game', maxParti: 6, nowParti: 2},
    { name: 'GameRoom4', isPlaying: true, type: 'Poker', maxParti: 6, nowParti: 6},
    { name: 'GameRoom5', isPlaying: false, type: 'Poker', maxParti: 6, nowParti: 4},
    { name: 'GameRoom6', isPlaying: true, type: 'Poker', maxParti: 6, nowParti: 6},
    { name: 'GameRoom7', isPlaying: true, type: 'Poker', maxParti: 6, nowParti: 6},
    { name: 'GameRoom8', isPlaying: false, type: 'Poker', maxParti: 6, nowParti: 3},
  ]
</script>

<style> 
</style>