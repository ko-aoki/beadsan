angular.module('perlerbeadsApp').factory('beadResource', ['$resource',

    function ($resource) {
        var baseApi = 'api/bead/';
        var params = {};
        var actions = {
            hasDuplicated: {
                method: 'GET',
                url: baseApi + 'designName/:designName'
            },
            find: {
                method: 'GET',
                url: baseApi + 'find'
            },
            save: {
                method: 'POST',
                params: {
                    'name': '@name',
                    'paletteId': '@paletteId',
                    'design': '@design'
                },
                url: baseApi
            },
            deleteByName: {
                method: 'DELETE',
                url: baseApi + 'designName/:designName'
            },
        };
        return $resource(baseApi, params, actions);
    }]);
