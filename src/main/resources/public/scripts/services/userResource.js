angular.module('perlerbeadsApp').factory('userResource', ['$resource',
    function ($resource) {
        var baseApi = 'api/user/';
        var params = {};
        var actions = {
            login: {
                method: 'POST',
                url: baseApi + 'login'
            },
            logout: {
                method: 'POST',
                url: baseApi + 'logout'
            }
        };
        return $resource(baseApi, params, actions);
    }]);