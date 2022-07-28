<template>
<div>naver 콜백입니다.</div>
<div>
  <p>{{ userInfo.email }}</p>
  <p>{{ userInfo.profileImage }}</p>
  <p>{{ userInfo.birthday }}</p>
  <p>{{ userInfo.uniqId }}</p>
</div>
</template>

<script setup>
import { onMounted, reactive } from 'vue';
const userInfo = reactive({
  email: '',
  profileImage: '',
  birthday: '',
  uniqId: '',
})

onMounted(() => {
  const naverLogin = new naver.LoginWithNaverId({
    clientId: 'clientId',
    isPopup: false
  })
  naverLogin.init()
  naverLogin.getLoginStatus(function(status) {
    if (status) {
      userInfo.email = naverLogin.user.getEmail()
      userInfo.profileImage = naverLogin.user.getProfileImage()
      userInfo.birthday = naverLogin.user.getBirthday()
      userInfo.uniqId = naverLogin.user.getId()
    } else {
      console.log('AccessToken이 올바르지 않습니다.')
    }
    window.close()
  })
})
</script>