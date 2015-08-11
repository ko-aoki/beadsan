/**
 * @ngdoc function
 * @name perlerbeadsApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the perlerbeadsApp
 */
angular.module('perlerbeadsApp')
  .controller('MainCtrl',
  ['$scope', '$modal', '$window', '$q', 'beadService', 'beadDataService', 'beadViewService',
    function ($scope, $modal, $window, $q, beadService, beadDataService, beadViewService) {

      var savedRecs;
      function load() {
        var itemSize = Math.floor($window.document.body.offsetWidth / 150);
        itemSize = itemSize > 4 ? 4 : itemSize;
//        beadDataService.load().$promise.then(
        beadDataService.load(
            function(data) {
              savedRecs = data;
              if (savedRecs !== undefined) {
                for (var i = 0; i < savedRecs.length; i++) {
                  savedRecs[i].data = beadViewService.convert(savedRecs[i].paletteType, savedRecs[i].data, true, i, itemSize);
                }
                $scope.page = {
                  itemSize: itemSize,
                  totalItems: savedRecs.length,
                  currentPage: 1
                };
                $scope.savedRecs = savedRecs.slice(0, $scope.page.itemSize);
              }
            }
        );
      }
      load();

      $scope.pageChanged = function () {
        if (savedRecs === null) {
          return;
        }

        $scope.savedRecs = savedRecs.slice(($scope.page.currentPage - 1) * $scope.page.itemSize,
          $scope.page.currentPage * $scope.page.itemSize);
      };

      function displayCurrent(){
        var currentData = beadDataService.currentGet();
        if (currentData == null) {
          $scope.paletteType = "SQUARE";
          $scope.beadsList = beadViewService.makePalette("SQUARE");
        } else {
          $scope.name = currentData.name;
          $scope.beadsList = beadViewService.convert(currentData.paletteType, currentData.data);
          $scope.paletteType = currentData.paletteType;
          $scope.colors = beadViewService.countColors($scope.beadsList);
        }
      };
      displayCurrent();

      $scope.color = 'deselect';

      $scope.select = function (color) {
        $scope.color = color;
        $scope.colorName = beadService.getColorName(color);
      };

      $scope.changeColor = function (bead) {
        if (bead.color == $scope.color) {
          bead.color = 'deselect';
        } else {
          bead.color = $scope.color;
        }
        beadDataService.currentSave($scope.name, $scope.paletteType, $scope.beadsList);
        $scope.colors = beadViewService.countColors($scope.beadsList);
      };

      $scope.handleSavedRec = function (name) {
        var modalInstance = $modal.open({
          size: 'sm',
          templateUrl: 'handleSavedRecDialog.tmpl.html',
          controller: 'HandleSavedRecDialogCtrl',
          resolve: {
            name: function () {
              return name;
            },
            currentData: function() {
              return $.curretnData;
            }
          }
        });

        modalInstance.result.then(function (){
          $scope.name = name;
          load();
          displayCurrent();
        });
      };

      $scope.save = function () {
        var deferred = $q.defer();
        var modalInstance = $modal.open({
          size: 'sm',
          templateUrl: 'saveDialog.tmpl.html',
          controller: 'SaveDialogCtrl',
          resolve: {
            name: function () {
              return $scope.name;
            }
          }
        });

        modalInstance.result.then(function (fileName) {
          beadDataService.save(fileName, $scope.paletteType, $scope.beadsList);
          load();
          $scope.name = fileName;
          deferred.resolve();
        },function() {
          deferred.resolve();
        });
        return deferred.promise;
      };

      $scope.makeNewData = function () {
        var promise;
        if ($window.confirm( 'いまのずあんをほぞんしますか？')) {
          promise = $scope.save();
        }

        var makeNewDataFunc = function() {
          var modalInstance = $modal.open({
            size: 'sm',
            templateUrl: 'newPaletteDialog.tmpl.html',
            controller: 'NewPaletteDialogCtrl'
          });

          modalInstance.result.then(function (paletteType) {
            $scope.name = '';
            $scope.paletteType = paletteType;
            $scope.beadsList = beadViewService.makePalette(paletteType);
            $scope.colors = beadViewService.countColors($scope.beadsList);
          });
        };
        if (promise !== undefined) {
          promise.then(makeNewDataFunc);
        } else {
          makeNewDataFunc();
        }
      };

    }]);
