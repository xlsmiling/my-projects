import React from 'react';
/**
 * @type {{ randerBack,fontColor }}
 */
import styles from './style.scss'


class RatingIterm extends React.Component {

    render() {

        const {rating} = this.props;
        return (

            <div className={[styles.randerBack, styles.fontColor].join(' ')}>
                <span>{ rating.rank }</span>
                <span>|</span>
                <span>{ rating.name }</span>
                <span>|</span>
                <span>{ rating.elo }</span>
            </div>

        );

    }

}

export default RatingIterm;
