const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  devServer: {
    port: 4500,
    proxy: {
      "^/api": {
        target: "http://localhost:8080",
        changeOrigin: true,
        logLevel: "debug",
        pathRewrite: { "^/api": "/" },
      },
    },
  },
  transpileDependencies: ["vuetify"],
  configureWebpack: (config) => {
    if (process.env.NODE_ENV === "development") {
      config.devtool = "eval-source-map";

      config.output.devtoolModuleFilenameTemplate = (info) =>
        info.resourcePath.match(/^\.\/\S*?\.vue$/)
          ? `webpack-generated:///${info.resourcePath}?${info.hash}`
          : `webpack-yourCode:///${info.resourcePath}`;

      config.output.devtoolFallbackModuleFilenameTemplate =
        "webpack:///[resource-path]?[hash]";
    }
  },
});
