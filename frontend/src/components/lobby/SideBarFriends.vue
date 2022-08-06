<template>
  <!-- <ul v-infinite-scroll="load" class="friends-list" style="padding: 0px;"> -->
  <ul class="friends-list" style="padding: 0px;">
    pending
    <li v-for="friend in friendsList.pending" :key="friend.id" class="d-flex p-1 friends-item">
      <el-avatar shape="circle" class="me-1" :size="28"
        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
      <div class="friends-name">{{ friend.friendNickname }}</div>
      <button class="friends-delete" @click="acceptFriend(friend.friendNickname)">x</button>
    </li>
    online
    <li v-for="friend in friendsList.online" :key="friend.id" class="d-flex p-1 friends-item">
      <el-avatar shape="circle" class="me-1" :size="28"
        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
      <div class="friends-name">{{ friend.friendNickname }}</div>
      <button class="friends-delete" @click="deleteFriend(friend.friendNickname)">x</button>
    </li>
    offline
    <li v-for="friend in friendsList.offline" :key="friend.id" class="d-flex p-1 friends-item">
      <el-avatar shape="circle" class="me-1" :size="28"
        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
      <div class="friends-name">{{ friend.friendNickname }}</div>
      <button class="friends-delete">x</button>
    </li>
  </ul>
</template>

<script setup>
import { useStore } from 'vuex'
import { computed } from 'vue'

const store = useStore()

const friendsList = computed(() => store.getters.friendsList)

const acceptFriend = (friendNickname) => store.dispatch('acceptFriend', friendNickname)
const deleteFriend = (friendNickname) => store.dispatch('deleteFriend', friendNickname)

store.dispatch('subscribeFriends')
store.dispatch('fetchFriendsList')
</script>

<style>
</style>