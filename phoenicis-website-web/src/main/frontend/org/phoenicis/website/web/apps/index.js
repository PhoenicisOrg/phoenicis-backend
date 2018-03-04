var angular = require("angular");
var angularRoute = require("angular-route");
var angularResource = require("angular-resource");
var PhoenicisConfigModule = require("../config/index");

require("./index.html");
require("./apps.scss");

module.exports = {
    angularModule: angular.module("PhoenicisApps", [angularRoute, angularResource, PhoenicisConfigModule.angularModule.name])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider
                .when("/apps", {
                    templateUrl: "apps/index.html",
                    controller: "AppsController"
                })
                .when("/apps/:categoryName", {
                    templateUrl: "apps/index.html",
                    controller: "AppsCategoryController"
                });
        }])

        .factory("AppsREST", ['$resource', 'PHOENICIS_API', function ($resource, PHOENICIS_API) {
            return $resource(PHOENICIS_API + '/apps/:categoryId', {
                categoryId: "@categoryId"
            });
        }])

        .controller("AppsController", ['$scope', 'AppsREST', 'CurrentLocale', function ($scope, AppsREST, CurrentLocale) {
            AppsREST.query(function(categories) {
                $scope.categories = categories;
                $scope.currentLocale = CurrentLocale;
            });
        }])

        .controller("AppsCategoryController", ['$scope', '$routeParams', 'AppsREST', 'CurrentLocale', function ($scope, $routeParams, AppsREST, CurrentLocale) {
            AppsREST.query(function(categories) {
                $scope.categories = categories;
                $scope.currentLocale = CurrentLocale;

                categories.forEach(function(category) {
                    if(category.name.toLowerCase() === $routeParams.categoryName) {
                        $scope.selectedCategory = category;
                    }
                })
            });
        }])
};