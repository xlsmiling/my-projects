/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

const ratingList = __webpack_require__(1);
const renderRatings = __webpack_require__(2);
window.onload = function() {
    document.getElementById('root').innerHTML = renderRatings(ratingList);
};

/***/ }),
/* 1 */
/***/ (function(module, exports) {

module.exports = [
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

/***/ }),
/* 2 */
/***/ (function(module, exports) {

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

module.exports = renderRatings;

/***/ })
/******/ ]);