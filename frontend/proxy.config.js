const proxy = [
    {
      context: '/addItemToDelivery',
      target: 'http://localhost:8081'
    },
    {
      context: '/updateDelivery',
      target: 'http://localhost:8081'
    }
  ];
  module.exports = proxy;