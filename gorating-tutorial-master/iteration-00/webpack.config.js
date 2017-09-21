//webpack
// const config = {
//   entry: './src/index.js',
//   output: {
//     path: __dirname + '/dist',
//     filename: 'index.bundle.js',
//   },
// };

//babel
const config = { // ...
    entry: `${__dirname}/src/index.jsx`,
    output: {
        path: `${__dirname}/dist`,
        filename: 'index.bundle.js',
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: 'es2015',
                    },
                },
            },
        ],
    },
};
module.exports = config;