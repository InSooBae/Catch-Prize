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
<<<<<<< HEAD
      path: '/notice',
      name: 'notices',
      component: () => import('../components/notice/NoticeListView.vue'),
      children:[
        {
          path: '/notice/new',
          name: 'noticeNew',
          component: () => import('../components/notice/NoticeNewView.vue')
        },
        {
          path: '/notice/:noticeId/edit',
          name: 'noticeEdit',
          component: () => import('../components/notice/NoticeEditView.vue')
        },
        {
          path: '/notice/:noticeId',
          name: 'noticeDetail',
          component: () => import('../components/notice/NoticeDetailView.vue')
        }
      ]
    },
 
=======
      path: '/redirect',
      name: 'redirect',
      component: () => import('../views/RedirectView.vue'),
    }
>>>>>>> 1cb2e7501bfcc2eb78116c55f37833c3cef639b5
  ]
})

export default router
