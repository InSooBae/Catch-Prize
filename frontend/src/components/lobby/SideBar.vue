<template>
  <img src="@/assets/logo.svg" width="250" alt="logo" style="opacity: 0.9; margin: 0px 0px 0px 0px;">
  <el-tabs id="sidebar" tabPosition="bottom" :stretch=true @tab-click="changeTab">
    <SideProfile />
    <div v-if="isAddFriends" class="d-flex add-friend-container">
      <el-input v-model="addFriend" placeholder="이름을 입력하세요.">
        <template #suffix>
          <el-button color="#262C3A" type="info" class="add-button" @click="startVisible = true">
            <img src="@/assets/icons/person_add.svg" alt="add_friends">
          </el-button>
        </template>
      </el-input>

    </div>
    <el-tab-pane label="friends" class="sidebar-friends-element">
      <template #label>
        <div class="custom-tabs-label">
          <img src="@/assets/icons/group.svg" alt="group">
        </div>
      </template>
      <SideBarFriends />
    </el-tab-pane>
    <el-tab-pane label="record" class="sidebar-element">
      <template #label>
        <div class="custom-tabs-label">
          <img src="@/assets/icons/auto_stories.svg" alt="group">
        </div>
      </template>
      <SideBarMyRecord />
    </el-tab-pane>
    <el-tab-pane label="edit" class="sidebar-element">
      <template #label>
        <div class="custom-tabs-label">
          <img src="@/assets/icons/manage_accounts.svg" alt="group">
        </div>
      </template>
      <SideBarSetProfile />
    </el-tab-pane>
  </el-tabs>
  <GameStart />
</template>

<script setup>
import SideProfile from '@/components/lobby/SideProfile.vue'
import SideBarFriends from '@/components/lobby/SideBarFriends.vue'
import SideBarMyRecord from '@/components/lobby/SideBarMyRecord.vue'
import SideBarSetProfile from '@/components/lobby/SideBarSetProfile.vue'
import GameStart from '@/components/lobby/GameStart.vue';
import { ref } from 'vue';

const isAddFriends = ref(true)
const addFriend = ref('')

const changeTab = (data) => {
  if (data.props.label == "friends")
    isAddFriends.value = true
  else
    isAddFriends.value = false
}
</script>

<style>
</style>