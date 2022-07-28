import axios from 'axios'
// import drf from '@/api/drf'
import router from '@/router'

const drf = https://jsonplaceholder.typicode.com/posts/notice
import _ from 'lodash'
// import accounts from './accounts'

export default {
  // namespaced: true,
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
    fetchNotices({ commit, getters }) {
      // fetch('https://jsonplaceholder.typicode.com/posts')
      // .then((response) => response.json())
      // .then((json) => console.log(json));
      axios({
        url: drf,
        method: 'get',
        // headers: getters.authHeader,
      })
        .then(res => commit('SET_NOTICES', res))
        .catch(err => console.error(err.response))
    },

    fetchNotice({ commit, getters }, noticePk) {
      axios({
        url: drf.notices.notice(noticePk),
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

    createNotice({ commit, getters }, notice) {
      axios({
        url: drf.notices.notices(),
        method: 'post',
        data: notice,
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_NOTICE', res.data)
          router.push({
            name: 'notice',
            params: { noticePk: getters.notice.pk }
          })
        })
    },

    updateNotice({ commit, getters }, { pk, notice_title, notice_content}) {
      axios({
        url: drf.notices.notice(pk),
        method: 'put',
        data: { notice_title, notice_content },
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_NOTICE', res.data)
          router.push({
            name: 'notice',
            params: { noticePk: getters.notice.pk }
          })
        })
    },

    deleteNotice({ commit, getters }, noticePk) {
      if (confirm('정말 삭제하시겠습니까?')) {
        axios({
          url: drf.notices.notice(noticePk),
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