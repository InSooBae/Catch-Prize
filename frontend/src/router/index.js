import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue')
    },
    {
      path: '/login/:name',
      name: 'login',
      component: () => import('../views/LoginView.vue')
    },
    {
      path: '/lobby',
      name: 'lobby',
      component: () => import('../views/LobbyView.vue'),
      children: [
        {
          path: '',
          name: 'main',
          component: () => import('../components/lobby/RoomList.vue'),
        },
        {
          path: ':gameid',
          name: 'gameroom',
          component: () => import('../components/lobby/WaitingRoom.vue'),
        },
      ],
    },
    {
      path: '/game',
      name: 'game',
      component: () => import('../views/GameView.vue')
    },
    {
      path: '/redirect',
      name: 'redirect',
      component: () => import('../views/RedirectView.vue'),
    },
    {
      path: '/notice',
      name: 'notice',
      component: () => import('../views/NoticeView.vue'),
        children: [
        {
          path: '',
          name: 'notices',
          component: () => import('../components/notice/NoticeList.vue'),
        },
        {
          path: 'new',
          name: 'noticeNew',
          component: () => import('../components/notice/NoticeNew.vue')
        },
        {
          path: ':noticeId/edit',
          name: 'noticeEdit',
          component: () => import('../components/notice/NoticeEdit.vue')
        },
        {
          path: ':noticeId',
          name: 'noticeDetail',
          component: () => import('../components/notice/NoticeDetail.vue'),
          // props: true,
        }
      ]
    }
  ]
})

export default router
