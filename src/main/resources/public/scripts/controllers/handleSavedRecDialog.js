angular.module('perlerbeadsApp')
  .controller('HandleSavedRecDialogCtrl', ['$window', '$scope', '$modalInstance', 'beadDataService', 'beadViewService', 'savedRec',
    function ($window, $scope, $modalInstance, beadDataService,  beadViewService, savedRec) {

      $scope.savedRec = savedRec;
      $scope.edit = function () {
        var currentData = savedRec;
        beadDataService.currentSave(
            savedRec.name,
            currentData.paletteCd,
            beadViewService.convert(currentData.paletteCd, currentData.data),
            savedRec.tags
        );
        $modalInstance.close();
      };

      $scope.deleteData = function () {
        if ($window.confirm('「' + savedRec.name + '」' + '削除しますか？')) {
          beadDataService.deleteData(savedRec.name);
        }
        $modalInstance.close();
      };

      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
      };
    }]);
