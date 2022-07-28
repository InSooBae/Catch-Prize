import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
// element UI import 
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/display.css'

import './assets/main.css'

const app = createApp(App)

app.use(router)
app.use(ElementPlus)
app.mount('#app')