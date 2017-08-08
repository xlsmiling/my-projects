/**
 * @type {{ randerBack,fontColor }}
 */
import styles from './style.scss'
/**
 * @param rating {{ id, name, country, gender, rank, elo }}
 * */
const renderRatingItem = (rating) => `<div class="${ [styles.randerBack , styles.fontColor].join(' ') }">
       <span>${ rating.rank }</span>
       <span>|</span>
       <span>${ rating.name }</span>
       <span>|</span>
       <span>${ rating.elo }</span>
     </div>`;

const renderRatings = (ratings) =>
    `<div>
    ${ ratings.map(renderRatingItem).join('\n') }
    </div>`;

export default renderRatings;