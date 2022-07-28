<template>
<div>kakao</div>
</template>

<script setup>
import { onMounted } from 'vue';

onMounted(() => {
  Kakao.init('efb9ee3b699c56ffdc4bc6c4462bb5bf')
  Kakao.Auth.authorize({
    redirectUri: 'http://localhost:8080/oauth2/callback/kakao'
  })
})
</script>