var angular = require("angular");
var angularUi = require("angular-bootstrap-npm");

/* Font awesome */
require("font-awesome-sass-loader");

/* Roboto */
require("roboto-npm-webfont");

/* Bootstrap */
require("bootstrap-css");

require("./ui.scss");
require("./logo.svg");
require("./phoenicis.jpg");
require('./phoenicis_screenshot.png');
require("./phoenicis.svg");

module.exports = {
    angularModule: angular.module("PhoenicisUI", [angularUi])
        .filter('times', function(){
            return function(n) {
                var res = [];
                for (var i = 0; i < n; i++) {
                    res.push(i);
                }
                return res;
            };
        })

        .filter('stripTags', function() {
            return function(input) {
                return input ? input.replace("<br />", " ").replace("<br>", " ").replace(/<[^>]+>/gm, '') : '';
            }
        })

        .filter('fetchImageUrl', function() {
            return function(input) {
                var regex = /<img.*?src="(.*?)"/;
                var regex2 = /<img.*?src='(.*?)'/;

                var result = regex.exec(input);
                var result2 = regex2.exec(input);

                return result ? result[1] : result2 ? result2[1] : null;
            }
        })

        .filter('smallish', function() {
            return function(stringToMakeSmall, maxLen) {
                if (stringToMakeSmall.length <= maxLen) {
                    return stringToMakeSmall;
                }
                return stringToMakeSmall.substr(0, stringToMakeSmall.lastIndexOf(" ", maxLen)) + " ...";
            }
        })
};