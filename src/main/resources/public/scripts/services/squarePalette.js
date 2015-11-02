angular.module('palette').service('squarePalette',
  function () {
    this.sideLength = 20;
    this.beadsLength = 20;
    this.margin = 2;
    this.thunmbnailBeadsLength = 5;
    this.thunmbnailMargin = 0;
    this.makePalette = function (isThumbnail) {
      var topIdx, leftIdx, beadsLength, margin;
      var beads = new Array(this.sideLength);
      if (isThumbnail) {
        beadsLength = this.thunmbnailBeadsLength;
        margin = this.thunmbnailMargin;
      } else {
        beadsLength = this.beadsLength;
        margin = this.margin;
      }
      for (topIdx = 0; topIdx < this.sideLength; topIdx++) {
        beads[topIdx] = new Array(this.sideLength);
        for (leftIdx = 0; leftIdx < this.sideLength; leftIdx++) {
          beads[topIdx][leftIdx] = {};
          beads[topIdx][leftIdx].top = topIdx * (beadsLength + margin);
          beads[topIdx][leftIdx].left = leftIdx * (beadsLength + margin);
          beads[topIdx][leftIdx].color = 'deselect';
        }
      }
      return beads;
    };

    this.makeThumbnailPalette = function () {
      return this.makePalette(true);
    };

    this.convert = function(data, isThumbnail) {
      var topIdx , leftIdx;
      var convData = [];
      if (isThumbnail) {
        convData = this.makeThumbnailPalette();
      } else {
        convData = this.makePalette();
      }
      for(topIdx = 0; topIdx < convData.length; topIdx++) {
        for(leftIdx = 0; leftIdx < convData[topIdx].length; leftIdx++) {
          if (isThumbnail) {
            convData[topIdx][leftIdx].color = data[topIdx][leftIdx];
          } else {
            convData[topIdx][leftIdx].color = data[topIdx][leftIdx].color;
          }
        }
      }
      return convData;
    };
  });
