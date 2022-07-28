<template>
<div>naver</div>
</template>

<script setup>
import { onMounted } from 'vue';

onMounted(() => {
  const naverLogin = new naver.LoginWithNaverId({
    clientId: 'FqMSAC4J1Y8xLTVFMHdH',
    callbackUrl: 'http://localhost:8080/oauth2/callback/naver',
    callbackHandle: true
  })
  naverLogin.init() 
  naverLogin.reprompt()
})
</script>