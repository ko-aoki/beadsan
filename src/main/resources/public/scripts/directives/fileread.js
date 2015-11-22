/**
 * Created by ko-aoki on 2015/11/22.
 */
angular.module('perlerbeadsApp')
.directive('ngBeadsanFileread', [function () {
 return {
     scope: {
         fileread: "="
     },
     link: function (scope, element) {
         element.bind("change", function (changeEvent) {
             var reader = new FileReader();
             reader.onload = function (loadEvent) {
                 scope.$apply(function () {
                     scope.fileread = loadEvent.target.result;
                 });
             }
             reader.readAsDataURL(changeEvent.target.files[0]);
         });
     }
 }
}]);

