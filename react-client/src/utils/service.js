// generating randomQuestions
const randomQuestion = async count => {
  let response = await fetch(`http://localhost:8080/questions/${count}`);
  let data = await response.json();

  const newData = {
    ...data,
    choices: data.choices.split("|"),
    choices1: data.choices
  };

  return newData;
};

// submitting answer to back-end
const postResult = payLoad => {
  return fetch("http://localhost:8080/results", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(payLoad)
  })
    .then(res => res.json())
    .then(data => data);
};

const highScores = () => {
  return fetch(`http://localhost:5000/scorecard/leaderboard`, {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json"
    }
  })
    .then(response => response.json())
    .then(response => {
      return response;
    });
};

//getting a user's gamestats
const getGameStats = userId => {
  return fetch(`http://localhost:5000/scorecard/gamestats/${userId}`, {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json"
    }
  })
    .then(response => response.json())
    .then(response => {
      return response;
    });
};

export { randomQuestion, getGameStats, postResult, highScores };
