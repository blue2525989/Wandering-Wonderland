var app = angular.module('mainApp', [ 'ngRoute', 'ngCookies', 'ngResource']);

app.config(function($routeProvider, $httpProvider, $sceProvider) {
	
	$sceProvider.enabled(false);
	
	$routeProvider.when('/', {
		/* create a spring controller to hit URL */
		templateUrl : '/home',
		controller : 'index',
		controllerAs: 'controller',
		requireLogin : false
	}).when('/contact', {
		templateUrl : '/contact',
		controller: 'contact',
		controllerAs: 'controller',
		requireLogin : false
	}).when('/about', {
		templateUrl : '/about',
		requireLogin : false
	}).when('/shop', {
		templateUrl : '/shop',
		requireLogin: false
	}).otherwise('/');
	
	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
});