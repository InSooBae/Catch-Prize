<template>
  <img src="@/assets/logo.svg" width="250" alt="logo" style="opacity: 0.9; margin: 0px 0px 0px 0px;">
  <el-tabs id="sidebar" tabPosition="bottom" stretch="true">
    <div class="d-flex sidebar-profile p-1 pt-2">
      <img src="https://via.placeholder.com/50x50" alt="placeholder" style="margin-right: 10px;">
      <div>
        <p>PlayerName</p>
        <p style="font-size: smaller; color: gray;">email@gmail.com</p>
      </div>
    </div>
    <el-tab-pane class="sidebar-element">
      <template #label>
        <div class="custom-tabs-label">
          <img src="@/assets/icons/group.svg" alt="group">
        </div>
      </template>
      <SideBarFriends />
    </el-tab-pane>
    <el-tab-pane class="sidebar-element">
      <template #label>
        <div class="custom-tabs-label">
          <img src="@/assets/icons/auto_stories.svg" alt="group">
        </div>
      </template>
      <SideBarMyRecord />
    </el-tab-pane>
    <el-tab-pane class="sidebar-element">
      <template #label>
        <div class="custom-tabs-label">
          <img src="@/assets/icons/manage_accounts.svg" alt="group">
        </div>
      </template>
      <SideBarSetProfile />
    </el-tab-pane>
  </el-tabs>
  <el-button color="#00bd9d" size="large" type="info" class="start-button" @click="startVisible = true">GAME START!</el-button>
  <el-dialog v-model="startVisible" :show-close="false">
    <template #header="{ close, titleId, titleClass }">
      <div class="my-header">
        <h4 :id="titleId" :class="titleClass">START YOUR GAME!</h4>
        <el-button type="danger" @click="close">
          <el-icon class="el-icon--left"><CircleCloseFilled /></el-icon>
          Close
        </el-button>
      </div>
    </template>
    this is game info
  </el-dialog>
</template>

<script setup>
import SideBarFriends from '@/components/lobby/SideBarFriends.vue'
import SideBarMyRecord from '@/components/lobby/SideBarMyRecord.vue'
import SideBarSetProfile from '@/components/lobby/SideBarSetProfile.vue'

import { ref } from 'vue'

const startVisible = ref(false)
</script>

<style>
#sidebar {
  background-color: #262C3A;
  margin-bottom: 10px;
  border-radius: 15px;
}
.sidebar-profile {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: whitesmoke;
  padding-bottom: 5px;
  margin-bottom: 5px;
  border-bottom: solid 1px;
  border-color: #2c3e50;
}
.sidebar-element {
  height: 50vh;
}

@media (max-height: 850px) {
  .sidebar-element {
    height: 46vh;
  }
}

@media (max-height: 800px) {
  .sidebar-element {
    height: 44vh;
  }
}

@media (max-height: 770px) {
  .sidebar-element {
    height: 43vh;
  }
}
@media (max-height: 730px) {
  .sidebar-element {
    height: 42vh;
  }
}
@media (max-height: 700px) {
  .sidebar-element {
    height: 37vh;
  }
}
@media (max-height: 650px) {
  .sidebar-element {
    height: 31vh;
  }
}

@media (max-height: 600px) {
  .sidebar-element {
    height: 26vh;
  }
}

@media (max-height: 550px) {
  .sidebar-element {
    height: 17vh;
  }
}

#sidebar>.el-tabs__content {
  background-color: #262C3A;
  padding: 15px;
  border-radius: 15px;
}

.el-tabs__active-bar {
  background-color: white;
}

.el-tabs__nav {
  border-bottom: solid #0F1E33;
  ;
  padding: 0px 25px;
}

.el-tabs__item {
  background-color: #262C3A;
  padding: 0px 5px;
  margin: 0px 5px;
}

.start-button {
  border: none;
  font-family: "PressStart2P";
  font-size: medium;
  width: 100%;
  height: 60px;
  border-radius: 10px;
  color: white;
}
.start-button:hover {
  color: gray;
}
.el-dialog {
  border-radius: 10px;
  border: solid #f2e53bCC;
  background-color: #262C3A;
}
.el-dialog__title {
  color: whitesmoke;
}
.el-dialog__body {
  color: whitesmoke;
}
.el-form::-webkit-scrollbar {
  width: 5px;
}
.el-form::-webkit-scrollbar-thumb {
  background-color: #00bd9d;
  border-radius: 5px;
}
.el-form::-webkit-scrollbar-track {
  background-color: transparent;
  border-radius: 5px;
}
</style>