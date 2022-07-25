<template>
  <div class="lobby-room-item" @click="enter(room.name)">
    <p class="text-white">{{ room.name }}</p>
    <p v-if="room.isPlaying" class="text-green">NOW PLAYING</p>
    <p v-else class="text-green">WAITING</p>
    <p class="text-green">{{ room.type }}...</p>
    <p class="text-white" style="position: absolute; right: 10px; bottom: 10px;">{{ room.nowParti }}/{{ room.maxParti }}</p>
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