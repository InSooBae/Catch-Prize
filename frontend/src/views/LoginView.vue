<template>
<div>{{ name }}</div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const name = route.params.name;

onMounted(() => {
  window.location = `https://bb79-211-106-9-114.ngrok.io/oauth2/authorize/${name}?redirect_uri=http://localhost:3000/redirect`
})
</script>