angular.module('perlerbeadsApp')
    .controller('LoginCtrl', ['$scope', 'loginResource', '$modalInstance',
        function ($scope, loginResource, $modalInstance) {
            $scope.login = function() {
                loginResource.login(
                    {'mailAddress': $scope.mailAddress, 'password': $scope.password},
                    function (data) {
                        $scope.logined = data.logined;
                        $scope.message = data.message;
                        if (data.logined) {
                            $modalInstance.close(data);
                        }
                    }
                );
            };
            $scope.cancel = function() {
                $modalInstance.close();
            }
        }]);
