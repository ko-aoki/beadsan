/**
 * @ngdoc function
 * @name perlerbeadsApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the perlerbeadsApp
 */
angular.module('perlerbeadsApp')
  .controller('MainCtrl',
  ['$scope', '$modal', '$window', '$q', '$timeout', 'beadService', 'beadDataService', 'beadViewService', 'sharedStateService',
    function ($scope, $modal, $window, $q, $timeout, beadService, beadDataService, beadViewService, sharedStateService) {

      /** 1ページに表示するデータサイズ */
      var itemsPerPage = Math.floor($window.document.body.offsetWidth / 150);
      itemsPerPage = itemsPerPage > 4 ? 4 : itemsPerPage;

      /** 他コントローラ共有データ */
      $scope.$watch(
          function() {
            return sharedStateService.get("sharedState");
          }
          , function() {
            init();
          }, true);

      function init() {
        if (sharedStateService.get("sharedState") !== undefined
                && sharedStateService.get("sharedState").auth) {
          load();
        } {
          $scope.savedRecs = null;
        }
        $scope.sharedState = sharedStateService.get("sharedState");
      }

      function load() {
        beadDataService.load(
          {
            curPage: 1,
            itemsPerPage: itemsPerPage
          }
        ).then(
            function(data) {
              if (data.content !== undefined) {
                var savedRecs = new Array(data.content.length);
                for (var i = 0; i < data.content.length; i++) {
                  savedRecs[i] = {
                    name: data.content[i].name,
                    paletteCd: data.content[i].paletteCd,
                    data: beadViewService.convert(data.content[i].paletteCd,
                        data.content[i].design, true),
                    tags: data.content[i].tags
                  };
                }
                $scope.page = {
                  itemsPerPage: itemsPerPage,
                  totalItems: data.totalElements,
                  currentPage: 1
                };
                $scope.savedRecs = savedRecs;
              }
            }
        );
      }

      $scope.pageChanged = function () {
        beadDataService.load(
            {
              curPage: $scope.page.currentPage,
              itemsPerPage: itemsPerPage
            }
        ).then(
            function(data) {
              var savedRecs = data.content;
              if (savedRecs !== undefined) {
                for (var i = 0; i < savedRecs.length; i++) {
                  savedRecs[i].data = beadViewService.convert(savedRecs[i].paletteCd, savedRecs[i].design, true);
                }
                $scope.savedRecs = savedRecs;
              }
            }
        );

        //$scope.savedRecs = savedRecs.slice(($scope.page.currentPage - 1) * $scope.page.itemsPerPage,
        //  $scope.page.currentPage * $scope.page.itemsPerPage);
      };

      function displayCurrent(){
        var currentData = beadDataService.currentGet();
        if (currentData == null) {
          $scope.paletteCd = "SQUARE";
          $scope.beadsList = beadViewService.makePalette("SQUARE");
        } else {
          $scope.name = currentData.name;
          //$scope.beadsList = beadViewService.convert(currentData.paletteCd, currentData.data);
          $scope.beadsList = currentData.data;
          $scope.paletteCd = currentData.paletteCd;
          $scope.colors = beadViewService.countColors($scope.beadsList);
          $scope.tags = currentData.tags;
        }
      };
      displayCurrent();

      $scope.color = 'deselect';

      $scope.select = function (color) {
        $scope.color = color;
        $scope.colorName = beadService.getColorName(color);
      };

      $scope.changeColor = function (bead) {
        if (bead.color === $scope.color) {
          bead.color = 'deselect';
        } else {
          bead.color = $scope.color;
        }
        beadDataService.currentSave($scope.name, $scope.paletteCd, $scope.beadsList, $scope.tags);
        $scope.colors = beadViewService.countColors($scope.beadsList);
      };

      $scope.handleSavedRec = function (rec) {
        var modalInstance = $modal.open({
          size: 'sm',
          templateUrl: 'handleSavedRecDialog.tmpl.html',
          controller: 'HandleSavedRecDialogCtrl',
          resolve: {
            savedRec: function() {
              return rec
            }
          }
        });

        modalInstance.result.then(function (name){
          $scope.name = name;
          $scope.pageChanged();
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
            },
            tags: function () {
              return $scope.tags;
            }
          }
        });

        modalInstance.result.then(
            function (designInfo) {
              beadDataService.isOverwritable(designInfo.designName).then(
                  function (res) {
                    var isOverwritable = true;
                    if (res.result) {
                      if (!$window.confirm('「' + designInfo.designName + '」' + ' 同じ名前があります。上書きしますか？')) {
                        isOverwritable = false;
                      }
                    }
                    if (isOverwritable) {
                      beadDataService.save(designInfo.designName, $scope.paletteCd, $scope.beadsList, designInfo.tags).then(
                          function () {
                            $scope.name = designInfo.designName;
                            deferred.resolve();
                            load();
                          }
                      );
                      return;
                    }
                    deferred.reject();
                  }
              );
            }
            ,function () {
              deferred.reject();
            });
        return deferred.promise;
      };

      $scope.makeNewData = function () {
        var promise;
        if (
            $scope.sharedState !== undefined
            && $scope.sharedState.auth
            && $window.confirm( '編集中の図案を保存しますか？')) {
          promise = $scope.save();
        }

        var makeNewDataFunc = function() {
          var modalInstance = $modal.open({
            size: 'sm',
            templateUrl: 'newPaletteDialog.tmpl.html',
            controller: 'NewPaletteDialogCtrl'
          });

          modalInstance.result.then(function (paletteCd) {
            $scope.name = '';
            $scope.paletteCd = paletteCd;
            $scope.beadsList = beadViewService.makePalette(paletteCd);
            $scope.colors = beadViewService.countColors($scope.beadsList);
            $scope.tags = [];
          });
        };
        if (promise !== undefined) {
          promise.then(
              makeNewDataFunc,
              makeNewDataFunc
          );
        } else {
          makeNewDataFunc();
        }
      };

      $scope.$watch(
          function() {
            return $scope.capturedFile;
          }
          , function() {
            $scope.capture();
          }, true);

      $scope.capture = function () {

        if ($scope.capturedFile === undefined) {
          return;
        }
        var promise;
        if (
            $scope.sharedState !== undefined
            && $scope.sharedState.auth
            && $window.confirm( '編集中の図案を保存しますか？')) {
          promise = $scope.save();
        }

        var toBeadData = function() {

          // キャプチャ画像を変換
          var imgElm = document.createElement('img');
          imgElm.src = $scope.capturedFile;
          imgElm.onload = function() {
            $timeout(function () {
              var cvs = $window.document.getElementById("converter");
              var ctx = cvs.getContext("2d");
              var img = new Image();
              ctx.clearRect(0, 0, 20, 20);
              // 縮小画像作成
              ctx.drawImage(imgElm, 0, 0, 20, 20);
              $scope.name = "";
              $scope.paletteCd = "SQUARE";
              $scope.beadsList = beadViewService.applyColorPattern(ctx.getImageData(0, 0, 20, 20).data);
              $scope.colors = beadViewService.countColors($scope.beadsList);
              $scope.tags = [];
              beadDataService.currentSave($scope.name, $scope.paletteCd, $scope.beadsList, $scope.tags);
            });

          }
        };
        if (promise !== undefined) {
          promise.then(
              toBeadData,
              toBeadData
          );
        } else {
          toBeadData();
        }

      }

}]);
