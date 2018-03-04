var angular = require("angular");
var angularRoute = require("angular-route");
var angularResource = require("angular-resource");
var PhoenicisConfigModule = require("../config/index");

require("./index.html");
require("./news.scss");

module.exports = {
    angularModule: angular.module("PhoenicisNews", [angularRoute, angularResource, PhoenicisConfigModule.angularModule.name])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider
                .when("/news", {
                    templateUrl: "news/index.html",
                    controller: "NewsController"
                });
        }])

        .factory("NewsREST", ['$resource', 'PHOENICIS_API', function ($resource, PHOENICIS_API) {
            return $resource(PHOENICIS_API + '/news/:id', {
                id: "@id"
            });
        }])

        .controller("NewsController", ['$scope', 'NewsREST', 'CurrentLocale', function ($scope, NewsREST, CurrentLocale) {
            NewsREST.query(function(newsItems) {
                $scope.newsItems = newsItems;
                $scope.currentLocale = CurrentLocale;
            });
        }])
};