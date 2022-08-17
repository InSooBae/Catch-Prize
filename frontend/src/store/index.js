import { createStore } from "vuex";
import user from "./modules/user"
import notice from "./modules/notice"
import room from "./modules/room"
import item from './modules/item'
import faceapi from './modules/faceapi'


const store = createStore({
  modules: {
    user,
    notice,
    room,
    item,
    // faceapi,
  },
});

export default store