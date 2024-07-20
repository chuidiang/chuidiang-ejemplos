Ejemplos de servicios en [Servicios en Angular](https://chuidiang.org/index.php?title=Servicios_en_Angular)

En este ejemplo se crean dos servicios: RootGreetingService y NullGreetingService. Ambos son iguales con la diferencia
de que en su **providedIn**, el primero define 'root' mientras que el segundo no define nada y deja el valor null por defecto.

El segundo creará una instancia nueva del servicio para cada módulo lazy loaded. Para verificarlo, en este mismo directorio hay dos
subdirectorios con dos módulos lazy loaded y el html neceario para que el usuario pueda pedir uno u otro. En console.log se mostrará
cada vez que se instancia un servicio. Podemos así verificar si se instancia nuevos servicios o son compartidos.