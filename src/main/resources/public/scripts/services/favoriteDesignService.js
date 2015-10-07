angular.module('perlerbeadsApp').service('favoriteDesignService', ['$window','favoriteDesignResource',
    function ($window, favoriteDesignResource) {

        this.load = function (page) {
            return favoriteDesignResource.get(page).$promise;
        };

        this.addFavorite = function (designId) {
            return favoriteDesignResource.save({"designId": designId}).$promise;
        };

        this.removeFavorite = function (designId) {
            return favoriteDesignResource.delete({"designId": designId}).$promise;
        };

    }]);
