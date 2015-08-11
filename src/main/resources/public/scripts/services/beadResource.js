angular.module('perlerbeadsApp').factory('beadResource', ['$resource',

    function ($resource) {
        var baseApi = 'api/bead/';
        var params = null;
        var actions = {
            hasDuplicated: {
                method: 'GET',
                url: baseApi + 'designName/:designName'
            }
        };
        return $resource(baseApi, params, actions);
    }]);
