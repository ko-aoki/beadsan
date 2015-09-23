/**
 * @ngdoc function
 * @name perlerbeadsApp.controller:FinderCtrl
 * @description
 * # FinderCtrl
 * Controller of the perlerbeadsApp
 */
angular.module('perlerbeadsApp')
  .controller('FinderCtrl',
  ['$scope', '$modal', '$window', '$q', 'beadService', 'beadDataService', 'beadViewService', 'sharedStateService',
    function ($scope, $modal, $window, $q, beadService, beadDataService, beadViewService, sharedStateService) {

      /** 横１列に表示するデータサイズ */
      var itemsPerLine = Math.floor($window.document.body.offsetWidth / 150);
      itemsPerLine = itemsPerLine > 4 ? 4 : itemsPerLine;
      $scope.itemsPerLines = itemsPerLine;

      /** 1ページに表示するデータサイズ */
      var itemsPerPage = itemsPerLine * 5;

      /** 他コントローラ共有データ */
      $scope.$watch(
          function() {
            return sharedStateService.get("sharedState");
          }
          , function() {
            init();
          }, true);

      function init() {
        if (sharedStateService.get("sharedState") === undefined
                == sharedStateService.get("sharedState").auth) {
          $scope.foundRecs = null;
        }
        $scope.sharedState = sharedStateService.get("sharedState");
      }

      $scope.find = function(name, tag){

        beadDataService.find(
          {
            designName: name,
            tag: tag,
            curPage: 1,
            itemsPerPage: itemsPerPage
          }
        ).then(
            function(data) {
              if (data.content !== undefined) {
                var foundRecs = new Array(data.content.length);
                for (var i = 0; i < data.content.length; i++) {
                  foundRecs[i] = {
                    name: data.content[i].name,
                    paletteCd: data.content[i].paletteCd,
                    data: beadViewService.convert(data.content[i].paletteCd,
                        data.content[i].design, true, i, itemsPerPage),
                    tags: data.content[i].tags
                  };
                }
                $scope.page = {
                  itemsPerPage: itemsPerPage,
                  totalItems: data.totalElements,
                  currentPage: 1
                };
                $scope.foundRecs = foundRecs;
              }
            }
        );
      };

      $scope.calcTop = function(top, idx) {
        return top + Math.floor(idx / itemsPerLine) * 200;
      };

      $scope.calcLeft = function(left, idx) {
        return left + (idx % itemsPerLine) * 150;
      };

      $scope.pageChanged = function () {
        beadDataService.find(
            {
              designName: name,
              tag: tag,
              curPage: $scope.page.currentPage,
              itemsPerPage: itemsPerPage
            }
        ).then(
            function(data) {
              var foundRecs = data.content;
              if (foundRecs !== undefined) {
                for (var i = 0; i < foundRecs.length; i++) {
                  foundRecs[i].data = beadViewService.convert(foundRecs[i].paletteCd, foundRecs[i].design, true, i, itemsPerPage);
                }
                $scope.foundRecs = foundRecs;
              }
            }
        );

      };

      $scope.handleFoundRec = function (rec) {
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
          load();
          displayCurrent();
        });
      };


    }]);
