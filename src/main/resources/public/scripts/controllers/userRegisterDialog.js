angular.module('perlerbeadsApp')
    .controller('UserRegisterDialogCtrl', ['$scope', 'userResource', '$modalInstance', 'sharedStateService',
        function ($scope, userResource, $modalInstance, sharedStateService) {

            $scope.sharedState = sharedStateService.get("sharedState");

            if ($scope.sharedState !== undefined) {
                $scope.userRegisterDto = {
                                            mailAddress: $scope.sharedState.mailAddress,
                                            password: "",
                                            nickname: $scope.sharedState.nickname
                                        };
            }

            $scope.register = function () {
                if ($scope.userRegisterDto.password !==
                        $scope.userRegisterDto.passwordConfirm) {
                    $scope.userRegisterDto.errorMessage = "パスワードが一致しません";
                    return;
                }
                userResource.save(
                    {
                        mailAddress: $scope.userRegisterDto.mailAddress,
                        password: $scope.userRegisterDto.password,
                        nickname: $scope.userRegisterDto.nickname
                    },
                    function (data) {
                       $scope.userRegisterDto = data;
                        $scope.userRegisterDto.passwordConfirm
                            = data.password;
                    },
                    function (res) {
                        $scope.userRegisterDto = res.data;
                        $scope.userRegisterDto.passwordConfirm
                            = res.data.password;
                    }
                );
            }

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            }
        }]);
