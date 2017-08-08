(function () {
  const ratingList = [
    {
      "id": "1195",
      "name": "Ke Jie",
      "country": "cn",
      "gender": "male",
      "rank": 1,
      "avatar": "",
      "elo": 3672
    },
    {
      "id": "1090",
      "name": "Park Junghwan",
      "country": "kr",
      "gender": "male",
      "rank": 2,
      "avatar": "",
      "elo": 3627
    },
    {
      "id": "1313",
      "name": "Shin Jinseo",
      "country": "kr",
      "gender": "male",
      "rank": 3,
      "avatar": "",
      "elo": 3562
    },
    {
      "id": "601",
      "name": "Iyama Yuta",
      "country": "jp",
      "gender": "male",
      "rank": 4,
      "avatar": "",
      "elo": 3553
    },
    {
      "id": "1155",
      "name": "Mi Yuting",
      "country": "cn",
      "gender": "male",
      "rank": 5,
      "avatar": "",
      "elo": 3534
    },
    {
      "id": "449",
      "name": "Shi Yue",
      "country": "cn",
      "gender": "male",
      "rank": 6,
      "avatar": "",
      "elo": 3532
    },
    {
      "id": "5",
      "name": "Lee Sedol",
      "country": "kr",
      "gender": "male",
      "rank": 7,
      "avatar": "",
      "elo": 3515
    },
    {
      "id": "297",
      "name": "Chen Yaoye",
      "country": "cn",
      "gender": "male",
      "rank": 8,
      "avatar": "",
      "elo": 3509
    },
    {
      "id": "995",
      "name": "Tan Xiao",
      "country": "cn",
      "gender": "male",
      "rank": 9,
      "avatar": "",
      "elo": 3504
    },
    {
      "id": "381",
      "name": "Zhou Ruiyang",
      "country": "cn",
      "gender": "male",
      "rank": 10,
      "avatar": "",
      "elo": 3502
    }
  ];

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

  window.onload = function() {
    document.getElementById('root').innerHTML = renderRatings(ratingList);
  };

})();