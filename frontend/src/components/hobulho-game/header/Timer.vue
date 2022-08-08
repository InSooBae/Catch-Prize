<template>
  <div class="countdown">
    <div class="countdown-timer-values" ref="seconds">00</div>
  </div>
</template>

<script setup>
import { ref } from "vue";
const seconds = ref(null);
function stateTimer() {
  let timer = 30;
  let interval = setInterval(() => {
    if (timer === -1) {
      clearInterval(interval);
    } else {
      if (timer < 10) {
        seconds.value.textContent = "0" + timer;
      } else {
        seconds.value.textContent = timer;
      }
      timer--;
      console.log(timer);
    }
  }, 1000);
}
stateTimer();
// function startTimer() {
//   for (let t = 10; t >= 0; t--) {
//     setTimeout(() => {
//       seconds.value.textContent = t;
//     }, 1000);
//   }
//   return 1;
// }
// startTimer();
</script>

<style scoped>
.countdown-timer-values {
  font-size: 40px;
  font-family: "Galmuri9";
  /* color: #f2e536; */
  /* color: white; */
  /* color: #7608d3; */
  color: #00bd9d;
}
</style>
