# FluxIT
Desafio FluxIT

Se propone realizar una app en la cual se puedan visualizar perfiles de
personas (usando la API RandomUser), con nombre e icono a gusto. Se
debe usar Retrofit para la conexión a servicios. La aplicación tendrá al
menos 2 Activities y features opcionales:

Activity 1
En la primer Activity se mostrará una lista de 20 personas, donde en cada
ítem de la lista se muestre el thumbnail de la persona y el username.
Mientras se carga la lista, se debe mostrar un loading y al finalizar el
llamado al servicio se debe mostrar un Toast con el resultado (Éxito o
Fallo). Al hacer tap sobre un ítem de la lista se abre la segunda activity.

Desafío técnico FluxIT. Flux IT S.A 2019 - fluxitsoft.com 1

Activity 2
En la segunda Activity se deberá mostrar la imagen de tamaño grande
arriba, y debajo información básica del usuario: Nombre, Apellido, edad e
Email. (Leer opcionales)

Opcionales
- MVC, MVP o MVVM como patrones de diseño.
-No es necesario realizar el modelo de clases completo, pero se
valorará la calidad de la solución y la profundidad del tema.
-Constraint Layouts o Coordinator layouts en el armado de XML.
- Agregar un pull to refresh, el cual llamará nuevamente al servicio y
actualizará los datos de la lista.
- Agregar un paginado o “infinite scroll”. Al alcanzar el final de la lista,
se llamará nuevamente al servicio con la página siguiente (Recorda usar
la misma seed).
- Agregar una forma de buscar por username.
- En la segunda activity, en vez de mostrar la imagen del usuario,
mostrar un Fragment con la localización de la persona (La cual es
brindada por la API) y la ubicación actual del usuario.
