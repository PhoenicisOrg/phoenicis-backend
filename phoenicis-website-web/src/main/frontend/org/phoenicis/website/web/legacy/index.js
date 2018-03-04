var angular = require("angular");

module.exports = {
    angularModule: angular.module("PhoenicisLegacy", [])
        .filter('bbdecode', function(){
            return function(stringToDecode) {
                var stringToDecodeWithoutBr = stringToDecode.replace("<br>", "\n");

                if(!stringToDecodeWithoutBr.contains(">") && !stringToDecodeWithoutBr.contains("<")) {
                    stringToDecode = stringToDecode.replace(/\n/g, "<br />");
                }

                stringToDecode = stringToDecode.replace(/\[url=([^\s\]]+)\s*\](.*(?=\[\/url\]))\[\/url\]/g, '<a href="$1">$2</a>');
                stringToDecode = stringToDecode.replace(/\[b\](.*?)\[\/b\]/g, '<b>$1</b>');
                stringToDecode = stringToDecode.replace(/\[url\](.*?)\[\/url\]/g, '<a href="$1">$1</a>');
                stringToDecode = stringToDecode.replace(/\[flottante=left\](.*?)\[\/flottante\]/g, '<div style="float:left;">$1</div>');
                stringToDecode = stringToDecode.replace(/\[img\](.*?)\[\/img\]/g, '<img src="$1">');

                return stringToDecode;
            };
        })
};