angular.module('app', [])
  .controller('appController', function($scope, $http) {
    var app = this;

    console.log("inicializacion controller");

    app.eventBus = new EventBus('http://localhost:8081/eventbus/');
    app.eventBus.onopen = () => {
           console.log("Registrando handler");
           app.eventBus.registerHandler("prueba", function (err, msg) {
              console.log("I have received a message: " + msg.body);
              $scope.$apply(function(){
                app.eventBusReceived = msg.body;
              });
           });
        };
    app.eventBus.onerror = function(err) {
        console.log(err);
    }



    app.texto='Hola Angular';

    app.llamadaGet = function (){
        $http.get('/random').
                then(function(response) {
                    app.random = response.data.value;
                });
    }
})