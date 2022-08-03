import { createStore } from "vuex";
import user from "./modules/user"
import room from "./modules/room"

const store = createStore({
  modules: {
    user,
    room,
  },
});

export default store