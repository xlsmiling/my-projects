import React from 'react';
import ReactDOM from 'react-dom';
import RatingList from './components/rating-list';
import ratings from './ratings.json';

ReactDOM.render(<RatingList ratings={ratings} />, document.getElementById('root'));
