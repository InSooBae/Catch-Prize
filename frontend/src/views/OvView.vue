<template>
  <h1>
    개같은 OpenVidu 개같은
  </h1>
  <RouterView />
  <div class="people-container">
    <div v-for="n in 6" :key="n" class="person-container">
      <user-video :stream-manager="cam.publisher"></user-video>
    </div>
    <!-- <div v-for="sub in cam.subscribers" class="person-container">
      <user-video v-if="sub" :stream-manager="sub"></user-video>
    </div> -->
  </div>
</template>

<script setup>
import { reactive, computed } from 'vue';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';
import { OpenVidu } from 'openvidu-browser';
import { fetchRoomById } from '@/util/api';
import UserVideo from '@/components/webrtc/UserVideo.vue';

const route = useRoute()
const store = useStore()
const roomId = route.params.roomid

const cam = reactive({
  OV: undefined,
  session: undefined,
  mainStreamManager: undefined,
  publisher: undefined,
  subscribers: [],
  mySessionId: computed(() => store.state.room.sessionId),
  myUserName: computed(() => store.state.user.currentUser.username),
})

// const unSub = () => {
//   store.dispatch('closeSubscribe')
// }



const joinSession = () => {
  // console.log(token)
  // console.log('ws connect')

  cam.OV = new OpenVidu();
  // session을 사용할 수 있게함
  cam.session = cam.OV.initSession();

  cam.session.on('streamCreated', ({ stream }) => {
    const subscriber = cam.session.subscribe(stream);
    cam.subscribers.push(subscriber);
  });
  cam.session.on('streamDestroyed', ({ stream }) => {
    const index = cam.subscribers.indexOf(stream.streamManager, 0);
    if (index >= 0) {
      cam.subscribers.splice(index, 1);
    }
  });
  cam.session.on('exception', ({ exception }) => {
    console.warn(exception);
  });

  // getToken에서 ovdata를 반환
  // ovdata.token을 이용해서 session에 연결할 수 있음
  getToken(roomId).then(ovdata => {

    // console.log(cam.myUserName)
    cam.session.connect(ovdata.token)
      .then(() => {
        store.commit('SET_OV', ovdata)
        sessionStorage.setItem('ovdata', JSON.stringify(ovdata))
        // --- Get your own camera stream with the desired properties ---
        let publisher = cam.OV.initPublisher(undefined, {
          audioSource: undefined, // The source of audio. If undefined default microphone
          videoSource: undefined, // The source of video. If undefined default webcam
          publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
          publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
          resolution: '280x150',  // Cam Size
          frameRate: 30,			// The frame rate of your video
          insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
          mirror: false       	// Whether to mirror your local video or not
        });
        cam.mainStreamManager = publisher;
        cam.publisher = publisher;
        // --- Publish your stream ---
        cam.session.publish(cam.publisher);
      })
      .catch(error => {
        console.log('There was an error connecting to the session:', error.code, error.message);
      });
  })
  // joinSession을 하면서 beforeunload 
  window.addEventListener('beforeunload', leaveSession)
}

// 세션 퇴장
const leaveSession = () => {
  const ovdata = JSON.parse(sessionStorage.getItem('ovdata'))
  // 서버에 유저 삭제 요청
  console.log(ovdata)
  store.dispatch('removeUser', { roomId, ovdata })
    .then(
      ElMessage({
        type: 'message',
        message: '방에서 퇴장하셨습니다.'
      })
    )
  // 초기화 및 이벤트 삭제
  if (cam.session) cam.session.disconnect();
  cam.session = undefined;
  cam.mainStreamManager = undefined;
  cam.publisher = undefined;
  cam.subscribers = [];
  cam.OV = undefined;
  window.removeEventListener('beforeunload', leaveSession);
}

// roomId를 이용해서 ovtoken, userId 가져오는 요청 보내기
const getToken = (roomId) => {
  return new Promise((resolve, reject) => {
    fetchRoomById({ Authorization: `Bearer ${sessionStorage.getItem('token')}` }, roomId)
      .then(response => {
        resolve(response.data)
      })
      .catch(error => reject(error.response))
  })
}

joinSession()
</script>

<style>
.people-container {
  position: absolute;
  top: 175px;
  left: 580px;
}

.person-container div video {
  width: 180px;
}
</style>