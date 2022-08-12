<template>
  <el-row id="elem" class="wait-container" style="height: calc(100vh - 80px);">
    <el-col :sm="15" class="hidden-md-and-down">
      <div :gutter="20"  class="people-container">
        <div class="person-container">
          <user-video :stream-manager="cam.publisher"></user-video>
          <el-button id="ready-btn" color="#626aef">Ready</el-button>
        </div>
        <div v-for="sub in cam.subscribers" class="person-container">
          <user-video  v-if="sub" :stream-manager="sub"></user-video>
          <el-button id="ready-btn-ready" color="#626aef">Ready</el-button>
        </div>
      </div>
    </el-col>
    <el-col :xs="24" :lg="9" style="height: calc(100vh - 125px);">
      <div class="chat-container">
        <div class="chat-view">
          <div v-for="person in 10" :key="person">
            <p>황태희 바보</p>
            <p>바보가 맞다</p>
          </div>
        </div>
        <div>
          <el-input v-model="chatdata" placeholder="대화를 입력하세요.">
            <template #suffix>
              <el-button color="#7608d3" type="info" class="add-button" @click="startVisible = true">
                <img src="@/assets/icons/person_add.svg" alt="add_friends">
              </el-button>
            </template>
          </el-input>
        </div>  
      </div>
      <div class>
        <el-button color="#7608d3" type="info" id="start-ready-button" ></el-button>
      </div>
    </el-col>
  </el-row>
</template>

<script setup>
import { ElMessage } from 'element-plus'
import { reactive, ref, onMounted, onBeforeUnmount, computed } from 'vue';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '../webrtc/UserVideo.vue';
import { fetchRoomById } from '../../util/api';

const route = useRoute()
const store = useStore()
const chatdata = ref('')
const roomId = route.params.roomId


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
  console.log(cam.myUserName)
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
  const roomId = sessionStorage.getItem('roomId')
  const ovdata = JSON.parse(sessionStorage.getItem('ovdata'))
  // 서버에 유저 삭제 요청
  store.dispatch('removeUser', { roomId, ovdata})
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
    window.removeEventListener('beforeunload',leaveSession);
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

onMounted(() => {
  const startReady = document.getElementById('start-ready-button');
  startReady.innerText = 'Ready!'
  store.commit('SET_ISWAIT', true)
})

// beforeunmount
onBeforeUnmount(() => {
  leaveSession()
})


</script>

<style>
.wait-container .people-container {
  /* layout */
    display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  /* BOX */
  min-width: 56rem;
  max-width: 38rem;
  min-height: 38rem;
  padding: 2.1%;
  margin: 1rem;
  /* background */
  background-color: rgba(255, 255, 255, 0.15);
  /* font */
  /* border */

}



.wait-container .person-container video{
  margin-bottom: 1rem;
  margin-right: 0.3rem;
}

.wait-container .chat-container {
  margin-bottom: 10px;
  padding: 10px;
  height: calc(100% - 90px);
  background-color: #0F1E33;
  border-radius: 10px;
}

.chat-container .chat-view {
  /* layout */
  /* BOX */
  height: calc(100% - 40px);
  overflow: auto;
  /* background */
  /* font */
  color: whitesmoke;
  /* border */
}

.chat-container .chat-view::-webkit-scrollbar {
  width: 5px;
}

.chat-container .chat-view::-webkit-scrollbar-thumb {
  /* layout */
  /* BOX */
  /* background */
  background-color: #262C3A;
  /* font */
  /* border */
  border-radius: 5px;
}

.chat-container .chat-view::-webkit-scrollbar-track {
  /* layout */
  /* BOX */
  /* background */
  background-color: transparent;
  /* font */
  /* border */
  border-radius: 5px;
}

.chat-container .el-input__wrapper {
  /* layout */
  padding: 4px 5px 4px 10px;
  /* BOX */
  /* background */
  background-color: transparent;
  /* font */
  /* border */
  box-shadow: 0 0 0 1px #00bd9d inset;
}

#start-ready-button {
  /* layout */
  height: 60px;
  /* BOX */
  width: 100%;
  /* background */
  /* font */
  font-family: "PressStart2P";
  font-size: medium;
  color: whitesmoke;
  /* border */
  border: none;
  border-radius: 10px;
}

#ready-btn {
  width: 98%;
}

#ready-btn-ready {
  width: 98%;
  background-color:#262C3A;
}
  /* layout */
  /* BOX */
  /* background */
  /* font */
  /* border */
</style>

