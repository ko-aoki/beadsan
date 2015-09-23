angular.module('perlerbeadsApp')
    .controller('SaveDialogCtrl', ['$scope', '$modalInstance', 'name','tags',
        function ($scope, $modalInstance, name, tags) {

            if (name !== undefined) {
                $scope.designName = name;
            }

            if (tags !== undefined)  {
                $scope.tags = tags;
            } else {
                $scope.tags = [];
            }

            $scope.save = function () {
                $modalInstance.close({
                    "designName": $scope.designName,
                    "tags": $scope.tags
                });
            };

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };

            $scope.addTag = function() {
                if ($scope.tagInput === undefined) {
                    return;
                }
                if ($scope.tags.length >= 5) {
                    $scope.message = "タグは5個までです。";
                    return;
                }
                for (var i = 0; i < $scope.tags.length; i++) {
                    if ($scope.tags[i] === $scope.tagInput) {
                        $scope.message = "同じ名前のタグがあります。";
                        return;
                    }
                }
                $scope.message = "";
                $scope.tags.push($scope.tagInput);
            }

            $scope.delTag = function(idx) {
                $scope.message = "";
                $scope.tags.splice(idx, 1);
            }

        }]);
