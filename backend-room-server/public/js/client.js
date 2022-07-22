"use strict";

const socket = io.connect();

const userName = (Math.random() + 1).toString(36).substring(7);
document.querySelector("#userName").innerHTML = userName;

const createRoomBtn = document.querySelector("#createRoomBtn");
const joinRoomBtn = document.querySelector("#joinRoomBtn");

createRoomBtn.addEventListener("click", (event) => {
  const roomName = document.querySelector("#createRoomInput").value;
  document.querySelector("#roomName").innerHTML = roomName;
  socket.emit("create-room-req", { userName, roomName });
});

socket.emit("event", "data");

socket.on("event", (data) => {
  // logic
})

socket.on("created-room-notify", (name) => {
  console.log(`${name} has joined the room!!!`);
});