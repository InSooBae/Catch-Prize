<template>
  <div class="itemshop-container">
    <div>
      <el-button @click="sendFilter('abc', 'Rotation')"></el-button>
    </div>
    <div>
      <el-button @click="sendFilter('abc', 'Pitch')"></el-button>
    </div>
    <div>
      <el-button @click="sendFilter('abc', 'Chroma')"></el-button>
    </div>
    <div>
      <el-button @click="sendFilter('abc', 'Rotation')"></el-button>
    </div>
    <div>
      <el-button @click="sendFilter('abc', 'Rotation')"></el-button>
    </div>
    <div>
      <el-button @click="sendFilter('abc', 'Rotation')"></el-button>
    </div>
  </div>
</template>

<script setup>
import { useStore } from 'vuex';

const store = useStore()
const sendFilter = () => store.dispatch('sendFilter')
</script>

<style>
.itemshop-container {
  display: flex;
  flex-direction: row;
  width: 100%;
  height: 100%;
}
.itemshop-container div{
  width: 16%;
}
.itemshop-container img {
  width: 60%;
  height: 60%;
}
</style>
