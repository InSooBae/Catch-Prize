<template>
  <div>
    <form @submit.prevent="onSubmit">
      <div class="notice_button_container">
        <el-button 
          color="#7608d3" 
          link 
          :dark="isDark" 
          plain 
          size="small"
          >Create</el-button>
        <el-button color="#7608d3" link :dark="isDark" plain size="small">Delete</el-button>
      </div>
      <input 
        id="notice_title"
        v-model="newNotice.notice_title" 
        placeholder="Notice Title"
        color="green"
      />
      <hr>
      <textarea
        id="notice_content"
        v-model="newNotice.notice_content"
        :rows="10"
        type="textarea"
        placeholder="Notice Content"
      />
      <div class="notice_back_button_container">
        <el-button @click="ListView()" link color="#7608d3" :dark="isDark" plain size="small">Back</el-button>
      </div>
    </form>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
  export default {
    name: 'NoticeForm',
    props: {
      notice: Object,
      action: String,
    },
    data() {
      return {
        newNotice: {
          notice_title: this.notice.notice_title,
          notice_content: this.notice.notice_content,
        }
      }
    },
    methods: {
      ...mapActions(['createNotice', 'updateNotice']),
      ListView() {
                  this.$router.push('/notices');
              },
      onSubmit() {
        if (this.action === 'create') {
          this.createNotice(this.newNotice)
        } else if (this.action === 'update') {
          const payload = {
            pk: this.notice.pk,
            ...this.newNotice,
          }
          this.updateNotice(payload)
        }
      },
    },
  }
</script>

<style>
input{
  width : 100%;
  height : 100%;
  padding: 5px;
  background-color: transparent;
  border : none;
  color: white
}
input:focus{
  border: none;
    outline: none;
}
textarea{
  width : 100%;
  height : 30em;
  padding: 5px;
  background-color: transparent;
  border : none;
  resize: none;
  color: white
}
textarea:focus{
  border: none;
  outline: none;
}
el-input:focus{
  outline: none;
  --el-menu-active-color:green;
}
.notice_button_container button{
  float: right;
  border: none;
}
.notice_back_button_container button{
  float: left;
  border: none;
}
hr {
  border : 0px;
  border-top: 1.3px solid #7608d3;
}
el-button{
  border: none;
}
</style>