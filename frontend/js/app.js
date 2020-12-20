let ProductCatalogApp = angular.module('ProductCatalogApp', []);

ProductCatalogApp.controller('ProductCatalogController', function ($scope, $http) {
    $scope.name = '';
    $scope.searchedProduct = '';
    $scope.description = '';
    $scope.formData = '';
    $http.get("http://localhost:8080/api/products/all")
        .then(resp => {
                $scope.productItemList = resp.data;
            },
            resp => {
                console.error(resp);
            });

    $scope.editOrCreate = function (item) {
        $scope.currentItem = item ? item : {};
        console.log(item);
    }
    $scope.showEdit = function (item) {
        $scope.formData = angular.copy(item);
    }
    $scope.getTemplate = function (item) {
        if (item.id == $scope.formData.id) {
            return 'edit';
        } else {
            return 'display';
        }
    }

    $scope.reset = function () {
        $scope.formData = {};
    }

    $scope.saveProduct = function (item, id) {
        angular.extend(item, {id: id});
        return $http.post("http://localhost:8080/api/products", item);
    };

    $scope.search = function (searchedProduct) {
        $http.get("http://localhost:8080/api/products/" + searchedProduct)
            .then(resp => {
                    $scope.productItemList = resp.data;
                },
                resp => {
                    console.error(resp);
                });
    }

    $scope.create = function (name, description) {

        $http.post("http://localhost:8080/api/products", {'name': name, 'description': description})
            .then(resp => {
                    $scope.productItemList[resp.data.id] = resp.data;
                    $scope.name = '';
                    $scope.description = '';
                    console.log($scope.productItemList);
                },
                resp => {
                    console.error(resp);
                });
        $scope.name = '';
        $scope.description = '';
    }

    $scope.delete = function (item) {
        $http.delete("http://localhost:8080/api/products/" + item.id)
            .then(resp => {
                    delete $scope.productItemList[item.id];
                },
                resp => {
                    console.error(resp);
                });
    }

    $scope.update = function (item, newItem) {
        $http.put("http://localhost:8080/api/products/" + item.id, {
            'name': newItem.name,
            'description': newItem.description
        })
            .then(resp => {
                    console.log($scope.item);
                    $scope.productItemList[item.id] = resp.data;

                    $scope.formData = {};

                },
                resp => {
                    console.error(resp);
                });
        $scope.name = '';
        $scope.description = '';
    }
});