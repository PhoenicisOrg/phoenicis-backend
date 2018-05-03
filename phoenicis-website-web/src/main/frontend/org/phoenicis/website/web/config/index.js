var angular = require("angular");

module.exports = {
    angularModule: angular.module("PhoenicisConfig", [])
        .constant("PHOENICIS_API", "api/v5.0")
        .constant("ALLOWED_LOCALES", ["en", "fr"])

        .factory("CurrentLocale", ['$location', 'ALLOWED_LOCALES', function($location, ALLOWED_LOCALES) {
            var detectedLocale = $location.host().split('.')[0];

            if(ALLOWED_LOCALES.contains(detectedLocale)) {
                return detectedLocale;
            } else {
                return navigator.language || navigator.userLanguage;
            }
        }])
};
