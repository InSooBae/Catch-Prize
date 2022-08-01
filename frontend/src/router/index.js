import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: () => import('../views/LoginView.vue')
    },
    {
      path: '/signup',
      name: 'signup',
      component: () => import('../views/SignupView.vue')
    },
    {
      path: '/lobby',
      name: 'lobby',
      component: () => import('../views/LobbyView.vue')
    },
    {
      path: '/game',
      name: 'game',
      component: () => import('../views/GameView.vue')
    },
    {
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
 
  ]
})

export default router
