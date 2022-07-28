<template>
  <div class="NoticeNew">
    
    <el-main>
      <div class="notice_container">
        <notice-form :notice="notice" action="create"></notice-form>
      </div>
    </el-main>
  </div>
</template>

<script>
  import NoticeForm from './NoticeForm.vue'
  export default {
    name: 'NoticeNewView',
    components: { NoticeForm },
    data() {
      return {
        notice: {
          pk: null,
          title: '',
          content: '',
        }
      }
    },
  }
</script>

<style>
.notice_container {
  background-color: rgba( 255, 255, 255, 0.13) ;
  border-radius: 13px;
  padding: 40px;
}

</style>