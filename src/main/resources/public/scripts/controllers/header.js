angular.module('perlerbeadsApp')
    .controller('HeaderCtrl', ['$scope', '$modal',
        function ($scope, $modal) {
            $scope.logined = false;
            $scope.loginDialog = function () {
                var modalInstance = $modal.open({
                    size: 'sm',
                    templateUrl: 'login.tmpl.html',
                    controller: 'LoginCtrl',
                    resolve: {
                        userId: function () {
                            return $scope.userId;
                        }
                    }
                });
                modalInstance.result.then(function (data){
                    $scope.userInfo = data;
                });
            };


        }]);
