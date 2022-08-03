import { createStore } from "vuex";
import user from "./modules/user"
import notice from "./modules/notice"
import room from "./modules/room"


const store = createStore({
  modules: {
    user,
    notice,
    room,
  },
});

export default store