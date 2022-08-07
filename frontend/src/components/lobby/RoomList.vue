<template>
  <ul class="d-flex flex-wrap room-list">
    <li v-for="room in roomsList" >
      <RoomItem :room="room" />
    </li>
  </ul>
</template>

<script setup>
import RoomItem from '@/components/lobby/RoomItem.vue'
import { useStore } from 'vuex'
import { computed } from 'vue';

const store = useStore()

const roomsList = computed(() => store.getters.roomsList)
store.dispatch('fetchRooms')

</script>

<style> 
</style>