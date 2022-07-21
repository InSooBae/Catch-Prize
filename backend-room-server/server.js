"use strict";

const SERVER_PORT = 3010;

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
  socket.on("event", (data) => {

  });
});
