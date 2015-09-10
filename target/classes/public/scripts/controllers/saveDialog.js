angular.module('perlerbeadsApp')
    .controller('SaveDialogCtrl', ['$scope', '$modalInstance', 'name',
        function ($scope, $modalInstance, name) {
            if (name !== undefined) {
                $scope.fileName = name;
            }

            $scope.save = function () {
                $modalInstance.close($scope.fileName);
            };

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        }]);
