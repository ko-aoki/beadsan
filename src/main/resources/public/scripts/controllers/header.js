angular.module('perlerbeadsApp')
    .controller('HeaderCtrl', ['$scope', '$location', '$modal', 'userResource', 'sharedStateService',
        function ($scope, $location, $modal, userResource, sharedStateService) {

            $scope.init = function () {

                userResource.isAuth().$promise.then(
                    function (data) {
                        $scope.sharedState = data.headerDto;
                        sharedStateService.put('sharedState', data.headerDto);
                    }
                )
            }

            $scope.init();

            $scope.isActive = function (viewLocation) {
                return viewLocation === $location.path();
            };

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
                    templateUrl: 'userRegisterDialog.tmpl.html',
                    controller: 'UserRegisterDialogCtrl'
                });
                modalInstance.result.then(function (data){
                    $scope.sharedState = data;
                    sharedStateService.put('sharedState', data);
                });
            };

            $scope.userInfoChangeDialog = function () {
                var modalInstance = $modal.open({
                    templateUrl: 'userInfoChangeDialog.tmpl.html',
                    controller: 'UserInfoChangeDialogCtrl'
                });
                modalInstance.result.then(function (data){
                    $scope.sharedState = data;
                    sharedStateService.put('sharedState', data);
                });
            };

        }]);
