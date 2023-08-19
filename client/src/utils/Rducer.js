import { useDispatch } from "react-redux";
import { test } from "./Action";

const dispatch = useDispatch();

dispatch(test());

const reducer = (currentState, test) => {
    if(currentState === undefined) {
        return {className : "a"};
    }
    const newState = {...currentState};
    if(test.type === "a") {
        newState.className === "b";
    }
    return newState;
}

export default reducer;