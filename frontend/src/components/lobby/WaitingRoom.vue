<template>
  <el-row id="elem" class="wait-container" style="height: calc(100vh - 80px);">
    <el-col :sm="15" class="hidden-md-and-down">
    <div>{{ gameid }}</div>
      <el-row :gutter="20" class="people-container">
        <el-col class="person-container" :lg="8" v-for="person in 6" :key="person">
          <div>name-tag</div>
          <img class="person-image" src="@/components/hobulho-game/assets/person.png" alt="">
        </el-col>
      </el-row>
    </el-col>
    <el-col :xs="24" :lg="9" style="height: calc(100vh - 125px);">
      <div class="chat-container">
        <div class="chat-view">
          <div v-for="person in 10" :key="person">
            <p>황태희 바보</p>
            <p>바보가 맞다</p>
            <p>{{ gameid }}</p>
          </div>
        </div>
        <div>
          <el-input v-model="chatdata" placeholder="대화를 입력하세요.">
            <template #suffix>
              <el-button color="#7608d3" type="info" class="add-button" @click="startVisible = true">
                <img src="@/assets/icons/person_add.svg" alt="add_friends">
              </el-button>
            </template>
          </el-input>
        </div>  
      </div>
      <div class>
        <el-button color="#7608d3" type="info" id="start-ready-button" @click="unSub"></el-button>
      </div>
    </el-col>
  </el-row>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';

const route = useRoute()
const store = useStore()
const chatdata = ref('')
const gameid = route.params.gameid;

const unSub = () => {
  store.dispatch('closeSubscribe')
}

onMounted(() => {
  const startReady = document.getElementById('start-ready-button');
  startReady.innerText = 'Ready!'
  store.commit('SET_ISWAIT', true)
})
</script>

<style>
.wait-container .people-container {
  padding-right: 20px;
}

.wait-container .person-container {
  margin-bottom: 20px;
}

.person-container .person-image {
  width: 100%;
}

.wait-container .chat-container {
  margin-bottom: 10px;
  padding: 10px;
  height: calc(100% - 90px);
  background-color: #0F1E33;
  border-radius: 10px;
}

.chat-container .chat-view {
  /* layout */
  /* BOX */
  height: calc(100% - 40px);
  overflow: auto;
  /* background */
  /* font */
  color: whitesmoke;
  /* border */

}

.chat-container .chat-view::-webkit-scrollbar {
  width: 5px;
}

.chat-container .chat-view::-webkit-scrollbar-thumb {
  /* layout */
  /* BOX */
  /* background */
  background-color: #262C3A;
  /* font */
  /* border */
  border-radius: 5px;
}

.chat-container .chat-view::-webkit-scrollbar-track {
  /* layout */
  /* BOX */
  /* background */
  background-color: transparent;
  /* font */
  /* border */
  border-radius: 5px;
}

.chat-container .el-input__wrapper {
  /* layout */
  padding: 4px 5px 4px 10px;
  /* BOX */
  /* background */
  background-color: transparent;
  /* font */
  /* border */
  box-shadow: 0 0 0 1px #00bd9d inset;
}


#start-ready-button {
  /* layout */
  height: 60px;
  /* BOX */
  width: 100%;
  /* background */
  /* font */
  font-family: "PressStart2P";
  font-size: medium;
  color: whitesmoke;
  /* border */
  border: none;
  border-radius: 10px;
}
</style>