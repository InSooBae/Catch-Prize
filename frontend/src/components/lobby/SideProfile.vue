<template>
    <div class="d-flex sidebar-profile">
      <el-avatar shape="square" class="me-1" :size="50" src="https://placekitten.com/500/500/" />
      <div class="profile-box">
        <p class="profile-nickname">{{ userInfo.nickname }}</p>
        <p class="profile-point">MyPoint : <span class="text-yello">{{ userInfo.point }}</span></p>
        <p class="profile-email">{{ userInfo.email }}</p>
      </div>
      <img class="profile-logout" src="@/assets/icons/logout.svg" align="right" alt="logout" @click="logout">
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const userInfo = ref({ nickname: '', email: '', point: 0, profile: '' })

onMounted(() => {
  userInfo.value.nickname = '닉네임은여덟글자'
  userInfo.value.email = 'asdfqwerasdfqweasedf@naver.com'
  userInfo.value.point = 1000
})

const logout = () => {
  console.log('logout')
}
</script>