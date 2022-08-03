<template>
  <ul vv-infinite-scroll="load" class="friends-list" style="padding: 0px;">
    <li v-for="friend in friendsList" :key="friend.name" class="d-flex p-1 friends-item">
      <el-avatar shape="circle" class="me-1" :size="28"
        src="" />
      <p class="friends-name">{{ friend.name }}</p>
      <button class="friends-delete">x</button>
    </li>
  </ul>
</template>

<script setup>
import { useStore } from 'vuex'
import { reactive, toRefs, computed, ref } from 'vue'

const store = useStore()

const friends = reactive({
  friendsList: [
  ],
})

const { friendsList } = toRefs(friends)

friendsList.value = computed(() => store.getters.friendsList)

const load = () => {
  store.dispatch('fetchFriendsList');
}
</script>

<style>
</style>