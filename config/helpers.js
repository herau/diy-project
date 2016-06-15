var path = require('path');

// Helper functions
var ROOT = path.resolve(__dirname, '..');
var WEBAPP = path.resolve(__dirname, '../src/main/webapp');
var RESOURCES = path.resolve(__dirname, '../src/main/resources');

console.log('root directory:', root() + '\n');

function hasProcessFlag(flag) {
  return process.argv.join('').indexOf(flag) > -1;
}

function isWebpackDevServer() {
  return process.argv[1] && !! (/webpack-dev-server$/.exec(process.argv[1]));
}

function root(args) {
  args = Array.prototype.slice.call(arguments, 0);
  return path.join.apply(path, [ROOT].concat(args));
}

function webapp(args) {
  args = Array.prototype.slice.call(arguments, 0);
  return path.join.apply(path, [WEBAPP].concat(args));
}

function resources(args) {
  args = Array.prototype.slice.call(arguments, 0);
  return path.join.apply(path, [RESOURCES].concat(args));
}


exports.hasProcessFlag = hasProcessFlag;
exports.isWebpackDevServer = isWebpackDevServer;
exports.root = root;
exports.webapp = webapp;
exports.resources = resources;
