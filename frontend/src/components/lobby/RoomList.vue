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
import { reactive, toRefs, computed } from 'vue';

const store = useStore()

const rooms = reactive({
  roomsList: []
})

const { roomsList } = toRefs(rooms)

roomsList.value = computed(() => store.getters.roomsList)
</script>

<style> 
</style>