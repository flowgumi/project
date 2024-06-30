const {createProxyMiddleware} = require('http-proxy-middleware');
module.exports = function (app) {
    app.use('/httpServer', createProxyMiddleware({
        target: 'http:/localhost:8080',//后台服务器地址
        changeOrigin: true,
        pathRewrite: {
            '^/httpServer': 'http://localhost:8081',//本地地址
        },
    }))
}
