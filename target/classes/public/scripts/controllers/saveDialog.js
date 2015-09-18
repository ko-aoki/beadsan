angular.module('perlerbeadsApp')
    .controller('SaveDialogCtrl', ['$scope', '$modalInstance', 'name',
        function ($scope, $modalInstance, name) {

            $scope.tags = [];

            if (name !== undefined) {
                $scope.designName = name;
            }

            $scope.save = function () {
                $modalInstance.close($scope.designName);
            };

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };

            $scope.addTag = function() {
                if ($scope.tags.length >= 5) {
                    $scope.message = "タグは5個までです。";
                    return;
                }
                for (var i = 0; i < $scope.tags.length; i++) {
                    if ($scope.tags[i].name === $scope.tagInput) {
                        $scope.message = "同じ名前のタグがあります。";
                        return;
                    }

                }
                $scope.message = "";
                $scope.tags.push({name: $scope.tagInput});
            }

            $scope.delTag = function(idx) {
                $scope.message = "";
                $scope.tags.splice(idx, 1);
            }


        }]);
