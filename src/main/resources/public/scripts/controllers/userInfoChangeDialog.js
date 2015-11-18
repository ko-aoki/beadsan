angular.module('perlerbeadsApp')
    .controller('UserInfoChangeDialogCtrl', ['$scope', 'userResource', '$modalInstance', 'sharedStateService',
        function ($scope, userResource, $modalInstance, sharedStateService) {

            $scope.sharedState = sharedStateService.get("sharedState");

            if ($scope.sharedState !== undefined) {
                $scope.userRegisterDto = {
                                            mailAddress: $scope.sharedState.mailAddress,
                                            password: "",
                                            nickname: $scope.sharedState.nickname
                                        };
            }
            $scope.changePassword = function () {
                if ($scope.userRegisterDto.password !==
                    $scope.userRegisterDto.passwordConfirm) {
                    $scope.userRegisterDto.errorMessage = "パスワードが一致しません";
                    return;
                }
                userResource.changePassword(
                    {
                        mailAddress: $scope.userRegisterDto.mailAddress,
                        oldPassword: $scope.userRegisterDto.oldPassword,
                        password: $scope.userRegisterDto.password
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

            $scope.changeNickname = function () {
                userResource.changeNickname(
                    {
                        mailAddress: $scope.userRegisterDto.mailAddress,
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
