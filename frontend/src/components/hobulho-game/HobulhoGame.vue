<template>
  <div>
    <el-container class="el-header-container">
      <el-header height="100px"><HeaderHome /></el-header>
    </el-container>
      <el-container class="el-body-container">
        <el-aside class="game-aside-container" width="470px"><LeftHome/></el-aside>
        <el-main class="game-main-container" ><RightHome :publisher="cam.publisher" :subscriber="cam.subscribers"/></el-main>
    </el-container>
  </div>
</template>

<script setup>
import { reactive, inject, onBeforeUnmount, computed } from "vue";
import { useRoute } from "vue-router";
import HeaderHome from "./header/HeaderHome.vue";
import LeftHome from "./main/LeftHome.vue";
import RightHome from "./main/PlayersHome.vue";

// 기본 값
const route = useRoute();
const myid = route.params.myid;
const roomid = route.params.roomid;
const token = { Authorization: `Bearer ${sessionStorage.getItem('token')}`}

// 게임변수
const $clientstate = inject("$clientstate");
const $hobulhoSocket = inject("$hobulhoSocket");
const $dataBox = inject("$dataBox");

$clientstate.myid = myid;
$clientstate.roomid = roomid;

$hobulhoSocket.emit("server-get-roomid", $clientstate.roomid);

function whichData(roomid) {
  for (let t = 0; t < $dataBox.length; t++) {
    if ($dataBox[t].controlstate.roomid === roomid) {
      return t;
    }
  }
}

//현재상태가 loading 일때 게임 시작 요청
const gameStart = () => {
  let boxnum = whichData($clientstate.roomid);
  if ($dataBox[boxnum].controlstate.gamestate === "loading") {
    setTimeout(() => {
      $hobulhoSocket.emit("hobulho-start-req", $clientstate.roomid);
    }, 3000);
  }
};

$hobulhoSocket.on("to-player", function (roomid, myid, item) {
  //VideoRotate, PitchVoice, Chroma
  console.log(roomid);
  console.log(myid);
  console.log(item);
});

$hobulhoSocket.on("game-start-ready", function () {
  if ($clientstate.roomid != "") {
    gameStart();
  }
});

$hobulhoSocket.on("whose-turn", function () {
  let boxnum = whichData($clientstate.roomid);

  if ($dataBox[boxnum].attackstate.attackerId === $clientstate.myid) {
    $clientstate.gamestate = "select";
  } else {
    $clientstate.gamestate = "turn";
  }
});
$hobulhoSocket.on("whose-attack", function () {
  let boxnum = whichData($clientstate.roomid);

  if ($dataBox[boxnum].attackstate.attackerId === $clientstate.myid) {
    $clientstate.gamestate = "attack";
  } else {
    $clientstate.gamestate = "turn";
  }
});
$hobulhoSocket.on("whose-declare", function () {
  let boxnum = whichData($clientstate.roomid);

  if ($dataBox[boxnum].attackstate.attackerId === $clientstate.myid) {
    $clientstate.gamestate = "declare";
  } else {
    $clientstate.gamestate = "declare-turn";
  }
});
$hobulhoSocket.on("whose-judge", function () {
  let boxnum = whichData($clientstate.roomid);

  if ($dataBox[boxnum].attackstate.defenderId === $clientstate.myid) {
    $clientstate.gamestate = "judge";
  } else {
    $clientstate.gamestate = "judge-turn";
  }
});


// Openvidu //

const cam = reactive({
	OV: undefined,
	session: undefined,
	mainStreamManager: undefined,
	publisher: undefined,
	subscribers: [],
	mySessionId: computed(() => store.state.room.sessionId),
	myUserName: computed(() => store.state.user.currentUser.username),
})

const joinSession = () => {
  console.log(token)
  console.log('ws connect')
  
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
  getToken(roomid).then(ovdata => {

  console.log(cam.myUserName)
  cam.session.connect(ovdata.token, { serverData: 'myname' })
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
  const roomid = sessionStorage.getItem('roomid')
  const ovdata = JSON.parse(sessionStorage.getItem('ovdata'))
  // 서버에 유저 삭제 요청
  store.dispatch('removeUser', { roomid, ovdata})
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

// roomid를 이용해서 ovtoken, userId 가져오는 요청 보내기
const getToken = (roomid) => {
  return new Promise((resolve, reject) => {
    fetchRoomById({ Authorization: `Bearer ${sessionStorage.getItem('token')}` }, roomid)
    .then(response => {
      resolve(response.data)
      })
    .catch(error => reject(error.response))
    })
}    

joinSession()

// beforeunmount
onBeforeUnmount(() => {
  leaveSession()
})


// Filter

//화면 뒤집기
const videoRotate = (manager) => {
  manager.stream.applyFilter("GStreamerFilter", { "command": "videoflip method=vertical-flip" })
}

// 크로마
const chroma = (manager) => {
  manager.stream.applyFilter("GStreamerFilter", { "command": 'chromahold target-r=0 target-g=0 target-b=255 tolerance=90' })
}

// 목소리 변조
const pitchVoice = (manager) => {
  manager.stream.applyFilter("GStreamerFilter", { "command": 'chromahold target-r=0 target-g=0 target-b=255 tolerance=90' })
}



const clickFilter = (me) => {
  me.subscribeToRemote(true);
  me.stream.applyFilter("GStreamerFilter", { "command": "videoflip method=vertical-flip" })
    .then(() => {
    console.log("Video filltered!");
    setTimeout(() => me.stream.removeFilter(), 20000)
  })
  .catch((error) => {
    console.error(error);
  });
}

</script>
<style scoped>
.el-header-container {
  height: 100px;
}
.el-body-container {
  height: 90%;
}
.el-main {
  padding: 0px;
}
.el-aside {
  padding: 0px;
  margin-left: 20px;
}
.el-header {
  height: 100px;
  padding-top: 20px;
  padding-bottom: 1%;
}
</style>
