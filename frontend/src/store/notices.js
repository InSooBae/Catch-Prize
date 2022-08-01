import { createStore } from "vuex";
import user from "./modules/user"


import axios from 'axios'
import router from '@/router'

// const params = {page: 1, size: 10}
// axios.get('https://e5e4-211-192-210-62.jp.ngrok.io/notice', {params})
// const drf = "https://e5e4-211-192-210-62.jp.ngrok.io"
import _ from 'lodash'

const noticURL = 'https://e5e4-211-192-210-62.jp.ngrok.io/notice/'

const store =  {

  state: {
    notices: [],
    notice: {},
  },

  getters: {
    notices: state => state.notices,
    notice: state => state.notice,
    isAuthor: (state, getters) => {
      return state.notice.user?.username === getters.currentUser.username
    },
    isNotice: state => !_.isEmpty(state.notice),
  },

  mutations: {
    SET_NOTICES: (state, notices) => state.notices = notices,
    SET_NOTICE: (state, notice) => state.notice = notice,
    SET_NOTICE_COMMENTS: (state, comments) => (state.notice.comments = comments),
  },

  actions: {
    // notice list 가져오기
    fetchNotices({ commit, getters }) {
      fetch('notice', {params})
      .then((response) => response.json())
      .then((json) => console.log(json));
      axios({
        url: noticURL,
        params: {page: 1, size: 10},
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => commit('SET_NOTICES', res))
        .catch(err => console.error(err.response))
    },

    // 특정 notice 가져오기
    fetchNotice({ commit, getters }, noticId) {
      axios({
        url: noticURL + `${noticeId}/`,
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => commit('SET_NOTICE', res.data))
        .catch(err => {
          console.error(err.response)
          if (err.response.status === 404) {
            router.push({ name: 'NotFound404' })
          }
        })
    },

    // notice 생성
    createNotice({ commit, getters }, notice) {
      axios({
        url: noticURL,
        method: 'post',
        data: notice,
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_NOTICE', res.data)
          router.push({
            name: 'notice',
            params: { noticeId: getters.notice.id }
          })
        })
    },

    // notice 수정 , 작성자 id들어가야함
    updateNotice({ commit, getters }, { id, title, content}) {
      axios({
        url: noticURL + `${noticeId}/`,
        method: 'put',
        data: { title, content },
        headers: getters.authHeader,
      })
      .then(res => {
        commit('SET_NOTICE', res.data)
        router.push({
          name: 'notice',
          params: { noticeId: getters.notice.id }
        })
      })
    },

    // notice 삭제 
    deleteNotice({ commit, getters }, noticeId) {
      if (confirm('정말 삭제하시겠습니까?')) {
        axios({
          url: noticURL + `${noticeId}/`,
          method: 'delete',
          headers: getters.authHeader,
        })
          .then(() => {
            commit('SET_NOTICE', {})
            router.push({ name: 'notices' })
          })
          .catch(err => console.error(err.response))
      }
    },

  },
}

export default store;