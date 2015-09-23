/**
 * @ngdoc overview
 * @name perlerbeadsApp
 * @description
 * # perlerbeadsApp
 *
 * Main module of the application.
 */
angular
    .module('perlerbeadsApp', [
      'ngAnimate',
      'ngCookies',
      'ngResource',
      'ngRoute',
      'ngSanitize',
      'ngTouch',
      'ui.bootstrap',
      'palette'
    ])
    .config(function ($routeProvider) {
      $routeProvider
          .when('/', {
            templateUrl: 'views/main.html',
            controller: 'MainCtrl'
          })
          .when('/about', {
            templateUrl: 'views/about.html',
            controller: 'AboutCtrl'
          })
          .when('/find', {
              templateUrl: 'views/finder.html',
              controller: 'FinderCtrl'
          })
          .otherwise({
            redirectTo: '/'
          });
    });
