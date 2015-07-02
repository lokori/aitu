// Copyright (c) 2015 The Finnish National Board of Education - Opetushallitus
//
// This program is free software:  Licensed under the EUPL, Version 1.1 or - as
// soon as they will be approved by the European Commission - subsequent versions
// of the EUPL (the "Licence");
//
// You may not use this work except in compliance with the Licence.
// You may obtain a copy of the Licence at: http://www.osor.eu/eupl/
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// European Union Public Licence for more details.

angular.module('arviointipaatokset', [])

  .config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/arviointipaatokset', {controller: 'ArviointipaatoksetController', templateUrl: 'template/arviointipaatokset'});
  }])

  .controller('ArviointipaatoksetController', ['$route', '$scope', 'Suorittaja', function($route, $scope, Suorittaja) {
    $scope.suorittajaForm = {};

    $scope.lisaaSuorittaja = function() {
      $scope.lisaaSuorittajaDialogi = false;
      Suorittaja.lisaa($scope.suorittajaForm).then(function() {
        $route.reload();
      });
    };

    $scope.tallennaSuorittaja = function() {
      $scope.lisaaSuorittajaDialogi = false;
      Suorittaja.tallenna($scope.suorittajaForm).then(function() {
        $route.reload();
      });
    };

    $scope.suorittajanLisays = function() {
      $scope.lisaaSuorittajaDialogi = true;
      $scope.suorittajaForm = {};
    };

    $scope.suorittajanMuokkaus = function(suorittaja) {
      $scope.lisaaSuorittajaDialogi = true;
      $scope.suorittajaForm = angular.copy(suorittaja);
    };
  }])
;