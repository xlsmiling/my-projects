import React from 'react';
import RatingItem from './RatingItem';
/** @type {{ ratingList }} */
import styles from './styles.scss';

const RatingList = ({ ratings }) => (<div className={styles.ratingList}>
  { ratings.map(rating => (<RatingItem key={rating.id} rating={rating} />)) }
</div>);

export default RatingList;
