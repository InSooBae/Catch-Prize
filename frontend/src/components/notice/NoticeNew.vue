<template>
  <div class="NoticeNew">
    <el-main>
      <div class="container">
        <notice-form :notice="notice" action="글쓰기"></notice-form>
      </div>
    </el-main>
  </div>
</template>


<script setup>
import NoticeForm from './NoticeForm.vue';
const notice = {
  content : '',
  title : '',
}
</script>
<style>
@import '../../assets/notice.css';
</style>