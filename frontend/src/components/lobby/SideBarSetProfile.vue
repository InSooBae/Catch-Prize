<template>
  <el-form class="update-form" :model="formLabelAlign">
    <div class="profile-box py-3">
      <img>
      <label class="input-file-button" for="img-upload">
        File Upload
      </label>
    </div>
    <el-form-item>
      <el-input placeholder="Enter yout nickname" v-model="formLabelAlign.nickname" />
    </el-form-item>
    <el-form-item>
      <el-input placeholder="Enter your email" v-model="formLabelAlign.email" />
    </el-form-item>
    <el-form-item>
      <el-date-picker v-model="formLabelAlign.birthday" type="date" placeholder="Pick your Birthday" format="YYYY/MM/DD"
        value-format="YYYY-MM-DD">
      </el-date-picker>
    </el-form-item>
    <el-button color="#00bd9d" type="info" class="profile-update-button">UPDATE</el-button>
  </el-form>
</template>

<script setup>
import { reactive, ref } from 'vue'

const formLabelAlign = reactive({
  nickname: '',
  email: '',
  birthday: '',
})
</script>

<style>
.update-form {
  height: 100%;
  overflow: auto;
}

.el-input__wrapper {
  box-shadow: 0 0 0 2px #00bd9d inset;
  border: solid 2px #00bd9d;
  background-color: transparent;
}

.profile-update-button {
  border: none;
  font-family: "PressStart2P";
  font-size: medium;
  width: 100%;
  height: 38px;
  border-radius: 8px;
  color: white;
  margin-top: 5px;
}

.profile-update-button:hover {
  color: gray;
}
</style>