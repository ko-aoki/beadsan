angular.module('perlerbeadsApp')
    .controller('HeaderCtrl', ['$scope', '$modal', 'loginHttp',
        function ($scope, $modal, loginHttp) {
            $scope.logined = false;
            $scope.loginDialog = function () {
                var modalInstance = $modal.open({
                    size: 'sm',
                    templateUrl: 'login.tmpl.html',
                    controller: 'LoginCtrl'
                    //resolve: {
                    //    headerDto: function () {
                    //        return $scope.headerDto;
                    //    }
                    //}
                });
                modalInstance.result.then(function (data){
                    $scope.headerDto = data;
                });
            };

            $scope.logout = function () {
                loginHttp.logout();
                $scope.headerDto = {};
            }

        }]);
