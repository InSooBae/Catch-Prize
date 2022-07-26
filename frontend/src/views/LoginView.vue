<template>
  <StarBackground />
  <el-row align="middle" class="login-layout">
    <el-col :md="12" class="hidden-sm-and-down d-flex justify-content-center">
      <img src="@/assets/logo.svg" class="login-logo" alt="logo" @click="drawer = true">
    </el-col>
    <el-col :sm="24" :md="12">
      <div class="catch_prize d-flex justify-content-center">
        <p class="text-white text-animation">C</p>
        <p class="text-white text-animation" style="letter-spacing:-6px;">A</p>
        <p class="text-white text-animation" style="letter-spacing:-2px;">T</p>
        <p class="text-white text-animation">C</p>
        <p class="text-white text-animation" style="letter-spacing:-5px;">H</p>
        <p class="text-white text-animation me-3">!</p>
        <p class="text-green text-animation">P</p>
        <p class="text-yello text-animation" style="letter-spacing:-5px;">R</p>
        <p class="text-green text-animation">I</p>
        <p class="text-green text-animation">Z</p>
        <p class="text-green text-animation">E</p>
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
  <el-drawer v-model="drawer" title="I am the game" :with-header="false" direction="ltr" size="840px"
    custom-class="game-modal">
    <Game />
  </el-drawer>
</template>

<script setup>
import StarBackground from '@/components/StarBackground.vue'
import Game from '@/components/sample-game/SampleGame.vue'

import { ref } from 'vue'

const drawer = ref(false)



</script>

<style>
.login-layout {
  height: 100vh;
}

.login-logo {
  margin: 0 0 0 10px;
  width: min(40vw, 800px);
  opacity: 0.6;
  cursor: pointer;
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
  cursor: pointer;
}

.login_button_image:active {
  filter: brightness(0.7)
}

.el-drawer {
  background: rgba(0, 0, 0, 0.0);
}

.game-modal .el-drawer__body {
  padding-top: calc(50vh - 320px);
}

.text-animation {
  font-size: 43.5px;
  transition: all ease 0.3s 0s;
}

.text-animation:hover {
  transform: translate(0px, -30px);
}
</style>
