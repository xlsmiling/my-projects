import React from 'react';
import RatingItem from './RatingItem';

const RatingList = ({ ratings }) => (<div>
  { ratings.map(rating => (<RatingItem key={rating.id} rating={rating} />)) }
</div>);

export default RatingList;
