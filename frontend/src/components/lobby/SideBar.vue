<template>
  <img src="@/assets/logo.svg" width="250" alt="logo" style="opacity: 0.9; margin: 0px 0px 0px 0px;">
  <el-tabs id="sidebar" tabPosition="bottom" stretch="true">
    <div class="d-flex sidebar-profile p-1 pt-2">
      <img src="https://placekitten.com/50/50/" alt="placeholder" style="margin-right: 10px;">
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
  <el-button color="#00bd9d" size="large" type="info" class="start-button" @click="startVisible = true">GAME START!
  </el-button>
  <el-dialog class="start-modal" v-model="startVisible" :show-close="false">
    <template #header="{ titleId, titleClass }">
      <div class="my-header">
        <h4 :id="titleId" :class="titleClass">START YOUR GAME!</h4>
      </div>
    </template>
    <GameStart />
  </el-dialog>
</template>

<script setup>
import SideBarFriends from '@/components/lobby/SideBarFriends.vue'
import SideBarMyRecord from '@/components/lobby/SideBarMyRecord.vue'
import SideBarSetProfile from '@/components/lobby/SideBarSetProfile.vue'
import GameStart from '@/components/lobby/GameStart.vue';

import { ref } from 'vue'

const startVisible = ref(false)
</script>

<style>
</style>