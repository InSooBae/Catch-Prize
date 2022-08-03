<template>
  <div v-if="$hobulhostate.gamestate === 'declare'">
    <AttackCardDeclare />
  </div>
  <div v-else-if="$hobulhostate.gamestate === 'judge'">
    <DefendJudge />
  </div>
  <div v-else class="players-container">
    <div class="left-grid">
      <div class="left-grid1"><Player :player="playersList[0]" /></div>
      <div class="left-grid2">
        <Player :player="playersList[1]" />
      </div>
      <div class="left-grid3"><Player :player="playersList[2]" /></div>
    </div>
    <div class="right-grid">
      <div class="right-grid1"><Player :player="playersList[3]" /></div>
      <div class="right-grid2"><Player :player="playersList[4]" /></div>
      <div class="right-grid3"><Player :player="playersList[5]" /></div>
    </div>
  </div>
</template>

<script setup>
import Player from "./Player.vue";
import AttackCardDeclare from "../select/AttackCardDeclare.vue";
import DefendJudge from "../select/DefendJudge.vue";

import { reactive, toRefs, inject, ref } from "vue";

const $hobulhostate = inject("$hobulhostate");
const $hobulhoSocket = inject("$hobulhoSocket");
const nowstate = $hobulhostate.gamestate;

// 받아올 데이터
const players = reactive({
  playersList: [
    {
      name: "player1",
      isDeath: false,
      fieldlist: [0, 0, 0, 0, 0, 0],
      remain: 8,
    },
    {
      name: "player2",
      isDeath: false,
      fieldlist: [0, 0, 0, 0, 0, 0],
      remain: 8,
    },
    {
      name: "player3",
      isDeath: false,
      fieldlist: [0, 0, 0, 0, 0, 0],
      remain: 8,
    },
    {
      name: "player4",
      isDeath: false,
      fieldlist: [0, 0, 0, 0, 0, 0],
      remain: 8,
    },
    {
      name: "player5",
      isDeath: false,
      fieldlist: [0, 0, 0, 0, 0, 0],
      remain: 8,
    },
    {
      name: "player6",
      isDeath: false,
      fieldlist: [0, 0, 0, 0, 0, 0],
      remain: 8,
    },
  ],
});

const { playersList } = toRefs(players);

//처음 게임이 시작하고 첫 공격자 함수
const firstattacker = () => {
  if (nowstate === "start") {
    $hobulhostate.attacker = players.playersList[0].name;
    setTimeout(() => {
      $hobulhostate.gamestate = "select";
    }, 3000);
  }
};
firstattacker();
function checkDeath(player) {
  for (let t = 0; t < 6; t++) {
    if (player === players.playersList[t].name) {
      for (let i = 0; i < 6; i++) {
        if (players.playersList[t].fieldlist[i] >= 1) {
          players.playersList[t].isDeath = true;
          $hobulhostate.deathplayer = player;
          $hobulhostate.gamestate = "death";
          if (player === $hobulhostate.attacker) {
            $hobulhostate.attacker = $hobulhostate.defender;
          } else if (player === $hobulhostate.defender) {
            $hobulhostate.attacker = $hobulhostate.attacker;
          }
          return 1;
        }
      }
    }
  }
  return 0;
}
function checkwin() {
  let cnt = 0;
  for (let t = 0; t < 6; t++) {
    if (players.playersList[t].isDeath === true) {
      cnt++;
    }
  }
  if (cnt === 5) {
    for (let t = 0; t < 6; t++) {
      if (players.playersList[t].isDeath === false) {
        $hobulhostate.winner = players.playersList[t].name;
        $hobulhostate.gamestate = "win";
      }
    }
    return 1;
  }
  return 0;
}

$hobulhoSocket.on("attack-success", (data) => {
  console.log("success" + data);
  for (var t = 0; t < 6; t++) {
    if (players.playersList[t].name === $hobulhostate.defender) {
      players.playersList[t].fieldlist[$hobulhostate.selectedcardnum]++;
    }
  }
  if (!checkDeath($hobulhostate.defender)) {
    $hobulhostate.attacker = $hobulhostate.defender;
  }
  if (!checkwin()) {
    setTimeout(() => {
      $hobulhostate.gamestate = "turn";
    }, 3000);
    setTimeout(() => {
      $hobulhostate.gamestate = "select";
      console.log($hobulhostate.gamestate);
    }, 6000);
  }
});
$hobulhoSocket.on("attack-fail", (data) => {
  console.log("fail" + data);
  for (var t = 0; t < 6; t++) {
    if (players.playersList[t].name === $hobulhostate.attacker) {
      players.playersList[t].fieldlist[$hobulhostate.selectedcardnum]++;
    }
  }
  checkDeath($hobulhostate.attacker);
  if (!checkwin()) {
    setTimeout(() => {
      $hobulhostate.gamestate = "turn";
    }, 3000);
    setTimeout(() => {
      $hobulhostate.gamestate = "select";
      console.log($hobulhostate.gamestate);
    }, 6000);
  }
});
</script>

<style>
.players-container {
  /* border: 1px solid white; */
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
  height: 87%;
}
.left-grid {
  width: 50%;
  height: 100%;
  padding-right: 10px;
}
.right-grid {
  width: 50%;
  height: 100%;
  padding-right: 10px;
}
.left-grid1 {
  height: 27vh;
  margin-bottom: 5px;
}
.left-grid2 {
  height: 27vh;
  margin-bottom: 5px;
}
.left-grid3 {
  height: 27vh;
}
.right-grid1 {
  height: 27vh;
  margin-bottom: 5px;
}
.right-grid2 {
  height: 27vh;
  margin-bottom: 5px;
}
.right-grid3 {
  height: 27vh;
}
</style>
