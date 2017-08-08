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