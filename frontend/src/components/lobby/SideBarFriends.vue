<template>
  <ul v-infinite-scroll="load" class="friends-list" style="padding: 0px;">
    <li v-for="friend in friendsListItems" :key="friend.id" class="d-flex p-1 friends-item">
      <el-avatar shape="circle" class="me-1" :size="28"
        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
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
  friendsListItems: [],
  friendsList: [],
})

const { friendsListItems, friendsList } = toRefs(friends)

friendsList.value = computed(() => store.getters.friendsList)


const load = () => {
  store.dispatch('fetchFriendsList');
  friendsListItems.value = friendsListItems.value.concat(friendsList.value)
}
</script>

<style>
</style>