angular.module('perlerbeadsApp')
    .controller('LoginCtrl', ['$scope', 'loginHttp', '$modalInstance',
        function ($scope, loginHttp, $modalInstance) {
            $scope.login = function () {
                loginHttp.login({
                    mailAddress: $scope.loginDto.mailAddress,
                    password: $scope.loginDto.password
                })
                .success(
                    function (data) {
                        $scope.loginDto = data.loginDto;
                        $modalInstance.close(data.headerDto);
                    }
                )
                .error(
                    function (data) {
                        $scope.loginDto = data.loginDto;
                    }
                )
            }

            $scope.cancel = function () {
                $modalInstance.close();
            }
        }]);
