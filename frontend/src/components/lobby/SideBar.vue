<template>
  <img src="@/assets/logo.svg" width="250" alt="logo" style="opacity: 0.9; margin: 0px 0px 0px 0px;">
  <el-tabs id="sidebar" tabPosition="bottom" :stretch=true @tab-click="changeTab">
    <Profile />
    <AddFriend v-if="isFriends" />
    <el-tab-pane label="friends" class="sidebar-friends-element">
      <template #label>
        <div class="custom-tabs-label">
          <img src="@/assets/icons/group.svg" alt="group">
        </div>
      </template>
      <Friends />
    </el-tab-pane>
    <el-tab-pane label="record" class="sidebar-element">
      <template #label>
        <div class="custom-tabs-label">
          <img src="@/assets/icons/auto_stories.svg" alt="group">
        </div>
      </template>
      <MyRecord />
    </el-tab-pane>
    <el-tab-pane label="edit" class="sidebar-element">
      <template #label>
        <div class="custom-tabs-label">
          <img src="@/assets/icons/manage_accounts.svg" alt="group">
        </div>
      </template>
      <SetProfile />
    </el-tab-pane>
  </el-tabs>
  <GameStart />
</template>

<script setup>
import Profile from './SideBar/Profile.vue'
import Friends from './SideBar/Friends.vue'
import MyRecord from './SideBar/MyRecord.vue'
import SetProfile from './SideBar/SetProfile.vue'
import GameStart from './SideBar/GameStart.vue';
import AddFriend from './SideBar/AddFriend.vue';

import { ref } from 'vue';

const isFriends = ref(true)

const changeTab = (data) => {
  if (data.props.label == "friends")
    isFriends.value = true
  else
    isFriends.value = false
}
</script>

<style>
</style>