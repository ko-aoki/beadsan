angular.module('perlerbeadsApp').factory('loginResource', ['$resource',
    function ($resource) {
        var baseApi = 'api/';
        var params = null;
        var actions = {
            login: {
                method: 'POST',
                //params: {
                //    'userId': '@userId',
                //    'password': '@password'
                //},
                url: baseApi + 'login'
            }
        };
        return $resource(baseApi, params, actions);
    }]);
