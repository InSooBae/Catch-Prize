"use strict";

const SERVER_PORT = 3001;
const ROOM_SIZE = 4;

const os = require("os");
const https = require("https");
const fs = require("fs");
const nodeStatic = require("node-static");
const socketIO = require("socket.io");
const fileServer = new(nodeStatic.Server)();
const options = {
	key: fs.readFileSync("./private.pem"),
	cert: fs.readFileSync("./public.pem")
};

const app = https.createServer(options, function(req, res) {
  res.setHeader("Access-Control-Allow-Origin", "*");
  res.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET");
  res.setHeader("Access-Control-Max-Age", 2592000); // 30 days

  if (req.method === "OPTIONS") {
    res.writeHead(204, headers);
    res.end();
    return;
  }

  fileServer.serve(req, res);
}).listen(SERVER_PORT);

const io = socketIO.listen(app);
io.sockets.on("connection", function(socket) {

  socket.on("message", function(message) {
    log("Message from client: ", message);
    socket.broadcast.emit("message", message);
  });

  socket.on("create or join", function(room) {
    log("Received request to create or join room " + room);

    const clientsInRoom = io.sockets.adapter.rooms[room];
    const numClients = clientsInRoom ? Object.keys(clientsInRoom.sockets).length : 0;
    log("Room " + room + " now has " + numClients + " client(s)");

    if (numClients === 0) {
      socket.join(room);
      log("Client ID " + socket.id + " created room " + room);
      socket.emit("created", room, socket.id);

    } else if (numClients < ROOM_SIZE) {
      log("Client ID " + socket.id + " joined room " + room);
      io.sockets.in(room).emit("join", room);
      socket.join(room);
      socket.emit("joined", room, socket.id);
      io.sockets.in(room).emit("ready");
    } else {
      socket.emit("full", room);
    }
  });

  socket.on("ipaddr", function() {
    const ifaces = os.networkInterfaces();
    for (const dev in ifaces) {
      ifaces[dev].forEach(function(details) {
        if (details.family === "IPv4" && details.address !== "127.0.0.1") {
          socket.emit("ipaddr", details.address);
        }
      });
    }
  });

  socket.on("bye", function() {
    console.log("Received BYE");
  });

  function log() {
    const array = ["Message from server: "];
    array.push.apply(array, arguments);
    socket.emit("log", array);
  }

});
