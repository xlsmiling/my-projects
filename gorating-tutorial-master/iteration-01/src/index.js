//const ratingList = require('./ratings');
//const renderRatings = require('./render');
import ratingList from './ratings.json';
import renderRatings from './render';

window.onload = () => {
  document.getElementById('root').innerHTML = renderRatings(ratingList);
};