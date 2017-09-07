//const ratingList = require('./ratings');
//const renderRatings = require('./render');
import ratings from './data/ratings.json';
import React from 'react';
import ReactDom from 'react-dom';
import RatingList from './componets/RatingList';
import Hello from './componets/Hello';
import Echo from './componets/Echo';


ReactDom.render(
    <div>
        <RatingList dataList={ratings}/>
        <Hello value="asd"/>
        <Echo test="diaosi"/>
    </div>
    , document.getElementById('root')
)

// window.onload = () => {
//   document.getElementById('root').innerHTML = renderRatings(ratingList);
// };