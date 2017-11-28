angular.module('app', [])

angular.module('app').service ('eventBus', function(){

    this.eventBus = new EventBus('http://localhost:8081/eventbus/');
    this.conectado = false

    this.eventBus.onopen = () => {
       this.conectado = true
    };

    this.eventBus.onerror = function(err) {
        this.conectado = false
    }

    this.registerHandler = (topic, handler) => {
       if (!this.conectado){
          setTimeout( () => {
                if (!this.conectado){
                   console.log("No conectado tras dos segundos ");
                   return;
                }
                this.eventBus.registerHandler(topic,handler);
          },2000 )
          return;
       }
       this.eventBus.registerHandler(topic, handler);
    }

    this.publish = (topic, data) => {
       if (!this.conectado){
          setTimeout(()=>{
             if (!this.conectado){
                console.log("No conectado en dos segundos")
                return
             }
             this.eventBus.publish(topic,data)
          },2000)
          return
       }
       this.eventBus.publish(topic,data)
    }
})


angular.module('app').controller('appController', ['$scope','$http','eventBus', function($scope, $http, eventBus) {
    var app = this;

    console.log("inicializacion controller");

    app.texto='Hola Angular';

    app.llamadaGet = function (){
        $http.get('/random').
                then(function(response) {
                    app.random = response.data.value;
                });
    }

    app.publish = function (topic, data){
       eventBus.publish(topic,data)
    }

    console.log("Registrando handler");
    eventBus.registerHandler("prueba", function (err, msg) {
        console.log("I have received a message: " + msg.body);
        $scope.$apply(function(){
            app.eventBusReceived = msg.body;
        });
    });

}])

