import { createStore } from "vuex";
import user from "./modules/user"
import notice from "./modules/notice"
import room from "./modules/room"
import webrtc from "./modules/webrtc";


const store = createStore({
  modules: {
    user,
    notice,
    room,
    webrtc,
    item
  },
});

export default store