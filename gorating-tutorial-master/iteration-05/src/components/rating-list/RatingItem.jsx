import React from 'react';
/** @type {{ separator, ratingItem, avatarWrapper, profileWrapper, rankWrapper, rank, nameWrapper, name, flag, eloWrapper, prefix }} */
import styles from './styles.scss';
import avatar from '../../images/avatar.jpg';
import chinaFlag from '../../images/china.png';
import japanFlag from '../../images/japan.png';
import koreaFlag from '../../images/korea.png';


const flagImages = {
  'cn': chinaFlag,
  'jp': japanFlag,
  'kr': koreaFlag,
};

/** @type {{ rank, name, elo, avatar, country }} */
const RatingItem = ({ rating }) => (<div className={styles.ratingItem}>

  <div className={styles.avatarWrapper}>
    <img alt="avatar" src={rating.avatar || avatar} />
  </div>
  <div className={styles.rankWrapper}>
    <span className={styles.prefix}>No.</span>
    <span className={styles.rank}>{ rating.rank }</span>
  </div>

  <div className={styles.profileWrapper}>
    <div className={styles.nameWrapper}>
      <span className={styles.flag}>
            <img alt="flag" src={flagImages[rating.country]} />
      </span>
      <span className={styles.name}>{ rating.name }</span>
    </div>

    <div className={styles.eloWrapper}>
      <span className={styles.prefix}>ELO:</span>
      <span className={styles.prefix}>{ rating.elo }</span>
    </div>
  </div>

    {/*<div className={styles.rankWrapper}>*/}
      {/*No.*/}
      {/*<span className={styles.rank}>*/}
        {/*{ rating.rank }*/}
      {/*</span>*/}
    {/*</div>*/}
    {/*<div>{ rating.elo }</div>*/}
  {/*</div>*/}
  {/*<div className={styles.nameWrapper}>*/}
      {/*<span >*/}
        {/*{ rating.name }*/}
      {/*</span>*/}
  {/*</div>*/}
  {/*<div className={styles.flagWrapper}>*/}
    {/*<img alt="flag" src={flagImages[rating.country]} />*/}
  {/*</div>*/}
</div>);

export default RatingItem;
