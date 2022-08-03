import { createApp, reactive } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// element UI import 
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/display.css'

import './assets/main.css'

import io from "socket.io-client";
const hobulhoSocket = io("http://localhost:8081/hobulhoSocket",{ transports : ['websocket'] });

hobulhoSocket.on("card-select", function(data){
    hobulhostate.selectedcard=data;
    hobulhostate.gamestate="attack"
})
const app = createApp(App)
const hobulhostate = reactive({
    gamestate:"ready",
    selectedcard:"",
    selectedcardnum:2,
    attacker:"",
    defender:"",
    declaredcard:"",
    defendedeclare:"",
    deathplayer:"",
    winner:""
})

app.provide('$hobulhoSocket', hobulhoSocket);
app.provide('$hobulhostate', hobulhostate);
app.use(router)
app.use(ElementPlus)
app.use(store)
app.mount('#app')
