<script setup>
import StarBackground from '@/components/StarBackground.vue'
import Game from '@/components/sample-game/SampleGame.vue'

import { ref } from 'vue'
const drawer = ref(false)
</script>

<template>
  <StarBackground />
  <el-row align="middle" class="login-layout">
    <el-col :md="12" class="hidden-sm-and-down">
      <a @click="drawer = true">
        <img src="@/assets/logo.svg" class="login-logo" alt="logo">
      </a>
    </el-col>
    <el-col :sm="24" :md="12">
      <div class="catch_prize d-flex justify-content-center">
        <b class="text-white me-3">CATCH!</b>
        <b class="text-green">P</b>
        <b class="text-yello">R</b>
        <b class="text-green">IZE</b>
      </div>
      <div class="d-flex flex-column">
        <button type="button" class="login_button">
          <img class="login_button_image" src="@/assets/login/google.png" />
        </button>
        <button type="button" class="login_button">
          <img class="login_button_image" src="@/assets/login/kakao.png" />
        </button>
        <button type="button" class="login_button">
          <img class="login_button_image" src="@/assets/login/naver.png" />
        </button>
      </div>
    </el-col>
  </el-row>
  <el-drawer v-model="drawer" title="I am the game" :with-header="false" direction="ltr" size="55%"
    modal-class="game_modal">
    <Game />
  </el-drawer>
</template>

<style>
.login-layout {
  height: 100vh;
}

.login-logo {
  margin: 0 0 0 calc(27vw - 230px);
  width: 400px;
  opacity: 0.6;
  cursor:pointer;
}

.catch_prize {
  font-family: "PressStart2P";
  font-size: 30px;
}

.login_button {
  padding: 0px;
  border-width: 0px;
  background: rgba(0, 0, 0, 0.0);
}

.login_button_image {
  width: 360px;
  cursor:pointer;
}

.login_button_image:active {
  filter: brightness(0.7)
}
  
.el-drawer {
  background: rgba(0, 0, 0, 0.0);
}
</style>
