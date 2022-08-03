<template>
  <div class="NoticeNew">
    <el-main>
      <notice-form :notice="notice" action="글쓰기"></notice-form>
    </el-main>
  </div>
</template>


<script setup>
import NoticeForm from './NoticeForm.vue';
const notice = {
  content: '',
  title: '',
}
</script>
<style>
@import '../../assets/notice.css';
</style>