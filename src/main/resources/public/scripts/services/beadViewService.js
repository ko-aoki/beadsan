angular.module('perlerbeadsApp').service('beadViewService',['beadService',
  function (beadService) {

    this.makePalette = function (paletteCd) {
      return beadService.getPalette(paletteCd).makePalette();
    };
    this.convert = function (paletteCd, data, isThumbnail) {
      return beadService.getPalette(paletteCd).convert(data, isThumbnail);
    };

    this.countColors = function(beadsList) {
      var colors = {};
      var i,j;
      for(i = 0; i < beadsList.length; i++) {
        for (j = 0; j < beadsList[i].length; j++) {
          var color = beadsList[i][j].color;
          if ( color == 'deselect') {
            continue;
          }
          if (colors[color] == null) {
            colors[color] = {cnt:1, colorName: beadService.getColorName(color)};
          } else {
            colors[color].cnt++;
          }
        }
      }
      return colors;
    }

  }]);
