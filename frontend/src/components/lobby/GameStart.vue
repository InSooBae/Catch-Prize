<template>
  <el-button color="#00bd9d" size="large" type="info" class="start-button" @click="startVisible = true">GAME START!
  </el-button>
  <el-dialog v-model="startVisible" :show-close="false">
    <template #header="{ titleId, titleClass }">
      <div class="my-header">
        <h4 :id="titleId" :class="titleClass">START YOUR GAME!</h4>
      </div>
    </template>
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
      <el-button color="#00bd9d" type="info" class="start-button" @click="makeRoom">GAME START!</el-button>
    </el-form>
  </el-dialog>
</template>

<script setup>
import { reactive, ref } from 'vue'

const startVisible = ref(false)

const gameinfo = reactive({
  roomname: '',
  gametype: '',
  gamesize: '',
})

const makeRoom = () => {
  console.log(gameinfo)
  startVisible.value = false
}
</script>