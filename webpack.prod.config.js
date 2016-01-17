/**
 * Helpers rootDir(), and nodeDir()
 * are defined at the bottom.
 */
var path = require('path');
var webpack = require('webpack');
var ENV = process.env.NODE_ENV = process.env.ENV = 'production';

/**
 * Webpack Plugins
 */
var ProvidePlugin = require('webpack/lib/ProvidePlugin');
var DefinePlugin = require('webpack/lib/DefinePlugin');
var OccurenceOrderPlugin = require('webpack/lib/optimize/OccurenceOrderPlugin');
var DedupePlugin = require('webpack/lib/optimize/DedupePlugin');
var UglifyJsPlugin = require('webpack/lib/optimize/UglifyJsPlugin');
var CommonsChunkPlugin = require('webpack/lib/optimize/CommonsChunkPlugin');
var CopyWebpackPlugin = require('copy-webpack-plugin');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var WebpackMd5Hash    = require('webpack-md5-hash');

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
  debug: false,
  verbose: true,
  stats: {
    colors: true,
    reasons: true
  },

  //
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
    filename: '[name].[chunkhash].js',
    sourceMapFilename: '[name].[chunkhash].map',
    chunkFilename: '[id].[chunkhash].chunk.js'
  },

  resolve: {
    root: __dirname,
    // Ensure loader extensions match.
    extensions: ['','.ts','.js','.json', '.css', '.html'],
    alias: {
      'app':    srcDir('app'),
      'jquery': nodeDir('jquery/dist/jquery')
    },
    cache: false
  },

  module: {
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

        // Remove TypeScript helpers to be injected below by DefinePlugin.
        'compilerOptions': {
          'removeComments': true,
          'noEmitHelpers': true,
        },

        query: {
          'ignoreWarnings': [
            //2403, // 2403 -> Subsequent variable declarations.
            //2300, // 2300 -> Duplicate identifier.
            //2374, // 2374 -> Duplicate number index signature.
            //2375  // 2375 -> Duplicate string index signature.
          ]
        },

        exclude: [/\.(spec|e2e)\.ts$/]
      }
    ],
    noParse: [ /zone\.js\/dist\/.+/, /angular2\/bundles\/.+/ ]
  },

  plugins: [
    new WebpackMd5Hash(),
    new DedupePlugin(),
    new OccurenceOrderPlugin(true),
    new ProvidePlugin({
      "jQuery": "jquery",
      "$":      "jquery",
      // TypeScript helpers
      '__metadata': 'ts-helper/metadata',
      '__decorate': 'ts-helper/decorate',
      '__awaiter': 'ts-helper/awaiter',
      '__extends': 'ts-helper/extends',
      '__param': 'ts-helper/param',
      'Reflect': 'es7-reflect-metadata/dist/browser'
    }),
    new CommonsChunkPlugin({
      name: 'vendor',
      minChunks: Infinity,
      filename: 'vendor.js'
    }),
    // Copy assets.
    //new CopyWebpackPlugin([{ from: 'src/assets', to: 'assets' }]),
    // Generating html.
    //new HtmlWebpackPlugin({ template: 'src/index.html'}),
    // Replace
    new DefinePlugin({
      'process.env': {
        'ENV': JSON.stringify(metadata.ENV),
        'NODE_ENV': JSON.stringify(metadata.ENV)
      }
    }),
    new UglifyJsPlugin({
      // beautify: true,
      mangle: false,
      comments: false,
      compress : {
        screw_ie8 : true
      },
      // TODO(gdi2290): uncomment in beta.2.
      //mangle: {
      //  screw_ie8 : true
      //}
    })
  ],

  // We need this due to problems with es6-shim
  node: {
    global: 'window',
    progress: false,
    crypto: 'empty',
    module: false,
    clearImmediate: false,
    setImmediate: false
  }

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
