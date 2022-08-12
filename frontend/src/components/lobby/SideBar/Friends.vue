<template>
  <el-collapse class="friends-list" v-model="activeNames">
    <el-collapse-item name="pending">
      <template #title>
        <span style="margin: 0 0.25rem 0 2px;">대기</span>
        <span>중</span>
      </template>
      <ul>
        <li v-for="friend in friendsList.pending" :key="friend.id" class="d-flex p-1 friends-item">
          <el-avatar shape="circle" class="me-1" :size="28"
            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
          <div class="friends-name">{{ friend.friendNickname }}</div>
          <el-button color="#262C3A" type="info" class="friends-button" @click="acceptFriend(friend.friendNickname)">
            <img class="accept-icon" src="@/assets/icons/add_plus.svg" alt="+">
          </el-button>
        </li>
      </ul>
    </el-collapse-item>
    <el-collapse-item name="online">
      <template #title>
        <span style="margin-left: 2px;">온라인</span>
      </template>
      <ul>
        <li v-for="friend in friendsList.online" :key="friend.id" class="d-flex p-1 friends-item">
          <el-avatar shape="circle" class="me-1 avatar" :size="28"
            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
          <div class="friends-name">{{ friend.friendNickname }}</div>
          <el-button color="#262C3A" type="info" class="friends-button" @click="deleteFriend(friend.friendNickname)">
            <img class="delete-icon" src="@/assets/icons/close_delete.svg" alt="x">
          </el-button>
        </li>
      </ul>
    </el-collapse-item>
    <el-collapse-item name="offline">
      <template #title>
        <span style="margin-left: 2px;">오프라인</span>
      </template>
      <ul>
        <li v-for="friend in friendsList.offline" :key="friend.id" class="d-flex p-1 friends-item friends-offline">
          <el-avatar shape="circle" class="me-1" :size="28"
            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
          <div class="friends-name">{{ friend.friendNickname }}</div>
          <el-button color="#262C3A" type="info" class="friends-button">
            <img class="delete-icon" src="@/assets/icons/close_delete.svg" alt="x">
          </el-button>
        </li>
      </ul>
    </el-collapse-item>
  </el-collapse>
</template>

<script setup>
import { useStore } from 'vuex'
import { ref, computed } from 'vue'

const store = useStore()

const activeNames = ref(['pending', 'online'])
const friendsList = computed(() => store.getters.friendsList)

const acceptFriend = (friendNickname) => store.dispatch('acceptFriend', friendNickname)
const deleteFriend = (friendNickname) => store.dispatch('deleteFriend', friendNickname)

store.dispatch('subscribeFriends')
store.dispatch('fetchFriendsList')
</script>

<style>
</style>