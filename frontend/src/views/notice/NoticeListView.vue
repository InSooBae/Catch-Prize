
<template>
  <div>
    <h1 class="title">공지사항 view</h1>
        <router-link :to="{ name: 'noticeNew' }">
          CREATE
        </router-link>
    <notice-list-items :list-array="pageArray"></notice-list-items>
  </div>
</template>

<script>
  import axios from 'axios';
  // import { mapActions, mapGetters } from 'vuex'
  import NoticeListItems from './NoticeListItems.vue'
  export default {
    name: 'NoticeList',
    components: { NoticeListItems },
    data() {
      return {
        pageArray: []
    }},
    created () {
      axios.get('http://sample.bmaster.kro.kr/contacts')
      .then(response => {
      console.log(response);
      this.pageArray = response.data.contacts;
    })
    .catch(err => {
      console.log(err);
    });
    }}
  //   computed: {
  //     ...mapGetters(['notices']),
  //     ...mapGetters(['currentUser']),
  //   },
  //   methods: {
  //     ...mapActions(['fetchNotices'])
  //   },
  //   mounted() {
  //     this.fetchNotices()
  //   },
  //   data() {
  //     return {
        
  //   }
  // }
</script>

<style>

.th{
  text-align: center;
}
.tr{
  text-align: center;
}
</style>
