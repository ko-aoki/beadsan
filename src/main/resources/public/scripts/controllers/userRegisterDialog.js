angular.module('perlerbeadsApp')
    .controller('UserRegisterDialogCtrl', ['$scope', 'userResource', '$modalInstance',
        function ($scope, userResource, $modalInstance) {
            $scope.register = function () {
                userResource.save(
                    {
                        mailAddress: $scope.userRegisterDto.mailAddress,
                        password: $scope.userRegisterDto.password,
                        nickname: $scope.userRegisterDto.nickname
                    },
                    function (data) {
                       $scope.userRegisterDto = data;
                    },
                    function (res) {
                        $scope.userRegisterDto = res.data;
                    }
                );
            }

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            }
        }]);
