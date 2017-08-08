/**
 * @param rating {{ id, name, country, gender, rank, elo }}
 * */
function renderRatingItem(rating) {
    return [
        '<div>',
        '  <span>' + rating.rank + '</span>',
        '  <span>|</span>',
        '  <span>' + rating.name + '</span>',
        '  <span>|</span>',
        '  <span>' + rating.elo + '</span>',
        '</div>',
    ].join('\n');
}

function renderRatings(ratings) {
    return [
        '<div>',
        ratings.map(renderRatingItem).join('\n'),
        '</div>',
    ].join('\n');
}

export default renderRatings;
//module.exports = renderRatings;