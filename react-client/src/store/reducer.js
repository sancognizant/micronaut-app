import * as actionTypes from './actions';


const initialState = {
  attemptList: [],
  gameStats: {},
  loading: false, 
  error: null
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.ANSWER_QUESTION: {
      return {
        ...state,
        attemptList: [...state.attemptList, action.payload]
      };
    }
    case actionTypes.SET_ATTEMPTS: {
      return {
        ...state,
        attemptList: action.payload
      };
    }
    case actionTypes.FETCH_GAMESTATS_BEGIN: {
      return {
        ...state,
        loading: true,
        error: null
      };
    }

      case actionTypes.FETCH_GAMESTATS_SUCCESS: {
        return {
          ...state,
          loading: false,
          gameStats: action.payload
        };
      }

      case actionTypes.FETCH_GAMESTATS_FAILURE: {
        return {
          ...state,
          loading: false,
          error: action.payload,
          gameStats: {}
        };
      }


    
    default:
      return state;
  }
};

export default reducer;
