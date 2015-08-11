angular.module('perlerbeadsApp').service('beadDataService', ['$window','beadService', 'beadResource',
    function ($window, beadService, beadResource) {
        this.currentSave = function (name, paletteType, currentData) {
            $window.localStorage.setItem('CURRENT_DATA',
                angular.toJson(
                {
                    name: name,
                    paletteType: paletteType,
                    data: currentData
                }
            ));
        };

        this.currentGet = function () {
            return angular.fromJson($window.localStorage.getItem('CURRENT_DATA'));
        };

        this.save = function (name, paletteType, data) {
            var persistingData = {
                name: name,
                paletteType: paletteType,
                data: this.removePosition(data, beadService.getPalette(paletteType))
            };

            var hasDuplicated = beadResource.hasDuplicated(name);
            if (hasDuplicated) {
                if (!$window.confirm('「' + name + '」' + ' おなじなまえがあります。うわがきしますか？')) {
                    return;
                }
            }
            beadResource.post(persistingData);
        };

        this.load = function () {
            return beadResource.get();
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

    }]);
