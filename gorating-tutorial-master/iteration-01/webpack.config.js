const HtmlWebpackPlugin = require('html-webpack-plugin');
const webpack = require('webpack');
const { HotModuleReplacementPlugin } = webpack;

const config = {
  entry: `${__dirname}/src/index.jsx`,
  output: {
    path: `${__dirname}/dist`,
    filename: 'index.bundle.js',
  },
  resolve: {
    extensions: ['.js', '.jsx']
  },
  module: {
    rules: [
      {
        test: /\.js[x]?$/,// /\.(js|jsx)$/
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['es2015','react'],
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
    ],
  },
  plugins: [
    new HtmlWebpackPlugin ({
      template: __dirname+'/src/index.html',
    }),
    new HotModuleReplacementPlugin(),
  ],
  devtool: 'eval-source-map',
  devServer: {
    contentBase: `${__dirname}/dist`,
    port: 3000,
    hot: true,
  },
};

module.exports = config;