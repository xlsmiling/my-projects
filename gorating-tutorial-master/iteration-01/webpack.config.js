const config = {
  entry: `${__dirname}/src/index.js`,
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
        }
      },
      {
        test: /\.json$/,
        use: 'json-loader'
      },
      {
        test: /\.css$/,
        use: ['style-loader' ,'css-loader?modules&localIdentName=[local]-[hash:base64:4]'],
      },
      {
        test: /\.scss$/,
        use: ['style-loader' ,'css-loader?modules&localIdentName=[local]-[hash:base64:4]', 'sass-loader'],
      }
    ]
  }
};

module.exports = config;