import { API_BASE_URL } from "../../constants";
import router from '../../router';
import axios from 'axios'
// import router from useRouter()

const noticeURL = API_BASE_URL + '/notice';


const notice =  {
  state: {
    // 공지사항 전체를 받아옴
    notices: [],
    // 특정 공지사항을 받아옴 
    notice: {},
  },
  getters: {
    notices: state => state.notices,
    notice: state => state.notice,
  },
  mutations: {
    SET_NOTICES: (state, notices) => state.notices = notices,
    SET_NOTICE: (state, notice) => state.notice = notice,
  },
  actions: {
    // notice list 가져오기
    fetchNotices({ commit, getters }) {
      axios({
        url: noticeURL,
        params: {page: 1, size: 10},
        method: 'get',
        headers: getters.authHeader,
      })
      .then(res => {
        res.data.forEach(data => {
          data.regDate = data.regDate.substr(0, 10)
        })
        commit('SET_NOTICES', res.data)
        console.log(res.data)
      })
      .catch(err => console.error(err.response))
    },

    // 특정 notice 가져오기
    fetchNotice({ commit, getters }, noticeId) {
      axios({
        url: noticeURL + `/${noticeId}/`,
        method: 'get',
        headers: getters.authHeader,
      })
      .then(res => {
        commit('SET_NOTICE', res.data)
        console.log(res.data)
      })
      .catch(err => {
        console.error(err)
        // if (err.response.status === 404) {
        //   router.push({ name: 'NotFound404' })
        // }
      })
    },

    // notice 생성
    createNotice({ commit, getters }, notice) {
      axios({
        url: noticeURL,
        method: 'post',
        data: notice,
        headers: getters.authHeader,
      })
      .then(res => {
        commit('SET_NOTICE', res.data)
        console.log(res)
        console.log(getters.notice)
        router.push({
          name: 'noticeDetail',
          params: { noticeId: getters.notice }
        })
      })
    },
    // notice 수정 , 작성자 id들어가야함
    updateNotice({ commit, getters }, notice) {
      axios({
        url: noticeURL + `/${notice.id}/`,
        method: 'put',
        data: notice,
        headers: getters.authHeader,
      })
      .then(res => {
        commit('SET_NOTICE', res.data)
        router.push({
          name: 'noticeDetail',
          params: { noticeId: getters.notice }
        })

      })
    },
    // notice 삭제 
    deleteNotice({ commit, getters }, noticeId) {
      if (confirm('정말 삭제하시겠습니까?')) {
        axios({
          url: noticeURL + `/${noticeId}/`,
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

export default notice;