const proxy = [
    {
      context: '/addItemToDelivery',
      target: 'http://localhost:8081'
    },
    {
      context: '/updateDelivery',
      target: 'http://localhost:8081'
    },
    {
      context: '/getItems',
      target: 'http://localhost:8083'
    },
    {
      context: '/getDeliveries',
      target: 'http://localhost:8081'
    },
    {
      context: '/updateDelivery',
      target: 'http://localhost:8081'
    },
    {
      context: '/removeItemFromDelivery',
      target: 'http://localhost:8081'
    },
  ];
  module.exports = proxy;