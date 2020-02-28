import {getGameStats} from '../utils/service';

export const ANSWER_QUESTION = 'ANSWER_QUESTION';
export const SET_ATTEMPTS = 'SET_ATTEMPTS';
export const GET_LEADERBOARD = "GET_LEADERBOARD";

export const FETCH_GAMESTATS_BEGIN = "FETCH_GAMESTATS_BEGIN";
export const FETCH_GAMESTATS_SUCCESS = "FETCH_GAMESTATS_SUCCESS";
export const FETCH_GAMESTATS_FAILURE = "FETCH_GAMESTATS_FAILURE";


/* function that returns gameStats */
export const fetchGameStats = (userId) => {
    return dispatch => {
        dispatch(fetchGameStatsBegin());
        return getGameStats(userId)
        .then(res => {
            dispatch(fetchGameStatsSuccess(res));
            return res;
        })
        .catch(error => dispatch(fetchGameStatsFailure(error)))
    };
}

export const fetchGameStatsBegin = () => ({
    type: FETCH_GAMESTATS_BEGIN
});

export const fetchGameStatsSuccess = gameStats => 
({
    type: FETCH_GAMESTATS_SUCCESS,
    payload: gameStats
    
});

export const fetchGameStatsFailure = error => ({
    type: FETCH_GAMESTATS_FAILURE,
    payload: error
});
