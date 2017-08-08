import React from 'react';

/** @type {{ rank, name, elo }} */
const RatingItem = ({ rating }) => (<div>
  <span>{ rating.rank }</span>
  <span>|</span>
  <span>{ rating.name }</span>
  <span>|</span>
  <span>{ rating.elo }</span>
</div>);

export default RatingItem;
