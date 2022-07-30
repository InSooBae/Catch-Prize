<template>
<p>{{ accessToken }}</p>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';

const accessToken = ref('')
const route = useRoute();

accessToken.value = route.query.accessToken;
</script>