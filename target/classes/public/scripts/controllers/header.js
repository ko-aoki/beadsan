angular.module('perlerbeadsApp')
    .controller('HeaderCtrl', ['$scope', '$modal', 'userResource', 'sharedStateService',
        function ($scope, $modal, userResource, sharedStateService) {
            $scope.loginDialog = function () {
                var modalInstance = $modal.open({
                    size: 'sm',
                    templateUrl: 'login.tmpl.html',
                    controller: 'LoginCtrl'
                });
                modalInstance.result.then(function (data){
                    $scope.sharedState = data;
                    sharedStateService.put('sharedState', data);
                });
            };

            $scope.logout = function () {
                userResource.logout();
                $scope.sharedState = {};
                sharedStateService.put('sharedState', {});
            };

            $scope.userRegisterDialog = function () {
                var modalInstance = $modal.open({
//                    size: 'sm',
                    templateUrl: 'userRegisterDialog.tmpl.html',
                    controller: 'UserRegisterDialogCtrl'
                });
                modalInstance.result.then(function (data){
                    $scope.sharedState = data;
                    sharedStateService.put('sharedState', data);
                });
            };

        }]);
