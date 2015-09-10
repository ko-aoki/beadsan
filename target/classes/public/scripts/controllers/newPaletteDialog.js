angular.module('perlerbeadsApp')
    .controller('NewPaletteDialogCtrl', ['$scope', '$modalInstance',
        function ($scope, $modalInstance) {

            $scope.paletteCd = "SQUARE";

            $scope.ok = function () {
                $modalInstance.close($scope.paletteCd);
            };

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        }]);
