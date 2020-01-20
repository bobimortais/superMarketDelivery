const proxy = [
    {
      context: '/addItemToDelivery',
      target: 'http://localhost:8080'
    }
  ];
  module.exports = proxy;