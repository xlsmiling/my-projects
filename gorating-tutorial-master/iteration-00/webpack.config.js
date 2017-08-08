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