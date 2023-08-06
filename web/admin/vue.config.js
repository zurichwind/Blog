module.exports = {
  publicPath: './',
  productionSourceMap: false,
  devServer: {
    host: 'localhost',
    port: 8000,
    proxy: {
      "/api": {
        target: "http://localhost:8090",
        changeOrigin: true,
        pathRewrite: {
          "^/api": "",
        },
      },
    },
    disableHostCheck: true,
  },
  chainWebpack: (config) => {
    config.resolve.alias.set("@", resolve("src"));
  },
};

const path = require("path");
function resolve(dir) {
  return path.join(__dirname, dir);
}
