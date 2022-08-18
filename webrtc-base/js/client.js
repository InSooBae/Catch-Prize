"use strict";

const STUN_URL = "stun:13.125.182.230:3478";
const TURN_URL = "turn:13.125.182.230:3478";
const TURN_USER = "berry";
const TURN_CREDENTIAL = "straw";

let isChannelReady = false;
let isInitiator = false;
let isStarted = false;
let localStream;
let pc;
let remoteStream;
let turnReady;

const pcConfig = {
  "iceServers": [
    {
      "urls": STUN_URL
    },
    {
      "urls": TURN_URL,
      "username": TURN_USER,
      "credential": TURN_CREDENTIAL
    }
  ]
};

const sdpConstraints = {
  offerToReceiveAudio: true,
  offerToReceiveVideo: true
};

let room = prompt("방 제목을 입력하세요: ");
if (!room) {
  room = "제목없음";
}

const socket = io.connect();
socket.emit("create or join", room);
console.log("Attempted to create or join room ", room);

socket.on("created", function(room) {
  console.log("Created room " + room);
  isInitiator = true;
});

socket.on("full", function(room) {
  console.log("Room " + room + " is full");
});

socket.on("join", function (room){
  console.log("Another peer made a request to join room " + room);
  console.log("This peer is the initiator of room " + room + "!");
  isChannelReady = true;
});

socket.on("joined", function(room) {
  console.log("joined: " + room);
  isChannelReady = true;
});

socket.on("log", function(array) {
  console.log.apply(console, array);
});

function sendMessage(message) {
  console.log("Client sending message: ", message);
  socket.emit("message", message);
}

socket.on("message", function(message) {
  console.log("Client received message: ", message);
  if (message === "got user media") {
    maybeStart();
  } else if (message.type === "offer") {
    if (!isInitiator && !isStarted) {
      maybeStart();
    }
    pc.setRemoteDescription(new RTCSessionDescription(message));
    doAnswer();
  } else if (message.type === "answer" && isStarted) {
    pc.setRemoteDescription(new RTCSessionDescription(message));
  } else if (message.type === "candidate" && isStarted) {
    const candidate = new RTCIceCandidate({
      sdpMLineIndex: message.label,
      candidate: message.candidate
    });
    pc.addIceCandidate(candidate);
  } else if (message === "bye" && isStarted) {
    handleRemoteHangup();
  }
});

const localVideo = document.querySelector("#localVideo");
const videos = document.querySelector("#videos");

navigator.mediaDevices.getUserMedia({
  audio: false,
  video: true
}).then(gotStream)
  .catch(function(e) {
    alert("getUserMedia() error: " + e.name);
  });

const constraints = {
  video: true
};

console.log("Getting user media with constraints", constraints);

window.onbeforeunload = function() {
  sendMessage("bye");
};

function gotStream(stream) {
  console.log("Adding local stream.");
  localStream = stream;
  localVideo.srcObject = stream;
  sendMessage("got user media");
  if (isInitiator) {
    maybeStart();
  }
}

function maybeStart() {
  if (!isStarted && typeof localStream !== "undefined" && isChannelReady) {
    createPeerConnection();
    isStarted = true;
    console.log("isInitiator", isInitiator);
    if (isInitiator) {
      doCall();
    }
  }
}

function createPeerConnection() {
  try {
    pc = new RTCPeerConnection(pcConfig);
    localStream.getTracks().forEach(track => {
      pc.addTrack(track, localStream);
    });
    pc.onicecandidate = handleIceCandidate;
    pc.ontrack = (event) => {
      const remoteStream = event.streams[0];
      console.log("remoteStream = event.streams[0]... = ???");
      console.log(remoteStream);
      createVideoEl().srcObject = remoteStream;
    };
    console.log("Created RTCPeerConnnection");
  } catch (e) {
    console.log("Failed to create PeerConnection, exception: " + e.message);
    alert("Cannot create RTCPeerConnection object.");
    return;
  }
}

function handleIceCandidate(event) {
  console.log("icecandidate event: ", event);
  if (event.candidate) {
    sendMessage({
      type: "candidate",
      label: event.candidate.sdpMLineIndex,
      id: event.candidate.sdpMid,
      candidate: event.candidate.candidate
    });
  } else {
    console.log("End of candidates.");
  }
}

function handleCreateOfferError(event) {
  console.log("createOffer() error: ", event);
}

function doCall() {
  console.log("Sending offer to peer");
  pc.createOffer(setLocalAndSendMessage, handleCreateOfferError);
}

function doAnswer() {
  console.log("Sending answer to peer");
  pc.createAnswer().then(
    setLocalAndSendMessage,
    onCreateSessionDescriptionError
  );
}

function setLocalAndSendMessage(sessionDescription) {
  pc.setLocalDescription(sessionDescription);
  console.log("setLocalAndSendMessage sending message", sessionDescription);
  sendMessage(sessionDescription);
}

function onCreateSessionDescriptionError(error) {
  trace("Failed to create session description: " + error.toString());
}

function handleRemoteStreamRemoved(event) {
  console.log("Remote stream removed. Event: ", event);
}

function hangup() {
  console.log("Hanging up.");
  stop();
  sendMessage("bye");
}

function handleRemoteHangup() {
  console.log("Session terminated.");
  stop();
  isInitiator = false;
}

function stop() {
  isStarted = false;
  pc.close();
  pc = null;
}

function createVideoEl() {
  const videoEl = document.createElement("video");
  videoEl.setAttribute("autoplay", true);
  videoEl.setAttribute("muted", true);
  videoEl.setAttribute("playsinline", true);
  videos.appendChild(videoEl);
  return videoEl;
}