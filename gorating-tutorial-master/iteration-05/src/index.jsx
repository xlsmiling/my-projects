import React from 'react';
import ReactDOM from 'react-dom';
import RatingList from './components/rating-list';
import ratings from './ratings.json';
// import './styles/global.css'

ReactDOM.render(<RatingList ratings={ratings} />, document.getElementById('root'));
