<template>
</template>

<script setup>
import { onMounted } from 'vue';

onMounted(() => {
  const naverLogin = new naver.LoginWithNaverId({
    clientId: 'I4L7P1yrFjyL_fdoVw3i',
    callbackUrl: `${window.location.origin}/callback/naver`,
    isPopup: true,
  })
  naverLogin.init() 
  naverLogin.reprompt()
})
</script>