<template>
  <StarBackground />
  <el-row align="middle" class="login-layout">
    <el-col :span="24">
      <div class="catch_prize d-flex justify-content-center">
        <p class="text-white text-animation">C</p>
        <p class="text-white text-animation" style="letter-spacing:-6px;">A</p>
        <p class="text-white text-animation" style="letter-spacing:-2px;">T</p>
        <p class="text-white text-animation">C</p>
        <p class="text-white text-animation" style="letter-spacing:-5px;">H</p>
        <p class="text-white text-animation me-3">!</p>
        <p class="text-green text-animation">P</p>
        <p class="text-yello text-animation blink" @click="drawer = true" style="letter-spacing:-5px; cursor: pointer;">
          R</p>
        <p class="text-green text-animation">I</p>
        <p class="text-green text-animation">Z</p>
        <p class="text-green text-animation">E</p>
      </div>
      <div class="d-flex flex-column">
        <button type="button" class="login_button">
          <img class="login_button_image" src="@/assets/login/google.png" alt="login with google"
            @click="loginPopup('google')" />
        </button>
        <button type="button" class="login_button">
          <img class="login_button_image" src="@/assets/login/kakao.png" alt="login with kakao"
            @click="loginPopup('kakao')" />
        </button>
        <button type="button" class="login_button">
          <img class="login_button_image" src="@/assets/login/naver.png" alt="login with naver"
            @click="loginPopup('naver')" />
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
const option = 'menubar=no, status=no, toolbar=no'

const loginPopup = (url) => {
  window.open(`http://localhost:3000/login/${url}`, 'window', option);
}

</script>

<style>
.login-layout {
  height: 100vh;
}

@keyframes size-effect {
  0% {
    transform: scale(0.0) rotate(-0.01turn);
  }

  50% {
    transform: scale(1.3) rotate(0.001turn);
  }

  100% {
    transform: scale(1);
  }
}

.catch_prize {
  font-family: "PressStart2P";
  font-size: 48px;
  margin: 20px 0;
  animation-name: size-effect;
  animation-duration: 2s;
  animation-iteration-count: 1;
}

.login_button {
  padding: 0px;
  border-width: 0px;
  background: rgba(0, 0, 0, 0.0);
}

.login_button_image {
  cursor: pointer;
  width: 380px;
  opacity: 0.9;
}

.login_button_image:hover {
  opacity: 1;
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
  transition: all ease 0.3s 0s;
  vertical-align: bottom;
  padding-top: 15px;
}

.text-animation:hover {
  padding: 0 1px;
  padding-bottom: 15px;
}

@keyframes blink-effect {
  10% {
    opacity: 1.0;
  }

  25% {
    opacity: 0.05;
  }

  40% {
    opacity: 1.0;
  }
}

.blink {
  animation-name: blink-effect;
  animation-duration: 2.5s;
  animation-delay: 1.5s;
  animation-iteration-count: infinite;
}

@media (pointer:coarse) {
  .catch_prize {
    font-size: 28px;
    margin: 10px 0;
  }

  .login_button_image {
    width: 290px;
  }
}
</style>
