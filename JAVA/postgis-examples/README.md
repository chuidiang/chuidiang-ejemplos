Algunos experimentos con Java y Postgis.

Se trata de ver rendimiento a la hora de construir trayectorias que se van incrementando según el móvil va avanzando.

Con UpdateTrajectoriesMain se crean 10000 entradas en BD, cada una con una trayectoria y cada 
segundo se le añade un punto a cada una. En BD se guardar como MultiLine de Postgis y se le 
añade un punto.

Con InsertPointsThenCreateTrajectoriesMain se insertan en una tabla cada uno de los puntos de
cada móvil, con su timestamp. Una vez por minuto se construye una trayectoria completa con todos
los puntos del minuto anterior.

La ventaja del segundo frente al primero es que no hay tanto update, que al final a postgres le 
sientan mal (hay que hacer vacuum) y aparentemente es más rápido.