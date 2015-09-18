angular.module('palette').service('circlePalette',
  function () {

    this.coordinates =[
      [{left:'194',top:'189'}],
      [{ left:'180',top:'165'},	{ left:'207',top:'165'},	{ left:'221',top:'187'},	{ left:'207',top:'211'},	{ left:'180',top:'211'},	{ left:'167',top:'187'}],
      [{ left:'194',top:'137'},	{ left:'220',top:'143'},	{ left:'239',top:'163'},	{ left:'247',top:'190'},	{ left:'237',top:'215'},	{ left:'220',top:'237'},	{ left:'194',top:'242'},	{ left:'167',top:'237'},	{ left:'148',top:'215'},	{ left:'139',top:'190'},	{ left:'145',top:'163'},	{ left:'162',top:'143'}],
      [{ left:'208',top:'111'},	{ left:'234',top:'120'},	{ left:'255',top:'138'},	{ left:'269',top:'162'},	{ left:'274',top:'190'},	{ left:'265',top:'215'},	{ left:'251',top:'241'},	{ left:'233',top:'261'},	{ left:'208',top:'269'},	{ left:'180',top:'269'},	{ left:'154',top:'261'},	{ left:'132',top:'241'},	{ left:'118',top:'215'},	{ left:'110',top:'190'},	{ left:'116',top:'162'},	{ left:'130',top:'138'},	{ left:'151',top:'120'},	{ left:'179',top:'111'}],
      [{ left:'195',top:'80'},	{ left:'222',top:'87'},	{ left:'247',top:'97'},	{ left:'269',top:'114'},	{ left:'287',top:'135'},	{ left:'298',top:'162'},	{ left:'300',top:'191'},	{ left:'294',top:'217'},	{ left:'283',top:'242'},	{ left:'265',top:'265'},	{ left:'247',top:'284'},	{ left:'222',top:'294'},	{ left:'195',top:'297'},	{ left:'165',top:'294'},	{ left:'140',top:'284'},	{ left:'119',top:'265'},	{ left:'100',top:'242'},	{ left:'88',top:'217'},	{ left:'83',top:'191'},	{ left:'87',top:'162'},	{ left:'98',top:'135'},	{ left:'113',top:'114'},	{ left:'137',top:'97'},	{ left:'164',top:'87'}],
      [{ left:'208',top:'55'},	{ left:'235',top:'64'},	{ left:'261',top:'73'},	{ left:'285',top:'91'},	{ left:'303',top:'110'},	{ left:'316',top:'135'},	{ left:'326',top:'163'},	{ left:'329',top:'190'},	{ left:'322',top:'217'},	{ left:'312',top:'243'},	{ left:'298',top:'268'},	{ left:'280',top:'288'},	{ left:'261',top:'307'},	{ left:'235',top:'319'},	{ left:'208',top:'323'},	{ left:'181',top:'323'},	{ left:'153',top:'319'},	{ left:'128',top:'307'},	{ left:'104',top:'288'},	{ left:'85',top:'268'},	{ left:'70',top:'243'},	{ left:'58',top:'217'},	{ left:'55',top:'190'},	{ left:'59',top:'163'},	{ left:'67',top:'134'},	{ left:'81',top:'110'},	{ left:'100',top:'91'},	{ left:'123',top:'73'},	{ left:'150',top:'61'},	{ left:'179',top:'55'}],
      [{ left:'192',top:'27'},	{ left:'220',top:'29'},	{ left:'248',top:'38'},	{ left:'274',top:'50'},	{ left:'297',top:'66'},	{ left:'318',top:'85'},	{ left:'333',top:'108'},	{ left:'345',top:'135'},	{ left:'353',top:'161'},	{ left:'355',top:'189'},	{ left:'351',top:'217'},	{ left:'340',top:'244'},	{ left:'332',top:'269'},	{ left:'313',top:'292'},	{ left:'295',top:'312'},	{ left:'273',top:'329'},	{ left:'249',top:'341'},	{ left:'223',top:'347'},	{ left:'192',top:'350'},	{ left:'166',top:'347'},	{ left:'139',top:'341'},	{ left:'113',top:'329'},	{ left:'89',top:'312'},	{ left:'70',top:'292'},	{ left:'53',top:'269'},	{ left:'41',top:'244'},	{ left:'32',top:'217'},	{ left:'30',top:'189'},	{ left:'32',top:'161'},	{ left:'38',top:'135'},	{ left:'50',top:'108'},	{ left:'66',top:'85'},	{ left:'87',top:'66'},	{ left:'109',top:'50'},	{ left:'135',top:'36'},	{ left:'166',top:'29'}],
      [{ left:'205',top:'2'},	{ left:'235',top:'5'},	{ left:'262',top:'14'},	{ left:'288',top:'27'},	{ left:'313',top:'42'},	{ left:'333',top:'62'},	{ left:'350',top:'85'},	{ left:'365',top:'110'},	{ left:'374',top:'135'},	{ left:'380',top:'163'},	{ left:'381',top:'192'},	{ left:'376',top:'218'},	{ left:'369',top:'247'},	{ left:'359',top:'273'},	{ left:'345',top:'296'},	{ left:'328',top:'318'},	{ left:'309',top:'337'},	{ left:'287',top:'353'},	{ left:'262',top:'366'},	{ left:'237',top:'375'},	{ left:'210',top:'378'},	{ left:'183',top:'378'},	{ left:'154',top:'375'},	{ left:'125',top:'366'},	{ left:'101',top:'353'},	{ left:'76',top:'337'},	{ left:'55',top:'318'},	{ left:'37',top:'296'},	{ left:'23',top:'273'},	{ left:'10',top:'247'},	{ left:'2',top:'218'},	{ left:'0',top:'192'},	{ left:'2',top:'163'},	{ left:'10',top:'135'},	{ left:'20',top:'110'},	{ left:'33',top:'85'},	{ left:'51',top:'62'},	{ left:'72',top:'42'},	{ left:'95',top:'27'},	{ left:'121',top:'14'},	{ left:'150',top:'5'},	{ left:'177',top:'2'}]
    ];
    this.thunmbnailCoordinates =[
      [{ left:'49',top:'47'}],
      [{ left:'45',top:'41'},	{ left:'52',top:'41'},	{ left:'55',top:'47'},	{ left:'52',top:'53'},	{ left:'45',top:'53'},	{ left:'42',top:'47'}],
      [{ left:'49',top:'34'},	{ left:'55',top:'36'},	{ left:'60',top:'41'},	{ left:'62',top:'48'},	{ left:'59',top:'54'},	{ left:'55',top:'59'},	{ left:'49',top:'61'},	{ left:'42',top:'59'},	{ left:'37',top:'54'},	{ left:'35',top:'48'},	{ left:'36',top:'41'},	{ left:'40',top:'36'}],
      [{ left:'52',top:'28'},	{ left:'59',top:'30'},	{ left:'64',top:'35'},	{ left:'67',top:'41'},	{ left:'69',top:'48'},	{ left:'66',top:'54'},	{ left:'63',top:'60'},	{ left:'58',top:'65'},	{ left:'52',top:'67'},	{ left:'45',top:'67'},	{ left:'39',top:'65'},	{ left:'33',top:'60'},	{ left:'30',top:'54'},	{ left:'28',top:'48'},	{ left:'29',top:'41'},	{ left:'33',top:'35'},	{ left:'38',top:'30'},	{ left:'45',top:'28'}],
      [{ left:'49',top:'20'},	{ left:'56',top:'22'},	{ left:'62',top:'24'},	{ left:'67',top:'29'},	{ left:'72',top:'34'},	{ left:'75',top:'41'},	{ left:'75',top:'48'},	{ left:'74',top:'54'},	{ left:'71',top:'61'},	{ left:'66',top:'66'},	{ left:'62',top:'71'},	{ left:'56',top:'74'},	{ left:'49',top:'74'},	{ left:'41',top:'74'},	{ left:'35',top:'71'},	{ left:'30',top:'66'},	{ left:'25',top:'61'},	{ left:'22',top:'54'},	{ left:'21',top:'48'},	{ left:'22',top:'41'},	{ left:'25',top:'34'},	{ left:'28',top:'29'},	{ left:'34',top:'24'},	{ left:'41',top:'22'}],
      [{ left:'52',top:'14'},	{ left:'59',top:'16'},	{ left:'65',top:'18'},	{ left:'71',top:'23'},	{ left:'76',top:'28'},	{ left:'79',top:'34'},	{ left:'82',top:'41'},	{ left:'82',top:'48'},	{ left:'81',top:'54'},	{ left:'78',top:'61'},	{ left:'75',top:'67'},	{ left:'70',top:'72'},	{ left:'65',top:'77'},	{ left:'59',top:'80'},	{ left:'52',top:'81'},	{ left:'45',top:'81'},	{ left:'38',top:'80'},	{ left:'32',top:'77'},	{ left:'26',top:'72'},	{ left:'21',top:'67'},	{ left:'18',top:'61'},	{ left:'15',top:'54'},	{ left:'14',top:'48'},	{ left:'15',top:'41'},	{ left:'17',top:'34'},	{ left:'20',top:'28'},	{ left:'25',top:'23'},	{ left:'31',top:'18'},	{ left:'38',top:'15'},	{ left:'45',top:'14'}],
      [{ left:'48',top:'7'},	{ left:'55',top:'7'},	{ left:'62',top:'10'},	{ left:'69',top:'13'},	{ left:'74',top:'17'},	{ left:'80',top:'21'},	{ left:'83',top:'27'},	{ left:'86',top:'34'},	{ left:'88',top:'40'},	{ left:'89',top:'47'},	{ left:'88',top:'54'},	{ left:'85',top:'61'},	{ left:'83',top:'67'},	{ left:'78',top:'73'},	{ left:'74',top:'78'},	{ left:'68',top:'82'},	{ left:'62',top:'85'},	{ left:'56',top:'87'},	{ left:'48',top:'88'},	{ left:'42',top:'87'},	{ left:'35',top:'85'},	{ left:'28',top:'82'},	{ left:'22',top:'78'},	{ left:'18',top:'73'},	{ left:'13',top:'67'},	{ left:'10',top:'61'},	{ left:'8',top:'54'},	{ left:'8',top:'47'},	{ left:'8',top:'40'},	{ left:'10',top:'34'},	{ left:'13',top:'27'},	{ left:'17',top:'21'},	{ left:'22',top:'17'},	{ left:'27',top:'13'},	{ left:'34',top:'9'},	{ left:'42',top:'7'}],
      [{ left:'51',top:'0'},	{ left:'59',top:'1'},	{ left:'66',top:'4'},	{ left:'72',top:'7'},	{ left:'78',top:'11'},	{ left:'83',top:'16'},	{ left:'88',top:'21'},	{ left:'91',top:'28'},	{ left:'94',top:'34'},	{ left:'95',top:'41'},	{ left:'95',top:'48'},	{ left:'94',top:'55'},	{ left:'92',top:'62'},	{ left:'90',top:'68'},	{ left:'86',top:'74'},	{ left:'82',top:'80'},	{ left:'77',top:'84'},	{ left:'72',top:'88'},	{ left:'66',top:'92'},	{ left:'59',top:'94'},	{ left:'53',top:'95'},	{ left:'46',top:'95'},	{ left:'39',top:'94'},	{ left:'31',top:'92'},	{ left:'25',top:'88'},	{ left:'19',top:'84'},	{ left:'14',top:'80'},	{ left:'9',top:'74'},	{ left:'6',top:'68'},	{ left:'3',top:'62'},	{ left:'1',top:'55'},	{ left:'0',top:'48'},	{ left:'1',top:'41'},	{ left:'3',top:'34'},	{ left:'5',top:'28'},	{ left:'8',top:'21'},	{ left:'13',top:'16'},	{ left:'18',top:'11'},	{ left:'24',top:'7'},	{ left:'30',top:'4'},	{ left:'38',top:'1'},	{ left:'44',top:'0'}]
    ];

    //this.thunmbnailTopOffset = 40;
    //this.thunmbnailRecordOffset = 150;

    this.makePalette = function (isThumbnail, idx, itemSize) {
      var diameter, circumference, topOffset, recordOffset, margin;
      var beads = new Array(this.coordinates.length);
      if (isThumbnail) {
        //topOffset = this.thunmbnailTopOffset;
        //recordOffset = this.thunmbnailRecordOffset * (idx % itemSize);
        for (diameter = 0; diameter < this.thunmbnailCoordinates.length; diameter++) {
          beads[diameter] = new Array(this.thunmbnailCoordinates[diameter].length);
          for (circumference = 0; circumference < this.thunmbnailCoordinates[diameter].length; circumference++) {
            beads[diameter][circumference] = {};
            //beads[diameter][circumference].top = parseInt(this.thunmbnailCoordinates[diameter][circumference].top, 10) + topOffset;
            //beads[diameter][circumference].left = parseInt(this.thunmbnailCoordinates[diameter][circumference].left, 10) + recordOffset;
            beads[diameter][circumference].top = parseInt(this.thunmbnailCoordinates[diameter][circumference].top, 10);
            beads[diameter][circumference].left = parseInt(this.thunmbnailCoordinates[diameter][circumference].left, 10);
            beads[diameter][circumference].color = 'deselect';
          }
        }
      } else {
        for (diameter = 0; diameter < this.coordinates.length; diameter++) {
          beads[diameter] = new Array(this.coordinates[diameter].length);
          for (circumference = 0; circumference < this.coordinates[diameter].length; circumference++) {
            beads[diameter][circumference] = {};
            beads[diameter][circumference].top = this.coordinates[diameter][circumference].top;
            beads[diameter][circumference].left = this.coordinates[diameter][circumference].left;
            beads[diameter][circumference].color = 'deselect';
          }
        }
      }
      return beads;
    };

    this.makeThumbnailPalette = function (idx, itemSize) {
      return this.makePalette(true, idx, itemSize);
    };

    this.convert = function(data, isThumbnail, idx, itemSize) {
      var diameter , circumference;
      var convData = [];
      if (isThumbnail) {
        convData = this.makeThumbnailPalette(idx, itemSize);
      } else {
        convData = this.makePalette();
      }
      for(diameter = 0; diameter < convData.length; diameter++) {
        for(circumference = 0; circumference < convData[diameter].length; circumference++) {
          if (isThumbnail) {
            convData[diameter][circumference].color = data[diameter][circumference];
          } else {
            convData[diameter][circumference].color = data[diameter][circumference].color;
          }
        }
      }
      return convData;
    };
  });
