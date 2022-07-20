<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <!-- <header>
    <div>
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/signup">Signup</RouterLink>
        <RouterLink to="/lobby">Lobby</RouterLink>
        <RouterLink to="/game">Game</RouterLink>
      </nav>
    </div>
  </header> -->
  <RouterView />
</template>


<style>
.v-application {
  font-family: 'PressStart2P', sans-serif !important;
}
@font-face {
  font-family:'PressStart2P';
  src: url('assets/fonts/PressStart2P-Regular.ttf') format('truetype');
  font-weight: 400;
}
</style>