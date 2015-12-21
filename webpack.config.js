/**
 * Helpers rootDir(), and NodeDir()
 * are defined at the bottom.
 */
var sliceArgs = Function.prototype.call.bind(Array.prototype.slice);
var toString  = Function.prototype.call.bind(Object.prototype.toString);
var path = require('path');
var webpack = require('webpack');

/**
 * Webpack Plugins
 */
var CommonsChunkPlugin   = webpack.optimize.CommonsChunkPlugin;
var ProvidePlugin  = webpack.ProvidePlugin;

/**
 * Config
 */
module.exports = {
  // for faster builds use 'eval'
  devtool: 'source-map',
  context: __dirname,
  debug: true,
  verbose: true,
  stats: {
    colors: true,
    reasons: true
  },

  //
  entry: {
    // to ensure these modules are grouped together in one file
    'vendor': [
      // angular, zone, reflect-metadata, rxjs, ...
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
      // App Page
      'app/bootstrap'
    ],
    'login': [
      // Login Page
      'app/login'
    ]
  },

  output: {
    path: rootDir('src/main/resources/static/build'),
    publicPath: "build/",
    filename: '[name].js',
    sourceMapFilename: '[name].map',
    chunkFilename: '[id].chunk.js'
  },

  resolve: {
    root: __dirname,
    // ensure loader extensions match
    extensions: ['','.ts','.js','.json', '.css', '.html'],
    alias: {
      'app':    'src/main/resources/static/app',
      'jquery': 'semantic-ui-less/node_modules/jquery/dist/jquery'
    }
  },

  module: {
    loaders: [
      // Support for *.json files.
      { test: /\.json$/, loader: 'json' },

      // Support for CSS as document style.
      { test: /\.css$/, loader: 'style!css' },

      // Support for Less as document style.
      { test: /\.less$/, loader: 'style!css!less' },

      // support for .html as raw text.
      { test: /\.html$/, loader: 'raw' },

      // support for fonts as raw.
      { test: /\.(eot)|(ttf)|(woff)|(woff2)$/, loader: 'file?name=[name].[ext]' },

      // support for images as raw.
      { test: /\.(png)|(svg)$/, loader: 'file?name=[name].[ext]' },

      // Support for .ts files.
      {
        test: /\.ts$/,

        loader: 'ts',

        query: {
          'ignoreWarnings': [
            //2300, // 2300 -> Duplicate identifier
            //2309, // 2309 -> An export assignment cannot be used in a module with other exported elements.
          ]
        },

        exclude: [
          /\.spec\.ts$/,
          /\.e2e\.ts$/,
          /node_modules/
        ]
      }
    ],
    noParse: [ /zone\.js\/dist\/.+/, /angular2\/bundles\/.+/ ]
  },

  plugins: [
    new ProvidePlugin({
      "jQuery": "jquery",
      "$":      "jquery"
    }),
    new CommonsChunkPlugin({
      name: 'vendor',
      minChunks: Infinity,
      filename: 'vendor.js'
    }),
    new CommonsChunkPlugin({
      name: 'common',
      filename: 'common.js',
      minChunks: 3,
      chunks: ['app', 'login', 'vendor']
    })
  ],

  // our Webpack Development Server config
  devServer: {
    historyApiFallback: true,
    contentBase: rootDir('src/main/resources/static/build'),
    publicPath: "build/"
  }

};


// Helpers
function rootDir(args) {
  args = sliceArgs(arguments, 0);
  return path.join.apply(path, [__dirname].concat(args));
}
function NodeDir(args) {
  args = sliceArgs(arguments, 0);
  return rootDir.apply(path, ['node_modules'].concat(args));
}
