<template>
  <div class="time-progress">
    <el-progress :text-inside="true" :stroke-width="26" :percentage="70" />
  </div>
</template>

<script>
export default {
  name: "timer",
};
</script>

<style scoped>

.time-progress .el-progress--line {
  margin-left: 50px;
  width: 100%;
}
</style>
