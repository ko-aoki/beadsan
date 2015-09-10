angular.module('perlerbeadsApp').factory('beadResource', ['$resource',

    function ($resource) {
        var baseApi = 'api/bead/';
        var params = {};
        var actions = {
            hasDuplicated: {
                method: 'GET',
                url: baseApi + 'designName/:designName'
            },
            save: {
                method: 'POST',
                params: {
                    'name': '@name',
                    'paletteId': '@paletteId',
                    'design': '@design'
                },
                url: baseApi
            }
        };
        return $resource(baseApi, params, actions);
    }]);
