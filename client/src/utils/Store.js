import { createStore } from "redux";
import reducer from "./Rducer";

const store = createStore(reducer);

export default store;