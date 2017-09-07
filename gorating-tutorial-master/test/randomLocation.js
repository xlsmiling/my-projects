const arr = [1,2,3,4,5,6,7,8,9,10,11,12,13,14];
let s = shuffle(arr); // [2,4,1,3,9,6,7,5,8]
console.log(s);

function shuffle(arr){
    const size =  arr.length;
    let usedIndices = new Array();
    let shuffledArr = new Array();
    while(shuffledArr.length < size){
        let randomIndex = Math.floor(Math.random()*size);
        if(usedIndices.indexOf(randomIndex) < 0){
            usedIndices.push(randomIndex);
            shuffledArr.push(arr[randomIndex]);
        }
    }
    return shuffledArr;
}

function shuffle2(arr){
    if (arr.length == 1)
        return arr;
    const randomIndex = Math.floor(Math.random() * arr.length);
    console.log(randomIndex);
    const left = arr.slice(0, randomIndex);
    const right = arr.slice(randomIndex + 1);
    const nextArr = left.concat(right);
    //console.log(shuffle2(nextArr));
    //const shuffledArr = shuffle2(nextdArr).concat(arr[randomIndex]);
    return shuffle2(nextArr).concat(arr[randomIndex]);
}

let shuffle3 = arr => arr
    .map(i => [i,Math.random()])    //为每个元素绑定一个随机数
    .sort((i1, i2) => i1[1] - i2[1])//按绑定的随机数顺序排序
    .map(i => i[0]);                //提取出原始元素
console.log(shuffle3(arr));