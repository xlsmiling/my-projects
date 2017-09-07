import React from 'react';
import RatingIterm from '../RatingIterm'

class RatingList extends  React.Component {
    render() {
        const { dataList } = this.props;

        return (
            <div>
                {
                    dataList.map(rating => <RatingIterm key={rating.id} rating={rating} />)
                }
            </div>
        );
    }
}

export default RatingList;
