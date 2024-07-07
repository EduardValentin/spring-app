const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/PS',
    createProxyMiddleware({
      target: 'http://serverside:8080/',
      changeOrigin: true,
    })
  );
};