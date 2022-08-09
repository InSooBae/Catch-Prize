<template>
  <el-button color="#00bd9d" type="info"  class="start-button" @click="startVisible = true">GAME START!
  </el-button>
  <el-dialog v-model="startVisible" :show-close="false" custom-class="start-modal">
    <template #header="{ titleId, titleClass }">
      <div class="my-header ">
        <h4 :id="titleId" :class="titleClass" style="text-align: center; padding-top: 5px; font-size: 1.3rem;">START
          YOUR GAME!</h4>
      </div>
    </template>
    <el-form class="start-form" :model="gameinfo">
      <el-form-item>
        <el-input placeholder="방 제목을 입력하세요." v-model="gameinfo.roomname" />
      </el-form-item>
      <el-form-item>
        <el-select placeholder="게임을 선택하세요." v-model="gameinfo.gametype">
          <el-option label="호불호 포커" value="poker" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select placeholder="참여인원을 선택하세요" v-model="gameinfo.gamesize" :disabled="!gameinfo.gametype">
          <el-option :label="4" value=4 />
          <el-option :label="6" value=6 />
        </el-select>
      </el-form-item>
      <el-button color="#00bd9d" type="info" class="start-button" @click="makeRoom">GAME START!</el-button>
    </el-form>
  </el-dialog>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';

const router = useRouter()
const store = useStore()

const startVisible = ref(false)

const gameinfo = reactive({
  roomname: '',
  gametype: '',
  gamesize: '',
})

const makeRoom = () => {
  router.push({ name: 'gameroom', params: {roomId: gameinfo.roomname} })
  gameinfo.roomname = ''
  gameinfo.gametype = ''
  gameinfo.gamesize = ''
  startVisible.value = false
  const createRoom = () => store.dispatch('createRoom', gameinfo)
  createRoom()

}
</script>