Ejemplo del tutorial en [Routing en Angular](https://chuidiang.org/index.php?title=Routing_en_Angular)

En app.component.ts está la página principal, con un título y una barra de navegación a las páginas "One" y "Two". 

En one.component.ts y two.component.ts están esas dos páginas hijas, sencillas.

En app.routes.ts está el array de rutas para las páginas hijas y el menú de navegación.

En app.config.ts está la configuración de la aplicación, donde se indica al módulo de Routing de Angular donde está el array de rutas.

Finalmente, en main.ts está el main principal de la aplicación, donde se pasa adicionalmente la configuración de app.config.ts del arranque