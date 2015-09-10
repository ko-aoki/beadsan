angular.module('perlerbeadsApp').service('beadDataService', ['$window','beadService', 'beadResource',
    function ($window, beadService, beadResource) {
        this.currentSave = function (name, paletteCd, currentData) {
            $window.localStorage.setItem('CURRENT_DATA',
                angular.toJson(
                {
                    name: name,
                    paletteCd: paletteCd,
                    data: currentData
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

        this.save = function (name, paletteCd, data) {

            var persistingData = {
                'name': name,
                'paletteCd': paletteCd,
                'design': this.removePosition(data, beadService.getPalette(paletteCd))
            };
            return beadResource.save(persistingData).$promise;
        };

        this.load = function (page) {
            return beadResource.get(page).$promise;
        };

        // TODO 未実装
        this.deleteData = function (name) {
            var records = [];
            if ($window.localStorage.getItem('PERSISTED_DATA') != null) {
                records = angular.fromJson($window.localStorage.getItem('PERSISTED_DATA'));
            }
            for (var i = 0; i < records.length; i++) {
                var rec = records[i];
                if (rec.name === name) {
                    if ($window.confirm('「' + name + '」' + 'さくじょしますか？')) {
                        records.splice(i, 1);
                    }
                }
            }
            $window.localStorage.setItem('PERSISTED_DATA', angular.toJson(records));
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

            //var data = angular.copy(currentData);
            //for (var topIdx = 0; topIdx < data.length; topIdx++) {
            //    for (var leftIdx = 0; leftIdx < data[topIdx].length; leftIdx++) {
            //        delete data[topIdx][leftIdx].top;
            //        delete data[topIdx][leftIdx].left;
            //    }
            //}
            return data;
        };

    }]);
