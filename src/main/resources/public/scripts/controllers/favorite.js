/**
 * @ngdoc function
 * @name perlerbeadsApp.controller:FavoriteCtrl
 * @description
 * # FavoriteCtrl
 * Controller of the perlerbeadsApp
 */
angular.module('perlerbeadsApp')
  .controller('FavoriteCtrl',
  ['$scope', '$modal', '$window', '$q', 'beadService', 'favoriteDesignService', 'beadViewService', 'sharedStateService',
    function ($scope, $modal, $window, $q, beadService, favoriteDesignService, beadViewService, sharedStateService) {

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
                || sharedStateService.get("sharedState").auth === undefined) {
          $scope.foundRecs = null;
        }
        $scope.sharedState = sharedStateService.get("sharedState");
        $scope.load();
      }

      $scope.load = function(name, tag){

        favoriteDesignService.load(
          {
            curPage: 1,
            itemsPerPage: itemsPerPage
          }
        ).then(
            function(data) {
              if (data.content !== undefined) {
                var foundRecs = new Array(data.content.length);
                for (var i = 0; i < data.content.length; i++) {
                  foundRecs[i] = {
                    designId: data.content[i].designId,
                    name: data.content[i].name,
                    paletteCd: data.content[i].paletteCd,
                    data: beadViewService.convert(data.content[i].paletteCd,
                        data.content[i].design, true),
                    tags: data.content[i].tags,
                    favoriteCnt: data.content[i].favoriteCnt,
                    favoriteOne: data.content[i].favoriteOne,
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
        return top + Math.floor(idx / itemsPerLine) * 180;
      };

      $scope.calcLeft = function(left, idx) {
        return left + (idx % itemsPerLine) * 150;
      };

      $scope.pageChanged = function () {
        favoriteDesignService.load(
            {
              curPage: $scope.page.currentPage,
              itemsPerPage: itemsPerPage
            }
        ).then(
            function(data) {
              var foundRecs = data.content;
              if (foundRecs !== undefined) {
                for (var i = 0; i < foundRecs.length; i++) {
                  foundRecs[i].data = beadViewService.convert(foundRecs[i].paletteCd, foundRecs[i].design, true);
                }
                $scope.foundRecs = foundRecs;
              }
            }
        );

      };

      $scope.handleFoundRec = function (rec) {
        var modalInstance = $modal.open({
          size: 'lg',
          templateUrl: 'handleFoundRecDialog.tmpl.html',
          controller: 'HandleFoundRecDialogCtrl',
          resolve: {
            foundRec: function() {
              return {
                designId: rec.designId,
                name: rec.name,
                paletteCd: rec.paletteCd,
                data: beadViewService.convert(rec.paletteCd, rec.data, false),
                tags: rec.tags,
                favoriteCnt: rec.favoriteCnt,
                favoriteOne: rec.favoriteOne
              };
            }
          }
        });

        modalInstance.result.then(function (name){
          $scope.name = name;
        });
      };


    }]);
