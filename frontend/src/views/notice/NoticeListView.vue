<template>
  <div class="container">
    <h1>공지사항 목록을 보여주는 View입니다</h1>
  </div>
</template>

<script>
  import { mapActions, mapGetters } from 'vuex'
  export default {
    name: 'NoticeList',
    computed: {
      ...mapGetters(['notices']),
      // ...mapGetters(['currentUser']),
    },
    methods: {
      ...mapActions(['fetchNotices'])
    },
    mounted() {
      this.fetchNotices()
    },
    data() {
      return {
        
    }
  }
  }
</script>

<style>

.th{
  text-align: center;
}
.tr{
  text-align: center;
}
</style>
