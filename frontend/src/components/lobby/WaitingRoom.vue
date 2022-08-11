<template>
  <el-row id="elem" class="wait-container" style="height: calc(100vh - 80px);">
    <el-col :sm="15" class="hidden-md-and-down">
      <el-row :gutter="20" class="people-container">
        <el-col class="person-container" :lg="8" >
          <div>name-tag</div>
          <!-- webRTC uservideo -->
          <!-- <user-video v-for="player in players" :stream-manager="player" @click.native="updateMainVideoStreamManager(player)"/> -->
          <user-video :stream-manager="cam.publisher"></user-video>
          <user-video v-for="sub in cam.subscribers" :stream-manager="sub"></user-video>
        </el-col>
      </el-row>
      <!-- {{room.room}} -->
      {{roomId}}
      <!-- {{ov.token}} -->
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
import { reactive, ref, onMounted, onBeforeUnmount, computed } from 'vue';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '../webrtc/UserVideo.vue';
import { fetchRoomById } from '../../util/api';


const route = useRoute()
const store = useStore()
const chatdata = ref('')
// const players = computed(() => store.room)


const roomId = route.params.roomId
// const room = computed(() => store.state.room)

// const ov = computed(() => store.state.room.ov)
// store.dispatch('fetchRoom', roomId)
// .then(joinSession())
// const ovtoken = computed(() => store.getters.room.ov.token)



const cam = reactive({
	OV: undefined,
	session: undefined,
	mainStreamManager: undefined,
	publisher: undefined,
	subscribers: [],
	mySessionId: computed(() => store.state.room.sessionId),
	myUserName: computed(() => store.state.user.currentUser),
})

// const unSub = () => {
//   store.dispatch('closeSubscribe')
// }


// const joinSession = () => {
//   console.log(cam.sessions)
//   cam.OV = new OpenVidu();
//   cam.session = cam.OV.initSession();
//   console.log('init 세션', cam.session)
//   cam.session.on('streamCreated', ({ stream }) => {
//     console.log('첫번째 세션')
//     const subscriber = cam.session.subscribe(stream);
//     cam.subscribers.push(subscriber);
//   });
//   cam.session.on('streamDestroyed', ({ stream }) => {
//     const index = cam.subscribers.indexOf(stream.streamManager, 0);
//     if (index >= 0) {
//       cam.subscribers.splice(index, 1);
//     }
//   });
//   cam.session.on('exception', ({ exception }) => {
//     console.warn(exception);
//   });
//   cam.session.connect(ovtoken, { clientData: cam.myUserName })
//     .then(() => {
//       // --- Get your own camera stream with the desired properties ---
//       let publisher = cam.OV.initPublisher(undefined, {
//         audioSource: undefined, // The source of audio. If undefined default microphone
//         videoSource: undefined, // The source of video. If undefined default webcam
//         publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
//         publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
//         resolution: '200x100',  // The resolution of your video
//         frameRate: 30,			// The frame rate of your video
//         insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
//         mirror: false       	// Whether to mirror your local video or not
//       });
//       cam.mainStreamManager = publisher;
//       cam.publisher = publisher;
//       // --- Publish your stream ---
//       cam.session.publish(cam.publisher);
//     })
//     .catch(error => {
//       console.log('There was an error connecting to the session:', error.code, error.message);
//     });
//   const leaveSession = () => {
//     // --- Leave the session by calling 'disconnect' method over the Session object ---
//     if (cam.session) {
//       cam.session.disconnect();
//     }
//     cam.session = undefined;
//     cam.mainStreamManager = undefined;
//     cam.publisher = undefined;
//     cam.subscribers = [];
//     cam.OV = undefined;
//     window.removeEventListener('beforeunload', leaveSession)
// }
//   // window.addEventListener('beforeunload', leaveSession)
// }


const joinSession = () => {
	cam.OV = new OpenVidu();
	cam.session = cam.OV.initSession();
  console.log('session 생성',cam.session)

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
  
  getToken(roomId).then(ovtoken => {
  cam.session.connect(ovtoken, { clientData: cam.myUserName })
    .then(() => {
      console.log(ovtoken)
      console.log('토큰입니다')
      // --- Get your own camera stream with the desired properties ---
      let publisher = cam.OV.initPublisher(undefined, {
        audioSource: undefined, // The source of audio. If undefined default microphone
        videoSource: undefined, // The source of video. If undefined default webcam
        publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
        publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
        resolution: '640x480',  // The resolution of your video
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
	window.addEventListener('beforeunload', leaveSession)
}
const leaveSession = () => {
  // --- Leave the session by calling 'disconnect' method over the Session object ---
  store.dispatch('removeUser', roomId)
  if (cam.session) cam.session.disconnect();
  cam.session = undefined;
  cam.mainStreamManager = undefined;
  cam.publisher = undefined;
  cam.subscribers = [];
  cam.OV = undefined;

  window.removeEventListener('beforeunload',leaveSession);
}
const getToken = (roomId) => {
  // store.dispatch('fetchRoom', roomId)
  // const ovtoken = computed(() => store.state.room.ov.token)
  return new Promise((resolve, reject) => {
    fetchRoomById({ Authorization: `Bearer ${sessionStorage.getItem('token')}` }, roomId)
    .then(response => response.data)
    .then(data => resolve(data.token))
    .catch(error => reject(error.response))
    })

}    


joinSession()

onMounted(() => {
  const startReady = document.getElementById('start-ready-button');
  startReady.innerText = 'Ready!'
  store.commit('SET_ISWAIT', true)
  
})


</script>

<style>
.wait-container .people-container {
  padding-right: 20px;
}

.wait-container .person-container {
  margin-bottom: 20px;
}

.person-container .person-image {
  width: 100%;
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
</style>