<template>
  <div class="d-flex sidebar-profile">
    <el-avatar shape="square" class="me-1" :size="50" src="https://placekitten.com/500/500/" />
    <div class="profile-box">
      <p class="profile-nickname">{{ currentUser.username }}</p>
      <p class="profile-point">MyPoint : <span class="text-yello">{{ currentUser }}</span></p>
      <p class="profile-email">{{ currentUser }}</p>
    </div>
    <img class="profile-logout" src="@/assets/icons/logout.svg" align="right" alt="logout" @click="logout">
  </div>
</template>

<script setup>
import { computed, onMounted } from 'vue'
import { useStore } from 'vuex'

const store = useStore()
const currentUser = computed(() => store.getters.currentUser)
store.dispatch('fetchCurrentUser');

const logout = () => {
  store.dispatch('logout');
}
</script>