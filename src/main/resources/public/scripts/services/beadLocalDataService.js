angular.module('perlerbeadsApp').service('beadDataService', ['$resource', '$window','beadService',
    function ($resource, $window, beadService) {
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

        this.save = function (name, paletteCd, data) {
            var records = [];
            if ($window.localStorage.getItem('PERSISTED_DATA') != null) {
                records = angular.fromJson($window.localStorage.getItem('PERSISTED_DATA'));
            }
            var persistingData = {
                name: name,
                paletteCd: paletteCd,
                data: this.removePosition(data, beadService.getPalette(paletteCd)),
                updateDate: new Date()
            };

            for (var i = 0; i < records.length; i++) {
                var rec = records[i];
                if (rec.name === name) {
                    if ($window.confirm('「' + name + '」' + ' おなじなまえがあります。うわがきしますか？')) {
                        records[i] = persistingData;
                    }
                    break;
                }
                if (i === records.length - 1) {
                    records.push(persistingData);
                    break;
                }
            }
            if (records.length < 1) {
                records.push(persistingData);
            }
            $window.localStorage.setItem('PERSISTED_DATA', angular.toJson(records));
        };

        this.load = function () {
            return angular.fromJson($window.localStorage.getItem('PERSISTED_DATA'));
        };

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

        this.getDataByName = function (name) {
            var records = [];
            if ($window.localStorage.getItem('PERSISTED_DATA') != null) {
                records = angular.fromJson($window.localStorage.getItem('PERSISTED_DATA'));
            }
            for (var i = 0; i < records.length; i++) {
                var rec = records[i];
                if (rec.name === name) {
                    return rec;
                }
            }
            return {};
        };

        this.removePosition = function (currentData) {
            var data = angular.copy(currentData);
            for (var topIdx = 0; topIdx < data.length; topIdx++) {
                for (var leftIdx = 0; leftIdx < data[topIdx].length; leftIdx++) {
                    delete data[topIdx][leftIdx].top;
                    delete data[topIdx][leftIdx].left;
                }
            }
            return data;
        };

    }]);
