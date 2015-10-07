angular.module('perlerbeadsApp')
  .controller('HandleFoundRecDialogCtrl', ['$window', '$scope', '$modalInstance', 'favoriteDesignService', 'foundRec',
    function ($window, $scope, $modalInstance, favoriteDesignService, foundRec) {

      $scope.foundRec = foundRec;
      $scope.addFavorite = function () {
        favoriteDesignService.addFavorite(foundRec.designId);
        $modalInstance.close();
      };

      $scope.removeFavorite = function () {
        if ($window.confirm('「' + foundRec.name + '」' + 'お気に入りから削除しますか？')) {
          favoriteDesignService.removeFavorite(foundRec.designId);
        }
        $modalInstance.close();
      };

      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
      };
    }]);
