/**
 * Helpers rootDir(), and nodeDir()
 * are defined at the bottom.
 */
var path = require('path');
var webpack = require('webpack');
var ENV = process.env.ENV = process.env.NODE_ENV = 'development';

/**
 * Webpack Plugins
 */
var CommonsChunkPlugin   = webpack.optimize.CommonsChunkPlugin;
var ProvidePlugin        = webpack.ProvidePlugin;
var OccurenceOrderPlugin = webpack.optimize.OccurenceOrderPlugin;
var DefinePlugin         = webpack.DefinePlugin;
var CopyWebpackPlugin    = require('copy-webpack-plugin');
var HtmlWebpackPlugin    = require('html-webpack-plugin');

var metadata = {
  title: 'DIY',
  baseUrl: '/',
  ENV: ENV
};

/**
 * Config
 */
module.exports = {
  // Static data for index.html.
  metadata: metadata,
  // For faster builds use 'eval'.
  devtool: 'source-map',
  context: __dirname,
  debug: true,
  verbose: true,
  stats: {
    colors: true,
    reasons: true
  },

  entry: {
    // To ensure these modules are grouped together in one file.
    'vendor': [
      // Angular, zone, reflect-metadata, rxjs, ...
      'app/vendor'
    ],
    'semantic': [
      'semantic-ui-less/definitions/globals/site',
      'semantic-ui-less/definitions/behaviors/api',
      'semantic-ui-less/definitions/behaviors/colorize',
      'semantic-ui-less/definitions/behaviors/form',
      'semantic-ui-less/definitions/behaviors/state',
      'semantic-ui-less/definitions/behaviors/visibility',
      'semantic-ui-less/definitions/behaviors/visit',
      'semantic-ui-less/definitions/modules/accordion',
      'semantic-ui-less/definitions/modules/checkbox',
      'semantic-ui-less/definitions/modules/dimmer',
      'semantic-ui-less/definitions/modules/dropdown',
      'semantic-ui-less/definitions/modules/embed',
      'semantic-ui-less/definitions/modules/modal',
      'semantic-ui-less/definitions/modules/nag',
      'semantic-ui-less/definitions/modules/popup',
      'semantic-ui-less/definitions/modules/progress',
      'semantic-ui-less/definitions/modules/rating',
      'semantic-ui-less/definitions/modules/search',
      'semantic-ui-less/definitions/modules/shape',
      'semantic-ui-less/definitions/modules/sidebar',
      'semantic-ui-less/definitions/modules/sticky',
      'semantic-ui-less/definitions/modules/tab',
      'semantic-ui-less/definitions/modules/transition',
      'semantic-ui-less/definitions/modules/video',
      'semantic-ui-less/semantic.less'
    ],
    'app': [
      // App Page.
      'app/bootstrap'
    ],
    'login': [
      // Login Page.
      'app/login'
    ]
  },

  output: {
    path: srcDir('build'),
    publicPath: "build/",
    filename: '[name].js',
    sourceMapFilename: '[name].map',
    chunkFilename: '[id].chunk.js'
  },

  resolve: {
    root: __dirname,
    // Ensure loader extensions match.
    extensions: ['','.ts','.js','.json', '.css', '.html'],
    alias: {
      'app':    srcDir('app'),
      'jquery': nodeDir('jquery/dist/jquery')
    }
  },

  module: {
    preLoaders: [
      // Enable lint before build.
      /*{
        test: /\.ts$/,
        loader: 'tslint-loader',
        exclude: [/node_modules/]
      }*/,
      // Rewire source map files of libraries, use to debug into 3rd party libraries.
      {
        test: /\.js$/,
        include: [
          path.resolve(__dirname, "node_modules", "angular2")
          // Add more as needed or replace to include all modules:
          // path.resolve(__dirname, "node_modules")
        ],
        loader: "source-map-loader"
      }
    ],
    loaders: [
      // Support for *.json files.
      { test: /\.json$/, loader: 'json' },

      // Support for CSS as document style.
      { test: /\.css$/, loader: 'style!css' },

      // Support for Less as document style.
      { test: /\.less$/, loader: 'style!css!less' },

      // Support for .html as raw text.
      { test: /\.html$/, loader: 'raw' },

      // Support for fonts as raw.
      { test: /\.(eot)|(ttf)|(woff)|(woff2)$/, loader: 'file?name=[name].[ext]' },

      // Support for images as raw.
      { test: /\.(png)|(svg)$/, loader: 'file?name=[name].[ext]' },

      // Support for .ts files.
      {
        test: /\.ts$/,

        loader: 'ts',

        exclude: [/\.(spec|e2e)\.ts$/, /node_modules\/(?!(ng2-.+))/]
      }
    ],
    noParse: [ /zone\.js\/dist\/.+/, /angular2\/bundles\/.+/ ]
  },

  plugins: [
    new OccurenceOrderPlugin(true),
    new ProvidePlugin({
      "jQuery": "jquery",
      "$":      "jquery"
    }),
    new CommonsChunkPlugin({
      name: 'vendor',
      minChunks: Infinity,
      filename: 'vendor.js'
    }),
    // Copy Semantic config (does not work because it's performed after build step).
    //new CopyWebpackPlugin([
    //  { from: nodeDir('semantic-ui-less'),      to: 'semantic'},               // Copy Semantic sources to build folder.
    //  { from: srcDir('semantic/site'),          to: 'semantic/site' },         // Copy Semantic config to build folder.
    //  { from: srcDir('semantic/theme.config'),  to: 'semantic/theme.config' }  // Copy Semantic config to build folder.
    //]),
    // Generating html.
    //new HtmlWebpackPlugin({ template: 'src/index.html', inject: false }),
    // Replace.
    new DefinePlugin({
      'process.env': {
        'ENV': JSON.stringify(metadata.ENV),
        'NODE_ENV': JSON.stringify(metadata.ENV)
      }
    })
  ]

};

// Helpers.

function rootDir(args) {
  args = Array.prototype.slice.call(arguments, 0);
  return path.join.apply(path, [__dirname].concat(args));
}

function nodeDir(args) {
  args = Array.prototype.slice.call(arguments, 0);
  return rootDir.apply(path, ['node_modules'].concat(args));
}

function srcDir(args) {
  args = Array.prototype.slice.call(arguments, 0);
  return rootDir.apply(path, ['src/main/resources/static'].concat(args));
}

