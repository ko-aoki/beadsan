angular.module('perlerbeadsApp')
    .controller('LoginCtrl', ['$scope', 'userResource', '$modalInstance',
        function ($scope, userResource, $modalInstance) {
            $scope.login = function () {
                userResource.login({
                    mailAddress: $scope.loginDto.mailAddress,
                    password: $scope.loginDto.password
                }).$promise.then(
                    function (data) {
                        $scope.loginDto = data.loginDto;
                        $modalInstance.close(data.headerDto);
                    },
                    function (res) {
                        $scope.loginDto = res.data.loginDto;
                    }
                )
            }

            $scope.cancel = function () {
                $modalInstance.close();
            }
        }]);
