// @AngularClass

/*
 * Helpers env(), getBanner(), root(), and rootNode()
 * are defined at the bottom.
 */
var sliceArgs = Function.prototype.call.bind(Array.prototype.slice);
var toString  = Function.prototype.call.bind(Object.prototype.toString);
var NODE_ENV  = process.env.NODE_ENV || 'development';

// Polyfill
Object.assign = require('object-assign');

// Node
var pkg = require('./package.json');
var path = require('path');
var webpack = require('webpack');

// Webpack Plugins
var OccurenceOrderPlugin = webpack.optimize.OccurenceOrderPlugin;
var CommonsChunkPlugin   = webpack.optimize.CommonsChunkPlugin;
var UglifyJsPlugin = webpack.optimize.UglifyJsPlugin;
var DedupePlugin   = webpack.optimize.DedupePlugin;
var DefinePlugin   = webpack.DefinePlugin;
var ProvidePlugin  = webpack.ProvidePlugin;
var BannerPlugin   = webpack.BannerPlugin;


/*
 * Config
 */

module.exports = {
  devtool: env({
    'development': 'eval',
    'all': 'source-map'
  }),

  debug: env({
    'development': true,
    'all': false
  }),
  cache: env({
    // 'development': false
    'all': true
  }),
  verbose: true,
  displayErrorDetails: true,
  context: __dirname,
  stats: env({
    'all': {
      colors: true,
      reasons: true
    }
  }),

  // our Development Server config
  devServer: {
    inline: true,
    colors: true,
    historyApiFallback: true,
    contentBase: 'src/main/resources/static',
    publicPath: 'src/main/resources/static/build'
  },

  //
  entry: {
    // to ensure these modules are grouped together in one file
    'angular2': [
      '@reactivex/rxjs',
      'zone.js',
      'reflect-metadata',
      'angular2/angular2',
      'angular2/core',
      'angular2/router',
      'angular2/http',
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
      'semantic-ui-less/semantic.less',
    ],
    'app': [
      // App
      'app/bootstrap'
    ],
    'login': [
      // App
      'app/login'
    ]
  },

  // Config for our build files
  output: {
    path: root('src/main/resources/static/build'),
    publicPath: "/build/",
    filename: env({
      'development': '[name].js',
      'all': '[name].[hash].min.js'
    }),
    sourceMapFilename: env({
      'development': '[name].js.map',
      'all': '[name].[hash].min.js.map'
    }),
    chunkFilename: '[id].chunk.js'
  },

  resolve: {
    root: __dirname,
    extensions: ['','.ts','.js','.json'],
    alias: {
      'rx': '@reactivex/rxjs',
      'app':    'src/main/resources/static/app',
      'jquery': 'semantic-ui-less/node_modules/jquery/dist/jquery'
      // 'app': 'src/app',
      // 'common': 'src/common',
      // 'bindings': 'src/bindings',
      // 'components': 'src/app/components'
      // 'services': 'src/app/services',
      // 'stores': 'src/app/stores'
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
          /\.min\.js$/,
          /\.spec\.ts$/,
          /\.e2e\.ts$/,
          /node_modules/
        ]
      }
    ],
    noParse: [
      /rtts_assert\/src\/rtts_assert/,
      /reflect-metadata/
    ]
  },

  plugins: env({
    'production': [
      new UglifyJsPlugin({
        compress: {
          warnings: false,
          drop_debugger: env({
            'development': false,
            'all': true
          })
        },
        output: {
          comments: false
        },
        beautify: false
      }),
      new BannerPlugin(getBanner(), {entryOnly: true})
    ],
    'all': [
      new DefinePlugin({
        'process.env.NODE_ENV': JSON.stringify(NODE_ENV),
        'VERSION': JSON.stringify(pkg.version)
      }),
      new ProvidePlugin({
        "jQuery": "jquery",
        "$":      "jquery",
      }),
      new OccurenceOrderPlugin(),
      new DedupePlugin(),
      new CommonsChunkPlugin({
        name: 'angular2',
        minChunks: Infinity,
        filename: env({
          'development': 'angular2.js',
          'all': 'angular2.min.js'
        })
      }),
      new CommonsChunkPlugin({
        name: 'common',
        filename: env({
          'development': 'common.js',
          'all': 'common.min.js'
        })
      })
    ]
  }),

  /*
   * When using `templateUrl` and `styleUrls` please use `__filename`
   * rather than `module.id` for `moduleId` in `@View`
   */
  node: {
    crypto: false,
    __filename: true
  }
};


// Helpers

function env(configEnv) {
  if (configEnv === undefined) { return configEnv; }
  switch (toString(configEnv[NODE_ENV])) {
    case '[object Object]'    : return Object.assign({}, configEnv.all || {}, configEnv[NODE_ENV]);
    case '[object Array]'     : return [].concat(configEnv.all || [], configEnv[NODE_ENV]);
    case '[object Undefined]' : return configEnv.all;
    default                   : return configEnv[NODE_ENV];
  }
}

function getBanner() {
  return 'DIY v'+ pkg.version +' by @f2i and @n27';
}

function root(args) {
  args = sliceArgs(arguments, 0);
  return path.join.apply(path, [__dirname].concat(args));
}
function rootNode(args) {
  args = sliceArgs(arguments, 0);
  return root.apply(path, ['node_modules'].concat(args));
}
