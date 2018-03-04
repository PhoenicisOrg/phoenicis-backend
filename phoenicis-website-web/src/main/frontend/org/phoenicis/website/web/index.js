require("./index.html");
require('./utils/index.js');

var angular = require("angular");
var angularRoute = require("angular-route");
var angularSanitize = require("angular-sanitize");

var PhoenicisUi = require("./ui/index.js");
var PhoenicisNews = require("./news/index.js");
var PhoenicisHome = require("./home/index.js");
var PhoenicisLegacy = require("./legacy/index");
var PhoenicisApps = require("./apps/index");

angular.module("PhoenicisWebsite", [
    angularRoute,
    angularSanitize,
    PhoenicisUi.angularModule.name,
    PhoenicisNews.angularModule.name,
    PhoenicisHome.angularModule.name,
    PhoenicisLegacy.angularModule.name,
    PhoenicisApps.angularModule.name
])
    .config(['$locationProvider', function ($locationProvider) {
        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        });
    }]);