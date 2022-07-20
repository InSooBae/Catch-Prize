<script setup>
import StarBackground from '@/components/StarBackground.vue'
import Game from '@/components/sample-game/SampleGame.vue'

import { ref } from 'vue'
const drawer = ref(false)
</script>

<template>
  <div class="common-layout">
    <StarBackground />
    <el-drawer v-model="drawer" title="I am the game" 
    :with-header="false"
    direction="ltr"
    size="55%"
    modal-class="game_modal">
      <Game/> 
    </el-drawer>
    <el-container>
      <el-header style="height: 20vh;">
      </el-header>
      <el-main>
          <el-row>
            <el-col :md="12" :lg="14" :xl="16" class="hidden-sm-and-down">
              <a @click="drawer = true">
                <img src="@/assets/logo.svg" width="360" alt="logo" style="opacity: 0.6; margin-left: 12vw; margin-top: 15vh;">
              </a>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="10" :xl="8" style="margin-top: 8vh;">
              <div class="catch_prize d-flex justify-content-center">
                <b class="text_white me-3">CATCH!</b>
                <b class="text_green">P</b>
                <b class="text_yello">R</b>
                <b class="text_green">IZE</b>
              </div>
              <div class="d-flex flex-column">
                <button type="button" class="login_button">
                  <img class="login_button_image" src="@/assets/login/google.png"/>
                </button>
                <button type="button" class="login_button">
                  <img class="login_button_image" src="@/assets/login/kakao.png"/>
                </button>
                <button type="button" class="login_button">
                  <img class="login_button_image" src="@/assets/login/naver.png"/>
                </button>
              </div>
            </el-col>
          </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<style>
.catch_prize {
  font-family: "PressStart2P";
  font-size: 30px;
}
.text_white {
  color: #ffffff;
}
.text_green {
  color: #00bd9d;
}
.text_yello {
  color: #f2e63c;
}
.login_button {
  padding: 0px;
  border-width: 0px;
  background:rgba(0,0,0, 0.0);
}
.login_button_image {
  width: 360px;
}
.login_button_image:hover {
  filter: brightness(0.7)
}
.el-drawer {
  background:rgba(0,0,0, 0.0);
}
</style>
