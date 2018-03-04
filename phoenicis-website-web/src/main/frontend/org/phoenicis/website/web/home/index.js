var angular = require("angular");
var angularRoute = require("angular-route");

require("./index.html");
require("./home.scss");

module.exports = {
    angularModule: angular.module("PhoenicisHome", [angularRoute])
        .config(['$routeProvider', function($routeProvider) {
            $routeProvider
                .when("/", {
                    templateUrl : "home/index.html",
                    controller: "HomeController"
                });
        }])

        .controller("HomeController", ['$scope', function($scope) {

        }])
};