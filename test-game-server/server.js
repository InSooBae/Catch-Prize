var app = require("express")();
var server = require("http").createServer(app);
var io = require("socket.io")(server);

//시작할때 보내줄 데이터
const state = {
  response: {
    success: "false",
    error: {
      code: "7",
      message: "More players are required",
    },
  },
  gamestate: "loading",
  deathplayer: "",
  winner: "",
  players: [
    {
      playerId: "player1",
      cards: {
        hand: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
        remain: 8,
        board: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
      },
      isAlive: true,
    },
    {
      playerId: "player2",
      cards: {
        hand: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
        remain: 8,
        board: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
      },
      isAlive: true,
    },
    {
      playerId: "player3",
      cards: {
        hand: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
        remain: 8,
        board: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
      },
      isAlive: true,
    },
    {
      playerId: "player4",
      cards: {
        hand: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
        remain: 8,
        board: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
      },
      isAlive: true,
    },
    {
      playerId: "player5",
      cards: {
        hand: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
        remain: 8,
        board: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
      },
      isAlive: true,
    },
    {
      playerId: "player6",
      cards: {
        hand: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
        remain: 8,
        board: {
          cake: 0,
          durian: 0,
          eggplant: 0,
          insect: 0,
          mint: 0,
          pizza: 0,
        },
      },
      isAlive: true,
    },
  ],
};
// 공격하고 판단후 보내는 데이터
const attackstate = {
  attackerId: "",
  defenderId: "",
  selectedcard: "",
  declaredcard: "",
  declaration: true,
};
//6종류 10장씩의 카드를 랜덤으로 8장씩 분배
function cardsuffle() {
  const cardlist = ["cake", "durian", "eggplant", "insect", "mint", "pizza"];
  const list = [];
  for (let t = 0; t < 6; t++) {
    for (let i = 0; i < 10; i++) {
      list.push(cardlist[t]);
    }
  }
  list.sort(() => Math.random() - 0.5);
  // console.log(list);
  let cnt = 0;
  for (let t = 0; t < 6; t++) {
    for (let i = 0; i < 8; i++) {
      if (list[cnt] === "cake") state.players[t].cards.hand.cake++;
      if (list[cnt] === "durian") state.players[t].cards.hand.durian++;
      if (list[cnt] === "eggplant") state.players[t].cards.hand.eggplant++;
      if (list[cnt] === "insect") state.players[t].cards.hand.insect++;
      if (list[cnt] === "mint") state.players[t].cards.hand.mint++;
      if (list[cnt] === "pizza") state.players[t].cards.hand.pizza++;
      cnt++;
    }
  }
  // console.log(state.players[0].cards);
}

function checkdeclaration() {
  console.log(attackstate.selectedcard);
  console.log(attackstate.declaredcard);
  if (attackstate.selectedcard == attackstate.declaredcard) {
    attackstate.declaration = true;
    console.log(attackstate.declaration);
  } else if (attackstate.selectedcard != attackstate.declaredcard) {
    attackstate.declaration = false;
  }
}

//방어 성공
function defendSuccess() {
  for (let t = 0; t < 6; t++) {
    if (state.players[t].playerId === attackstate.attackerId) {
      if (attackstate.selectedcard === "cake")
        state.players[t].cards.board.cake++;
      if (attackstate.selectedcard === "durian")
        state.players[t].cards.board.durian++;
      if (attackstate.selectedcard === "eggplant")
        state.players[t].cards.board.eggplant++;
      if (attackstate.selectedcard === "insect")
        state.players[t].cards.board.insect++;
      if (attackstate.selectedcard === "mint")
        state.players[t].cards.board.mint++;
      if (attackstate.selectedcard === "pizza")
        state.players[t].cards.board.pizza++;
    }
  }
  if (!checkDeath(attackstate.attackerId)) {
    attackstate.attackerId = attackstate.attackerId;
  }
}
//방어 실패
function defendFail() {
  for (let t = 0; t < 6; t++) {
    if (state.players[t].playerId === attackstate.defenderId) {
      if (attackstate.selectedcard === "cake")
        state.players[t].cards.board.cake++;
      if (attackstate.selectedcard === "durian")
        state.players[t].cards.board.durian++;
      if (attackstate.selectedcard === "eggplant")
        state.players[t].cards.board.eggplant++;
      if (attackstate.selectedcard === "insect")
        state.players[t].cards.board.insect++;
      if (attackstate.selectedcard === "mint")
        state.players[t].cards.board.mint++;
      if (attackstate.selectedcard === "pizza")
        state.players[t].cards.board.pizza++;
    }
  }
  if (!checkDeath(attackstate.defenderId)) {
    attackstate.attackerId = attackstate.defenderId;
  }
}
function checkDeath(player) {
  for (let t = 0; t < 6; t++) {
    if (player === state.players[t].playerId) {
      if (
        state.players[t].cards.board.cake >= 1 ||
        state.players[t].cards.board.durian >= 1 ||
        state.players[t].cards.board.eggplant >= 1 ||
        state.players[t].cards.board.insect >= 1 ||
        state.players[t].cards.board.mint >= 1 ||
        state.players[t].cards.board.pizza >= 1
      ) {
        state.players[t].isAlive = false;
        state.deathplayer = player;
        state.gamestate = "death";
        if (player === attackstate.attackerId) {
          attackstate.attackerId = attackstate.defenderId;
        } else if (player === attackstate.defenderId) {
          attackstate.attackerId = attackstate.attackerId;
        }
        return 1;
      }
    }
  }
  return 0;
}
//우승자 확인
function checkwin() {
  let cnt = 0;
  for (let t = 0; t < 6; t++) {
    if (state.players[t].isAlive === false) {
      cnt++;
    }
  }
  if (cnt === 5) {
    for (let t = 0; t < 6; t++) {
      if (state.players[t].isAlive === true) {
        state.winner = state.players[t].playerId;
      }
    }
    return 1;
  }
  return 0;
}

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
// var roomSocket = io.of("/roomSocket");

//connection event handler
hobulhoSocket.on("connection", function (socket) {
  console.log("hobulho connected: " + socket);

  //게임 시작 요청이 들어오면
  socket.on("hobulho-start-req", function (data) {
    cardsuffle();
    if (state.gamestate === "loading") state.gamestate = "start";
    //state를 보냄

    //state 보내기
    socket.emit("data-refresh", state);
    //카드 세팅 Mycards.vue
    socket.emit("hobulho-start-card");
    //데이터 뿌리기 HobulhoGame.vue
    //첫공격  PlayersHome.vue
    socket.emit("first-attack");
  });

  socket.on("select-ready-req", function () {
    //attacker 저장
    attackstate.attackerId = state.players[0].playerId;
    //gamestate 변경
    state.gamestate = "select";
    //state 보내기

    socket.emit("attackdata-refresh", state);
    socket.emit("data-refresh", state);
  });

  socket.on("card-click", function (cardname, playerid) {
    //선택된 카드를 저장
    attackstate.selectedcard = cardname;
    console.log(cardname + playerid);
    for (let t = 0; t < 6; t++) {
      if (state.players[t].playerId === playerid) {
        state.players[t].cards.remain--;
        if (cardname === "cake") state.players[t].cards.hand.cake--;
        if (cardname === "durian") state.players[t].cards.hand.durian--;
        if (cardname === "eggplant") state.players[t].cards.hand.eggplant--;
        if (cardname === "insect") state.players[t].cards.hand.insect--;
        if (cardname === "mint") state.players[t].cards.hand.mint--;
        if (cardname === "pizza") state.players[t].cards.hand.pizza--;
      }
    }
    //카드가 선택되고 gamestate는 attack으로 변경
    state.gamestate = "attack";

    //state 보내기
    socket.emit("data-refresh", state);
    //Mycards.vue PlayersHome.vue
    socket.emit("players-refresh");
    // 클라이언트에게 메시지를 전송한다
  });

  //공격할 플레이어를 클릭했을때
  socket.on("player-click", function (defender) {
    attackstate.defenderId = defender;
    state.gamestate = "declare";

    //데이터 뿌리기
    socket.emit("data-refresh", state);
  });

  //뭐라고 할지 클릭했을때
  socket.on("declare-click", function (data) {
    attackstate.declaredcard = data;
    state.gamestate = "judge";
    //카드와 주장이 일치하는지 판단
    checkdeclaration();

    //데이터 뿌리기
    socket.emit("data-refresh", state);
    //attack 데이터 뿌리기
  });

  //방어자가 judge 선택
  socket.on("judge-selected", function (data) {
    //방어성공
    if (data === attackstate.declaration) {
      state.gamestate = "success";
      socket.emit("data-refresh", state);
      defendSuccess();
      //카드를 먹은 사람이 죽었을 경우
      if (checkDeath(attackstate.attackerId)) {
        setTimeout(() => {
          socket.emit("data-refresh", state);
          socket.emit("players-refresh");
        }, 3000);
        if (checkwin()) {
          setTimeout(() => {
            state.gamestate = "win";
            socket.emit("data-refresh", state);
          }, 6000);
        } else {
          setTimeout(() => {
            state.gamestate = "turn";
            socket.emit("data-refresh", state);
          }, 6000);
          setTimeout(() => {
            state.gamestate = "select";
            socket.emit("data-refresh", state);
          }, 9000);
        }
      } else {
        setTimeout(() => {
          state.gamestate = "turn";
          socket.emit("data-refresh", state);
        }, 3000);
        setTimeout(() => {
          state.gamestate = "select";
          socket.emit("data-refresh", state);
        }, 6000);
      }
      //방어 실패
    } else {
      state.gamestate = "fail";
      socket.emit("data-refresh", state);
      defendFail();
      //카드를 먹은 사람이 죽었을 경우
      if (checkDeath(attackstate.defenderId)) {
        setTimeout(() => {
          socket.emit("data-refresh", state);
          socket.emit("players-refresh");
        }, 3000);
        if (checkwin()) {
          setTimeout(() => {
            state.gamestate = "win";
            socket.emit("data-refresh", state);
          }, 6000);
        } else {
          setTimeout(() => {
            state.gamestate = "turn";
            socket.emit("data-refresh", state);
          }, 6000);
          setTimeout(() => {
            state.gamestate = "select";
            socket.emit("data-refresh", state);
          }, 9000);
        }
      } else {
        setTimeout(() => {
          state.gamestate = "turn";
          socket.emit("data-refresh", state);
        }, 3000);
        setTimeout(() => {
          state.gamestate = "select";
          socket.emit("data-refresh", state);
        }, 6000);
      }
    }
    //플레이어창 새로고침
    //다음턴
    socket.emit("attackdata-refresh", attackstate);
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
