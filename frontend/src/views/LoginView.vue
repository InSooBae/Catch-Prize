<template>
<div>{{ name }}</div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const name = route.params.name;

onMounted(() => {
  window.location = `https://e5e4-211-192-210-62.jp.ngrok.io/oauth2/authorize/${name}?redirect_uri=http://localhost:3000/redirect`
})
</script>