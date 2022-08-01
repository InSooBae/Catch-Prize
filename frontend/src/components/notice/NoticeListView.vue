
<template>
  <div>
    <h1 class="title">공지사항 view</h1>
        <router-link :to="{ name: 'noticeNew' }">
          CREATE
        </router-link>
    <NoticeListItems :list-array="notices" />
  </div>
</template>
<script>
import { mapActions, mapGetters } from 'vuex'
import NoticeListItems from './NoticeListItems.vue'
export default {
  data() {
    return {
      pageNum: 0,
    };
  },    
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
<script setup>
  // import axios from 'axios';
  

  // import { onMounted, ref } from 'vue'

  // const pageArray = ref([])
  // onMounted(() => {
  //   const params = {page: 1, size: 10}
  //   axios.get('https://e5e4-211-192-210-62.jp.ngrok.io/notice', {params})
  //   .then(response => {
  //   console.log(response.data);
  //   pageArray.value = response.data
  // })
  // .catch(err => {
  //   console.log(err);
  // });
  // })
    

</script>

<style>

.th{
  text-align: center;
}
.tr{
  text-align: center;
}
</style>
