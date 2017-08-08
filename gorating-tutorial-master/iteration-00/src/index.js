import ratingList from './ratings';
import renderRatings from './render';

window.onload = () => {
     document.getElementById('root').innerHTML = renderRatings(ratingList);
};

// const ratingList = require("./ratings");
// const renderRatings = require("./render");
// console.log(renderRatings(ratingList));
// window.onload = function() {
//     document.getElementById('root').innerHTML = renderRatings(ratingList);
// };
