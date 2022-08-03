var app = require("express")();
var server = require("http").createServer(app);
var io = require("socket.io")(server);

//setting cors
app.all("/*", function (req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "X-Requested-With");
  next();
});

app.get("/", function (req, res) {
  res.sendFile("Hellow Chating App Server");
});

var hobulhoSocket = io.of("/hobulhoSocket");

//connection event handler
hobulhoSocket.on("connection", function (socket) {
  console.log("hobulho connected: " + socket);

  socket.on("card-click", function (data) {
    console.log(data + "cliecked");

    socket.emit("card-select", data);
    // 클라이언트에게 메시지를 전송한다
  });

  socket.on("declare-click", function (data) {
    console.log(data + "clicked");
  });
  //판단성공 공격실패
  socket.on("judge-success", function (data) {
    console.log(data + "attaker++");
    socket.emit("attack-fail", data);
  });
  //판단실패 공격성공
  socket.on("judge-fail", function (data) {
    console.log(data + "defender++");
    socket.emit("attack-success", data);
  });
});

server.listen(8081, function () {
  console.log("socket io server listening on port 8080");
});
