<template>
  <div class="lobby-layout">
    <el-dialog custom-class="notice-modal" v-model="noticeVisible" title="Notice" width="50%" center>
      <span>Notice의 내용입니다.</span>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="noticeVisible = false">Close</el-button>
        </span>
      </template>
    </el-dialog>
    <el-container>
      <el-header class="hidden-xs-only" @click="noticeVisible = true">
        <marquee class="notice-marquee" scrollamount="12" onmouseover="this.stop();" onmouseout="this.start();">Notice: [New] Game released on 2022</marquee>
      </el-header>
      <el-container>
        <el-aside class="hidden-sm-and-down" width="310px">
          <SideBar/>
        </el-aside>
        <el-main>
          <RouterView/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import SideBar from '@/components/lobby/SideBar.vue';

import { ref } from 'vue';

const noticeVisible = ref(false);
</script>

<style>
@import '../assets/lobby.css';
</style>