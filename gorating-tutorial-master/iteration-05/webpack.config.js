const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path');

const rootPath = __dirname;
const { HotModuleReplacementPlugin } = webpack;
const config = {
  entry: path.join(rootPath, 'src', 'index.jsx'),
  output: {
    path: path.join(rootPath, 'dist'),
    filename: 'index.bundle.js',
  },
  resolve: {
    extensions: ['.js', '.jsx']
  },
  module: {
    rules: [
      {
        test: /\.js[x]?$/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['es2015', 'react'],
          },
        },
      },
      {
        test: /\.json$/,
        use: 'json-loader',
      },
      {
        test: /\.css$/,
        use: ['style-loader' ,'css-loader?modules&localIdentName=[local]-[hash:base64:4]'],
      },
      {
        test: /\.scss$/,
        use: ['style-loader' ,'css-loader?modules&localIdentName=[local]-[hash:base64:4]', 'sass-loader'],
      },
      {
        test: /\.(png|jpg)$/,
        use: ['url-loader?limit=8192'],
      }
    ],
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: path.join(rootPath, 'src', 'index.html'),
    }),
    new HotModuleReplacementPlugin(),
  ],
  devtool: 'eval-source-map',
  devServer: {
    contentBase: path.join(rootPath, 'dist'),
    port: 3000,
    hot: true,
  },
};

module.exports = config;