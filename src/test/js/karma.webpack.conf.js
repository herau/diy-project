var webpack = require('webpack');
var ENV = process.env.ENV = process.env.NODE_ENV = 'test';

/**
 * Webpack Plugins
 */
var DefinePlugin   = webpack.DefinePlugin;
var ProvidePlugin  = webpack.ProvidePlugin;

/**
 * Config
 */
module.exports = {
  devtool: 'inline-source-map',
  context: __dirname,
  debug: false,
  verbose: true,

  stats: {
    colors: true,
    reasons: true
  },

  resolve: {
    // Ensure loader extensions match.
    extensions: ['','.ts','.js','.json', '.css', '.html'],
    cache: false
  },

  module: {
    loaders: [
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

        exclude: [/\.e2e\.ts$/, /node_modules/]
      },
      { test: /\.json$/, loader: 'json-loader' },
      { test: /\.html$/, loader: 'raw-loader' },
      { test: /\.css$/,  loader: 'raw-loader' }
    ],
    postLoaders: [
      // instrument only testing sources with Istanbul
      {
        test: /\.(js|ts)$/,
        include: '../../main/resources/static/app',
        loader: 'istanbul-instrumenter-loader',
        exclude: [
          /\.e2e\.ts$/,
          /node_modules/
        ]
      }
    ],
    noParse: [ /zone\.js\/dist\/.+/, /angular2\/bundles\/.+/ ]
  },

  plugins: [
    new DefinePlugin({
      // Environment helpers
      'process.env': {
        'ENV': JSON.stringify(ENV),
        'NODE_ENV': JSON.stringify(ENV)
      },
      'global': 'window',
      // TypeScript helpers.
      '__metadata': 'Reflect.metadata',
      '__decorate': 'Reflect.decorate'
    }),
    new ProvidePlugin({
      // '__metadata': 'ts-helper/metadata',
      // '__decorate': 'ts-helper/decorate',
      '__awaiter': 'ts-helper/awaiter',
      '__extends': 'ts-helper/extends',
      '__param': 'ts-helper/param',
      'Reflect': 'es7-reflect-metadata/dist/browser'
    })
  ],

  // We need this due to problems with es6-shim.
  node: {
    global: 'window',
    progress: false,
    crypto: 'empty',
    module: false,
    clearImmediate: false,
    setImmediate: false
  }

};
