import { configureStore } from "@reduxjs/toolkit";
import userIdSlice from "../utils/userIdslice";

const store = configureStore({
  reducer: {
    userId: userIdSlice,
  },
});

export default store;