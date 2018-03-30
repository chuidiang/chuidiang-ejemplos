# Ejemplo de Jedis y Redis

Se presupone que hay arrancado un Redis en la IP 192.168.99.100 y puerto 6379.

En la clase Main se hace un set y un get de un valor en una clave.

También se lanzan clases hilo para:
- Añadir elementos a una lista y retirarlos bloqueándose mientras no haya uno disponible. Son las clases
ListWriterThread y ListReaderThread.
- Añadir elementos a un Hash y suscribirse a cambios en el mismo. Son las clases HashSetThread y HashGetThread. Como
Redis no permite suscripción a claves concretas del Hash, creamos una lista en Redis (lastUpdatedKeys) en la que a la
vez que hacemos set en el hash, guardamos en la lista la key de lo que acabamos de cambiar. HashGetThread se bloquea
en espera de nuevos elementos añadidos en esa lista, recoge la clave cambiada del hash y saca dicho valor del hash para
ponerlo en pantalla.
- KeyspaceEventsSubscriptorThread se suscribe a todos los eventos que suceden en el keyspace de Redis, por lo que se
enterará de todas las claves que cambian, cada vez que se añade un elemento al hash o la lista.