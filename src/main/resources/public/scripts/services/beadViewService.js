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
    };

    this.applyColorPattern = function(imgData){

      var beadData = this.makePalette("SQUARE");
      for (var topIdx=0; topIdx < beadData.length; topIdx++){
        for (var leftIdx=0; leftIdx < beadData[topIdx].length; leftIdx++){
          // 色情報取得
          var offset = (leftIdx + topIdx * 20) * 4;
          var red = imgData[offset];
          var green = imgData[offset + 1];
          var blue = imgData[offset + 2];
// alphaは未使用

          var distance = 255 * 255 * 255;
          for (var i=0; i < BEAD_COLORS.length; i++) {
            var beadColor = BEAD_COLORS[i];
            var redDiff =  red - beadColor.red;
            var greenDiff =  green - beadColor.green;
            var blueDiff =  blue - beadColor.blue;
            var beadColorDistance = Math.pow(redDiff, 2) + Math.pow(greenDiff, 2) + Math.pow(blueDiff, 2);
            if (distance > beadColorDistance) {
              distance = beadColorDistance;
              beadData[topIdx][leftIdx].color = beadColor.name;
            }
          }
        }
      }
      return beadData;
    };

    var BEAD_COLORS = [
      {
        "name": "shiro",
        "red": 255,
        "green": 255,
        "blue": 255
      },
      {
        "name": "kuro",
        "red": 0,
        "green": 0,
        "blue": 0
      },
      {
        "name": "aka",
        "red": 255,
        "green": 0,
        "blue": 0
      },
      {
        "name": "ao",
        "red": 0,
        "green": 0,
        "blue": 255
      },
      {
        "name": "kiiro",
        "red": 255,
        "green": 255,
        "blue": 0
      },
      {
        "name": "midori",
        "red": 0,
        "green": 128,
        "blue": 0
      },
      {
        "name": "kimidori",
        "red": 0,
        "green": 255,
        "blue": 0
      },
      {
        "name": "apricot",
        "red": 234,
        "green": 211,
        "blue": 165
      },
      {
        "name": "cha",
        "red": 189,
        "green": 74,
        "blue": 59
      },
      {
        "name": "kogecha",
        "red": 106,
        "green": 66,
        "blue": 69
      },
      {
        "name": "odo",
        "red": 190,
        "green": 118,
        "blue": 66
      },
      {
        "name": "komugi",
        "red": 213,
        "green": 170,
        "blue": 104
      },
      {
        "name": "creme",
        "red": 231,
        "green": 229,
        "blue": 221
      },
      {
        "name": "raspberry",
        "red": 190,
        "green": 0,
        "blue": 34
      },
      {
        "name": "budo",
        "red": 147,
        "green": 0,
        "blue": 118
      },
      {
        "name": "aomurasaki",
        "red": 129,
        "green": 159,
        "blue": 215
      },
      {
        "name": "uguisu",
        "red": 131,
        "green": 191,
        "blue": 197
      },
      {
        "name": "haiiro",
        "red": 128,
        "green": 128,
        "blue": 128
      },
      {
        "name": "murasaki",
        "red": 128,
        "green": 0,
        "blue": 128
      },
      {
        "name": "pink",
        "red": 255,
        "green": 192,
        "blue": 203
      }

    ];
  }]);
