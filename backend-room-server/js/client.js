"use strict";

const userName = (Math.random() + 1).toString(36).substring(7);
const roomName = prompt("Type room name!");
document.querySelector("#userName").innerHTML = userName;
document.querySelector("#roomName").innerHTML = roomName;

const socket = io.connect();

socket.emit("event", "data");

socket.on("event", (data) => {
  // logic
})