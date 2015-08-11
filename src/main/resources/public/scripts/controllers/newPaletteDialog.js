angular.module('perlerbeadsApp')
    .controller('NewPaletteDialogCtrl', ['$scope', '$modalInstance',
        function ($scope, $modalInstance) {

            $scope.paletteType = "SQUARE";

            $scope.ok = function () {
                $modalInstance.close($scope.paletteType);
            };

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        }]);
