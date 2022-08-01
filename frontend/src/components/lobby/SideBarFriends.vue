<template>
  <ul class="friends-list" style="padding: 0px;">
    <li v-for="friend in friendsList" :key="friend" class="d-flex p-1 friends-item">
      <el-avatar shape="circle" class="me-1" :size="28"
        :src="`https://placekitten.com/${friend.profile}/${friend.profile}`" />
      <p class="friends-name">{{ friend.name }}</p>
    </li>
  </ul>
</template>

<script setup>
import { reactive, toRefs } from 'vue'

const friends = reactive({
  friendsList: [
    { profile: '100', name: '황태희' },
    { profile: '200', name: '김태희' },
    { profile: '300', name: '이태희' },
    { profile: '123', name: '박태희' },
    { profile: '124', name: '고태희' },
    { profile: '125', name: '스탑태희' },
    { profile: '126', name: '돈스탑태희' },
    { profile: '100', name: '황태희' },
    { profile: '200', name: '김태희' },
    { profile: '300', name: '이태희' },
    { profile: '123', name: '박태희' },
    { profile: '124', name: '고태희' },
    { profile: '125', name: '스탑태희' },
    { profile: '126', name: '돈스탑태희' },
  ],
})

const { friendsList } = toRefs(friends)

</script>

<style>
</style>