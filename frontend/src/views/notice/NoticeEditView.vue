<template>
  <div class="container">
    
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