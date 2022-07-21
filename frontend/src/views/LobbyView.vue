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
.lobby-layout .el-container{
  height: 100vh;
  font-family: "PressStart2P";
}
.lobby-layout .el-aside{
  /* color: #0F1E33; */
  background-color: #0F1E33;
  padding: 0 40px 40px 40px;
}
.lobby-layout .el-header{
  height: 80px;
  background-color: #0F1E33;
  padding: 28px 10px 0px 10px;
}
.lobby-layout .el-header span{
  font-size: 15px;
  color: white;
}
.el-main::-webkit-scrollbar {
  width: 10px;
}
.el-main::-webkit-scrollbar-thumb {
  background-color: #0F1E33;
  border-radius: 10px;
}
.el-main::-webkit-scrollbar-track {
  background-color: transparent;
  border-radius: 10px;
}
</style>