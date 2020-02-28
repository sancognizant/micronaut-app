import { createStore, applyMiddleware } from 'redux';
//import rootReducer from '../store/rootReducer';
import reducer from '../store/reducer';
import thunk from "redux-thunk";

// to use root reducer, there is a need to object destruct properly in all mapstatetoprops to specify reducer used

export default createStore(reducer, applyMiddleware(thunk));