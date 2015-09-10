angular.module('perlerbeadsApp')
  .controller('HandleSavedRecDialogCtrl', ['$scope', '$modalInstance', 'beadDataService', 'beadViewService', 'name', 'savedRecs',
    function ($scope, $modalInstance, beadDataService,  beadViewService, name, savedRecs) {

      $scope.name = name;
      $scope.edit = function () {
        var currentData = beadDataService.getDataByName(name, savedRecs);
        beadDataService.currentSave(name, currentData.paletteCd, beadViewService.convert(currentData.paletteCd, currentData.data));
        $modalInstance.close();
      };

      $scope.deleteData = function () {
        beadDataService.deleteData(name);
        $modalInstance.close();
      };

      $scope.cancel = function () {
        $modalInstance.dismiss();
      };
    }]);
