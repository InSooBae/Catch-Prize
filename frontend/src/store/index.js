import { createStore } from "vuex";
import user from "./modules/user"
import notice from "./modules/notice"

const store = createStore({
  modules: {
    user,
    notice,
  },
});

export default store