angular.module('perlerbeadsApp').factory('favoriteDesignResource', ['$resource',

    function ($resource) {
        var baseApi = 'api/favorite/';
        var params = {};
        var actions = {
            save: {
                method: 'POST',
                params: {
                    'designId': '@designId'
                },
                url: baseApi + '/:designId'
            },
            delete: {
                method: 'DELETE',
                params: {
                    'designId': '@designId'
                },
                url: baseApi + '/:designId'
            }

        };
        return $resource(baseApi, params, actions);
    }]);
