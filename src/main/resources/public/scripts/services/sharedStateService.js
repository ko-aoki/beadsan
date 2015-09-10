/**
 * Created by ko-aoki on 2015/08/08.
 */
angular.module('perlerbeadsApp').factory('sharedStateService', [
    function () {
        var data = [];
        return {
            data : data,
            get: function (key) {
                return data[key];
            },
            put: function (key, value) {
                data[key] = value;
            }
        };
    }]);
