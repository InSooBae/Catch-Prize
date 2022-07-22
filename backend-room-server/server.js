"use strict";

const SERVER_PORT = 3010;

const base64url = require("base64url");
const { randomBytes } = require("crypto");
const { readFileSync } = require("fs");
const { createServer } = require("https");
const { Server:SocketServer } = require("socket.io");
const { Server:nodeStaticServer } = require("node-static");

const options = {
	key: readFileSync("./private.pem"),
	cert: readFileSync("./public.pem")
};

const fileServer = new nodeStaticServer("./public");
const httpsServer = createServer(options, (req, res) => {
  if (req.method === "OPTIONS") {
    res.writeHead(204, headers);
    res.end();
    return;
  }

  res.statusCode = 200;
  res.setHeader("Access-Control-Allow-Origin", "*");
  res.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET");
  res.setHeader("Access-Control-Max-Age", 2592000); // 30 days
  res.setHeader("Content-Type", "text/html");

  req.addListener("end", () => {
    fileServer.serve(req, res);
  }).resume();
}).listen(SERVER_PORT);
const io = new SocketServer(httpsServer);

const rooms = {};

io.sockets.on("connection", function(socket) {
  socket.on("create-room-req", ({ userName, roomName }) => {
    const roomId = generateRandomUrlSafeString(20);
    const users = new Set(); users.add(userName);
    rooms[roomId] = {
      roomName,
      creator: userName,
      created: Date.now(),
      users
    };
    socket.join(roomId);
    console.log(rooms);
    io.to(roomId).emit("created-room-notify", userName);
    console.log("userName: ", userName, "roomName: ", roomName);
  });
});

function generateRandomUrlSafeString(len) {
  return base64url(randomBytes(len));
}