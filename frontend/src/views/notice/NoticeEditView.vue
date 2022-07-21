<template>
  <div class="container">
    <h1 class="title">공지사항 수정</h1>
    <notice-form 
      v-if="isNotice" 
      :notice="notice" 
      action="update">
    </notice-form>
  </div>

</template>

<script>
import NoticeForm from './NoticeForm.vue'
import { mapGetters, mapActions } from 'vuex'
  export default {
    name: 'NoticeEditView',
    components: { NoticeForm }, 
    computed: {
      ...mapGetters(['notice', 'isNotice',])
    },
    methods: {
      ...mapActions(['fetchNotice'])
    },
    created() {
      this.fetchNotice(this.$route.params.noticePk)
    },
  }
</script>

<style></style> 