var amtBronze = .50;
var amtSilver = .75;
var amtGold = 1.0;
var amtPlatinum = 1.5;
var amtDiamond = 2;
var amtMaster = 7.5;
var amtGrandmaster = 75;
var prices = [amtBronze, amtSilver, amtGold, amtPlatinum, amtDiamond, amtMaster, amtGrandmaster];

var app = angular.module('priceCalc', []);

app.controller('calcCtrl', function($scope) {
 $scope.start = 2500;
 $scope.end = 2500;
 $scope.diff = $scope.end - $scope.start;
 $scope.fixedDiff;
 $scope.price;
 $scope.calculateCost = function() {
  var SR = $scope.start;

  var SRGains = [0, 0, 0, 0, 0, 0, 0];

  var totalSRGain = $scope.end - $scope.start;

  var cost = 0;
  
  var i;
  for (i = 0; i < 7; i++) {
   var tierMaxSR = 0;

   if (i == 0) tierMaxSR = 1500;
   if (i == 1) tierMaxSR = 2000;
   if (i == 2) tierMaxSR = 2500;
   if (i == 3) tierMaxSR = 3000;
   if (i == 4) tierMaxSR = 3500;
   if (i == 5) tierMaxSR = 4000;
   if (i == 6) tierMaxSR = 5000;

   if ($scope.end > tierMaxSR) {
    if (SR < tierMaxSR) {
     SRGains[i] = tierMaxSR - SR;

     SR = tierMaxSR;
    }
   } else {
    SRGains[i] = $scope.end - SR;

    SR = $scope.end;
   }
  }

  for (i = 0; i < 7; i++) {
   cost += (SRGains[i] * prices[i] / 100.0);
  }
  
  $scope.price = cost;
 };
});