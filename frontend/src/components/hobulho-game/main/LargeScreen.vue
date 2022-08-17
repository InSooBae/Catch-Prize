<template>
  <div>
    <img class="large-face-cam" src="../assets/Person.png" alt="비디오" />
    <p>
      {{ $clientstate.largeScreen }}
      <UserVideo :stream-manager="largeScreen.streamManager"></UserVideo>
    </p>
  </div>
</template>

<script setup>
import { inject, reactive, ref } from "vue";
import UserVideo from "../../webrtc/UserVideo.vue";
const $clientstate = inject("$clientstate");

const largeScreen = reactive({
  streamManager: publisher,
  streamManagerId: ''
  })

const props = defineProps({
  publisher,
  subscribers,
})

const updateMainVideoStreamManage = (stream) => {
    if (largeScreen.streamManager === stream) return;
    largeScreen.streamManager = stream;
  }

//$client.largeScreen
//클릭한 비디오 닉네임
</script>

<style>
/* .large-face-cam {
  width: 100%;
  height: 100%;
} */
p {
  color: white;
}
</style>
