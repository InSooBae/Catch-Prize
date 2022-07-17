<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
    <div>
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/signup">Signup</RouterLink>
        <RouterLink to="/lobby">Lobby</RouterLink>
        <RouterLink to="/game">Game</RouterLink>
      </nav>
    </div>
  </header>
  <RouterView />
</template>