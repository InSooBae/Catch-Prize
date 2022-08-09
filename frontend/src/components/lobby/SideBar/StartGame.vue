<template>
  <el-button color="#00bd9d" type="info"  class="start-button" @click="startVisible = true">GAME START!
  </el-button>
  <el-dialog v-model="startVisible" :show-close="false" custom-class="start-modal">
    <template #header="{ titleId, titleClass }">
      <div class="my-header ">
        <h4 :id="titleId" :class="titleClass" style="text-align: center; padding-top: 5px; font-size: 1.3rem;">START
          YOUR GAME!</h4>
      </div>
    </template>
    <el-form class="start-form" :model="gameinfo">
      <el-form-item>
        <el-input placeholder="방 제목을 입력하세요." v-model="gameinfo.roomName" />
      </el-form-item>
      <el-form-item>
        <el-select placeholder="게임을 선택하세요." v-model="gameinfo.roomType">
          <el-option label="호불호 포커" value="HOBULHO" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select placeholder="참여인원을 선택하세요" v-model="gameinfo.maxParticipants" :disabled="!gameinfo.rommType">
          <el-option :label="4" value=4 />
          <el-option :label="6" value=6 />
        </el-select>
      </el-form-item>
      <el-button color="#00bd9d" type="info" class="start-button" @click="makeRoom">GAME START!</el-button>
    </el-form>
  </el-dialog>
</template>

<script setup>
import { reactive, ref } from 'vue'
// import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import { OpenVidu } from 'openvidu-browser';
import { onMounted, computed } from 'vue';
import { fetchRoomById } from '../../../util/api';

// const router = useRouter()
const store = useStore()

const startVisible = ref(false)
// const set_gameinfo = () => store.commit('SET_GAMEINFO')

const gameinfo = reactive({
  roomName: 'string',
  roomType: 'HOBULHO',
  maxParticipants: 6,
})

const cam = reactive({
	OV: undefined,
  roomId: '',
	session: computed(() => store.room.room.session),
	mainStreamManager: undefined,
	publisher: undefined,
	subscribers: [],
	mySessionId: computed(() => store.room.room.roomId),
	myUserName: 'Participant' + Math.floor(Math.random() * 100),
})

const makeRoom = () => {
  // router.push({ name: 'gameroom', params: {roomId: gameinfo.roomname} })
  const createRoom = () => store.dispatch('createRoom', gameinfo)
  console.log(gameinfo)
  console.log(cam)
  createRoom()
  // cam.session = store.state.room.room.data.session
  // console.log(store.state.room.room.data.session)
  // cam.roomId = store.state.room.room.data.roomId
  // console.log(store.state.room.room.data.roomId)
  // fetchRoomById(cam.roomId)
  // console.log(cam.roomId)
  // console.log(store.state.room.ovToken)
  // joinSession()
  console.log(cam)
  // joinSession()
  // gameinfo.roomName = ''
  // gameinfo.roomType = ''
  // gameinfo.maxParticipants = 6
  // startVisible.value = false
}
const joinSession = () => {
	cam.OV = new OpenVidu();
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
	getToken(cam.mySessionId).then(token => {
		cam.session.connect(token, { clientData: cam.myUserName })
			.then(() => {
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
	});
	window.addEventListener('beforeunload', leaveSession)
}
</script>