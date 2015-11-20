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
            },
            isAuth: {
                method: 'GET',
                url: baseApi + 'auth'
            },
            changePassword: {
                method: 'PUT',
                url: baseApi + 'password'
            },
            changeNickname: {
                method: 'PUT',
                url: baseApi + 'nickname'
            }
        };
        return $resource(baseApi, params, actions);
    }]);