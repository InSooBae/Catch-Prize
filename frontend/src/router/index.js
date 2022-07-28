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
      path: '/login',
      name: 'login',
      children: [
        {
          path: 'naver',
          name: 'loginNaver',
          component: () => import('../components/login/LoginNaver.vue'),
        },
      ],
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
      path: '/callback',
      name: 'callback',
      component: () => import('../views/CallbackView.vue'),
      children: [
        {
          path: 'naver',
          name: 'callbackNaver',
          component: () => import('../components/callback/CallbackNaver.vue'),
        },
      ],
    }
  ]
})

export default router
