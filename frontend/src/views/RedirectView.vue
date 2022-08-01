<template>
  <p>{{ token }}</p>
</template>

<script setup>
import { onMounted, ref, computed } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();

onMounted(() => {
  localStorage.setItem('token', route.query.accessToken)
  window.opener.location.reload()
  window.close()
})
</script>