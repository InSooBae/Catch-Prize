<script setup>
import LobbyMain from '@/components/lobby/LobbyMain.vue'
import SideBar from '@/components/lobby/SideBar.vue'
</script>

<template>
  <div class="lobby-layout">
    <el-container>
      <el-header class="hidden-xs-only">
        <span>
          <MARQUEE>Notice: [New] Game released on 2022</MARQUEE>
        </span>
      </el-header>
      <el-container>
        <el-aside class="hidden-sm-and-down" width="310px">
          <SideBar></SideBar>
        </el-aside>
        <el-main>
          <LobbyMain></LobbyMain>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style>
@import '../assets/lobby.css';
</style>