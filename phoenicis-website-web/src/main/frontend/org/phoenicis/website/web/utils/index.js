Array.prototype.contains = function (element) {
    for (i in this) {
        if (this[i] === element) {
            return true;
        }
    }
    return false;
};

String.prototype.contains = function (string) {
    return -1 !== String.prototype.indexOf.call(this, string, 0);
};

