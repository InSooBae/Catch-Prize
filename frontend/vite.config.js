import { fileURLToPath, URL } from 'url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server : {
    // heeps: true,
    // open: true, 
    // proxy: {
    //   '/notice' : {
    //     'target' : 'https://e5e4-211-192-210-62.jp.ngrok.io',
    //     changeOrigin: true,
    //   }
    // }
  },
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
