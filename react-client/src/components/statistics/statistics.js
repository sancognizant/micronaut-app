import React, { useState, useEffect } from "react";
import { connect } from "react-redux";
import "./statistics.css";

const Statistics = props => {
 const [gamestats, setGameStats] = useState({});

  useEffect(() => {
    setGameStats(props.gameStats);
  });

  return (
    <div className="Statistics">
      <h1>Game Stats </h1>
      <table>
        <thead>
          <tr>
            <th style={{ width: "8%" }}>Username</th>
            <th style={{ width: "11%" }}>Total Score</th>
            <th style={{ width: "11%" }}>Badge</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td style={{ width: "8%" }}>{gamestats.userId}</td>
            <td style={{ width: "11%" }}>{gamestats.score}</td>
            <td style={{ width: "11%" }}>{gamestats.badge}</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

const mapStateToProps = state => {
  return {
     gameStats: state.gameStats,
    loading: state.loading,
    error: state.error
  };
};

export default connect(mapStateToProps, null)(Statistics);
