<template>
  <div class="d-flex sidebar-profile">
    <el-avatar shape="square" class="me-1" :size="50" src="https://placekitten.com/500/500/" />
    <div class="profile-box">
      <p class="profile-nickname">{{ userName }}</p>
      <p class="profile-point">MyPoint : <span class="text-yello">{{ userPoint }}</span></p>
      <p class="profile-email">{{ userEmail }}</p>
    </div>
    <img class="profile-logout" src="@/assets/icons/logout.svg" align="right" alt="logout" @click="logout">
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useStore } from 'vuex'

const store = useStore()

const userName = computed(() => store.getters.userName)
const userEmail = computed(() => store.getters.userEmail)
const userPoint = computed(() => store.getters.userPoint)

const logout = () => {
  console.log('logout')
}
</script>