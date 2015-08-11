angular.module('perlerbeadsApp')
  .controller('ContextMenuCtrl', ['$scope', '$modalInstance',
    function ($scope, $modalInstance) {
      $scope.select = function (color) {
        $modalInstance.close(color);
      };

      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
      };
    }]);
