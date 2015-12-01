angular.module('perlerbeadsApp').service('beadService',['squarePalette', 'circlePalette',
  function (squarePalette, circlePalette) {

    this.getPalette = function(paletteCd) {
      switch (paletteCd) {
        case "SQUARE":
          return squarePalette;
        case "CIRCLE":
          return circlePalette;
      }
    };

    this.getColorName = function(color) {
      switch (color) {
        case "aka":
          return "あか";
        case "ao":
          return "あお";
        case "apricot":
          return "アプリコット";
        case "cha":
          return "ちゃいろ";
        case "haiiro":
          return "はいいろ";
        case "kiiro":
          return "きいろ";
        case "kimidori":
          return "きみどり";
        case "kogecha":
          return "こげちゃいろ";
        case "kuro":
          return "くろ";
        case "midori":
          return "みどり";
        case "murasaki":
          return "むらさき";
        case "pink":
          return "ピンク";
        case "shiro":
          return "しろ";
        case "odo":
          return "おうどいろ";
        case "komugi":
          return "こむぎいろ";
        case "creme":
          return "クリーム";
        case "deselect":
          return "なし";
      }
    };

  }]);
