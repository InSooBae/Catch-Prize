<template>
  <el-form class="start-form" :model="gameinfo">
    <el-form-item>
      <el-input placeholder="방 제목을 입력하세요." v-model="gameinfo.roomname" />
    </el-form-item>
    <el-form-item>
      <el-input placeholder="게임을 선택하세요." v-model="gameinfo.gametype" />
    </el-form-item>
    <el-form-item>
      <el-input placeholder="참여인원을 선택하세요" v-model="gameinfo.gamesize" />
    </el-form-item>
    <el-button color="#00bd9d" type="info" class="start-button">GAME START!</el-button>
  </el-form>
</template>

<script setup>
import { reactive, ref } from 'vue'

const gameinfo = reactive({
  roomname: '',
  gametype: '',
  gamesize: '',
})
</script>