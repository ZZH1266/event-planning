'use strict'
const path = require('path')

function resolve(dir) {
  return path.join(__dirname, dir)
}

const CompressionPlugin = require('compression-webpack-plugin')

const name = process.env.VUE_APP_TITLE

const port = process.env.port || process.env.npm_config_port || 80 // �˿�

// vue.config.js ����˵��
//�ٷ�vue.config.js �ο��ĵ� https://cli.vuejs.org/zh/config/#css-loaderoptions
// ����ֻ��һ���֣��������òο��ĵ�
module.exports = {
  // �������������Ϳ��������µ�URL��
  // Ĭ������£�Vue CLI ��������Ӧ���Ǳ�������һ�������ĸ�·����
  publicPath: process.env.NODE_ENV === "production" ? "/" : "/",
  // ��npm run build �� yarn build ʱ �������ļ���Ŀ¼���ƣ�Ҫ��baseUrl����������·��һ�£���Ĭ��dist��
  outputDir: 'dist',
  // ���ڷ������ɵľ�̬��Դ (js��css��img��fonts) �ģ�����Ŀ���֮�󣬾�̬��Դ���������ļ����£�
  assetsDir: 'static',
  // �Ƿ���eslint�����⣬��Чֵ��ture | false | 'error'
  lintOnSave: process.env.NODE_ENV === 'development',
  // ����㲻��Ҫ���������� source map�����Խ�������Ϊ false �Լ�����������������
  productionSourceMap: false,
  // webpack-dev-server �������
  devServer: {
    host: '0.0.0.0',
    port: port,
    open: true,
    proxy: {
      // detail: https://cli.vuejs.org/config/#devserver-proxy
      [process.env.VUE_APP_BASE_API]: {
        target: `http://localhost:9090`,
        changeOrigin: true,
        pathRewrite: {
          ['^' + process.env.VUE_APP_BASE_API]: ''
        }
      }
    },
    disableHostCheck: true
  },
  css: {
    loaderOptions: {
      sass: {
        sassOptions: { outputStyle: "expanded" }
      }
    }
  },
  configureWebpack: {
    name: name,
    resolve: {
      alias: {
        '@': resolve('src')
      }
    },
    plugins: [
      new CompressionPlugin({
        cache: false,                                  // �������ļ�����
        test: /\.(js|css|html|jpe?g|png|gif|svg)?$/i,  // ѹ���ļ���ʽ
        filename: '[path][base].gz[query]',            // ѹ������ļ���
        algorithm: 'gzip',                             // ʹ��gzipѹ��
        minRatio: 0.8,                                 // ѹ��������С�� 80% ���ļ����ᱻѹ��
        deleteOriginalAssets: false                    // ѹ����ɾ��ԭ�ļ�
      })
    ],
  },
  chainWebpack(config) {
    config.plugins.delete('preload') // TODO: need test
    config.plugins.delete('prefetch') // TODO: need test

    // set svg-sprite-loader
    config.module
        .rule('svg')
        .exclude.add(resolve('src/assets/icons'))
        .end()
    config.module
        .rule('icons')
        .test(/\.svg$/)
        .include.add(resolve('src/assets/icons'))
        .end()
        .use('svg-sprite-loader')
        .loader('svg-sprite-loader')
        .options({
          symbolId: 'icon-[name]'
        })
        .end()

    config.when(process.env.NODE_ENV !== 'development', config => {
      config
          .plugin('ScriptExtHtmlWebpackPlugin')
          .after('html')
          .use('script-ext-html-webpack-plugin', [{
            // `runtime` must same as runtimeChunk name. default is `runtime`
            inline: /runtime\..*\.js$/
          }])
          .end()

      config.optimization.splitChunks({
        chunks: 'all',
        cacheGroups: {
          libs: {
            name: 'chunk-libs',
            test: /[\\/]node_modules[\\/]/,
            priority: 10,
            chunks: 'initial' // only package third parties that are initially dependent
          },
          elementUI: {
            name: 'chunk-elementUI', // split elementUI into a single package
            test: /[\\/]node_modules[\\/]_?element-ui(.*)/, // in order to adapt to cnpm
            priority: 20 // the weight needs to be larger than libs and app or it will be packaged into libs or app
          },
          commons: {
            name: 'chunk-commons',
            test: resolve('src/components'), // can customize your rules
            minChunks: 3, //  minimum common number
            priority: 5,
            reuseExistingChunk: true
          }
        }
      })
      config.optimization.runtimeChunk('single')
    })
  }
}
