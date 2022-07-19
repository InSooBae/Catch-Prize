<script setup>
import StarBackground from '@/components/StarBackground.vue'
</script>

<template>
  <div>
    <StarBackground />
    <div>
      <div class="catch_prize">
        <b class="text_white text_catch">CATCH!</b>
        <b class="text_green">P</b>
        <b class="text_yello">R</b>
        <b class="text_green">IZE</b>
      </div>
      <div>
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
    </div>
  </div>
</template>

<style>
.catch_prize {
  font-family: "PressStart2P";
  font-size: 30px;
}
.text_catch {
  padding:0 15px 0 10px
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
</style>
