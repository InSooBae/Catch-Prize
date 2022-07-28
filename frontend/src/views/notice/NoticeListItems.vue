<template>
<div>
  <div class="notice-container">
    <div class="btn-table-container">
      <div class="notice-create-btn">

      </div>
      <div class="notice-table">
        <table>
          <tr>
            <th class="table-no">NO</th>
            <th class="table-title">제목</th>
            <th class="table-time">작성시간</th>
          </tr>
          <tr v-for="p in paginatedData" :key="p.no">
            <td>{{ p.notice_id }}</td>
            <td><router-link :to="{ name: 'noticeDetail', params: { noticeId } } ">{{ p.tel }}</router-link></td>
            <td>{{ p.reg_date }}</td>
          </tr>
        </table>
      </div>
    </div>  
  </div>
  <div class="btn-cover">
    <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
      이전
    </button>
    <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
    <button
      :disabled="pageNum >= pageCount - 1"
      @click="nextPage"
      class="page-btn"
    >
      다음
    </button>
  </div>
</div>
</template>

<script>
// import { mapActions, mapGetters } from 'vuex'
// const data = {
//   title: "제목",
//   content: "내용",
//   reg_date: "2022-10-22",
//   user_id: "admmin1"
// }
export default {
  name: "NoticeList",
  data() {
    return {
      pageNum: 0,
    };
  },
  props: {
    listArray: {
      type: Array,
      required: true,
    },
    pageSize: {
      type: Number,
      required: false,
      default: 10,
    },
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
  },
  computed: {
    pageCount() {
      let listLeng = this.listArray.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;
      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.listArray.slice(start, end);
    },
  },
};
</script>

<style>
.notice-container{
  display : flex;
  flex-wrap: wrap;
  justify-content: center;
}
.btn-table-container{
  width:50rem;
}
.notice-create-btn{
  display: flex;
  justify-content: flex-end;

}
.create-btn{
  width : 5rem;
  height: 2rem;
}
table {
  width:50rem;
  border-collapse: collapse;
}

table th {
  font-size: 1.2rem;
}

table tr {
  height: 2rem;
  text-align: center;
  border-bottom: 1px solid #505050;
}

table tr:first-of-type {
  border-top: 2px solid #404040;
}

table tr td {
  padding: 1rem 0;
  font-size: 1.1rem;
}
.table-no {
  width: 10%
}
.table-time{
  width: 10%
}

.btn-cover {
  margin-top: 1.5rem;
  text-align: center;
}

.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}

.btn-cover .page-count {
  padding: 0 1rem;
}
</style>

