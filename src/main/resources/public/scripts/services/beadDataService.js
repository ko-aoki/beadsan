angular.module('perlerbeadsApp').service('beadDataService', ['$window','beadService', 'beadResource',
    function ($window, beadService, beadResource) {
        this.currentSave = function (name, paletteCd, currentData, tags) {
            $window.localStorage.setItem('CURRENT_DATA',
                angular.toJson(
                {
                    name: name,
                    paletteCd: paletteCd,
                    data: currentData,
                    tags: tags
                }
            ));
        };

        this.currentGet = function () {
            return angular.fromJson($window.localStorage.getItem('CURRENT_DATA'));
        };

        this.isOverwritable = function (name) {

            return beadResource.hasDuplicated(
                {'designName': name}
            ).$promise;
        };

        this.save = function (name, paletteCd, data, tags) {

            var persistingData = {
                'name': name,
                'paletteCd': paletteCd,
                'design': this.removePosition(data, beadService.getPalette(paletteCd)),
                'tags': tags
            };
            return beadResource.save(persistingData).$promise;
        };

        this.load = function (page) {
            return beadResource.get(page).$promise;
        };

        this.loadPopularDesign = function (page) {
            return beadResource.getPopularDesign(page).$promise;
        };

        this.find = function (cond) {
            return beadResource.find(cond).$promise;
        };

        this.findFavorite = function (cond) {
            return beadResource.findFavorite(cond).$promise;
        };

        this.deleteData = function (name) {
            return beadResource.deleteByName({"designName": name}).$promise;
        };

        this.getDataByName = function (name, savedRecs) {
            var records = [];
            for (var i = 0; i < savedRecs.length; i++) {
                var rec = savedRecs[i];
                if (rec.name === name) {
                    return rec;
                }
            }
            return {};
        };

        this.removePosition = function (currentData) {

            var data = new Array(currentData.length);
            for (var topIdx = 0; topIdx < currentData.length; topIdx++) {
                var nestData = new Array(currentData[topIdx].length)
                for (var leftIdx = 0; leftIdx < currentData[topIdx].length; leftIdx++) {
                    nestData[leftIdx] = currentData[topIdx][leftIdx].color;
                }
                data[topIdx] = nestData;
            }

            return data;
        };

    }]);
