<template>
  <div class="lobby-room-item d-flex justify-content-between" @click="enter(room.name)">
  <div>
    <p class="room-name text-white">{{ room.name }}</p>
    <p v-if="room.isPlaying" class="text-green">NOW PLAYING</p>
    <p v-else class="text-green">WAITING</p>
    <p class="text-green">{{ room.type }}...</p>
  </div>
    <p class="text-white align-self-start">{{ room.nowParti }}/{{ room.maxParti }}</p>
  </div>
</template>

<script setup>
const props = defineProps({
  room: Object
})

const enter = (name) => {
  console.log(name)
}

</script>

<style>
</style>