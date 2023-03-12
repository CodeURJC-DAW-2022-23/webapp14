# Fase 2

En esta fase vamos a definir y desarrollar nuestra aplicación web. Para ello hemos conformado un grupo de trabajo compuesto por:

-  Alberto García Sroda
-  Iván Sánchez Labrador
-  Sergio López Cuesta

Siendo todos compañeros de otros años y sabiendo la afinidad que nos une, confiamos en nuestras habilidades para llevar a cabo este proyecto.

## UniHub

##### Descripción y requisitos principales

UniHub es el nombre que le hemos dado a nuestra página, la cual tiene como temática la distribución de packs y material educativo universitario. Esta tendrá varios apartados que incluyen elementos de interacción social los cuales trabajaremos a lo largo de futuras fases para tenerlos implementados.

La web ofrecerá a los usuarios la posibilidad de obtener recursos que les sirvan en su desarrollo académico. Estos packs estarán protegidos por un paywall en un futuro, por lo que solo serán accesibles plenamente por los usuarios que se hayan registrado y que hayan realizado el pago correspondiente. Actualmente lo que tenemos es que cualquier usuario registrado puede acceder a documentos generados para estos. Sin ser la función original, teniendo la lógica inherente a poder usar archivos y cargarlos desde la base de datos, confiamos en que lo que varía es el contenido dado no el hecho de darlo en sí. Más adelante comentaremos estos cambios de funcionlidad que hemos tenido entre una fase y otra.

A su vez, contaremos con un apartado de foro en el cual los registrados podrán crear nuevos hilos para intercambiar opinones e información acerca de cualquier ocurrencia que tengan. Cabe destacar que los hilos serán visiblles para usuarios no registrados navegando en la web, pero no podrán interactuar con los mismos.

Por último y continuando con la importancia que le damos a la creación de una comunidad sana y que permita una escalabilidad y uso apropiado de la información, decidimos dejar la funcionalidad de poder implementar descuentos en nuestra página web a partir de encuestas para un futuro. Esta funcionalidad junto con la de que los usuarios hagan transacciones económicas usando la API de PayPal son las únicas que hemos decidido posponer ya que primero queríamos crear una fundación que estuviese correcta e interconectada. Por otro lado hemos implementado diferentes apartados que comentaremos más adelante.

Al final lo que se busca es crear un ambiente donde cada usuario sienta que tiene un rincón personalizado que fomente el aprendizaje y la comunicación, con una plataforma que de cabida a crear una comunidad estable, que se retroalimente y pueda recurrir al uso de materiales que puedan ayudar a que otros o ellos mismos logren sus metas.

---

##### Entidades

De lo mencionado previamente teníamos una serie de entidades definidas que recogen las abstracciones de los diferentes elementos que tiene nuestra aplicación web que tendrán datos que se almacenarán en nuestras bases de datos. Estas entidades eran:

-  Usuario
-  Foro
-  Encuesta
-  Cursos

Pero al llevar a cabo un análisis más profundo vimos que estas se ramificaban un poco más añadiendo

Las relaciones entre sí las podemos visualizar en el siguiente gráfico:

![Screenshot 2023-03-12 131918](https://user-images.githubusercontent.com/63263060/224544137-c2331bc1-1a7a-4560-bae3-108d697e7c74.png)

---

##### Tipos de usuarios

_Usuario anónimo_

Este usuario que no se registre y simplemente navegue por la página podrá visualizar el contenido del foro y los cursos disponibles para su compra, pero no podrá interactuar con estos elementos ni para responder o valorar en el foro, ni para realizar encuestas o acceder al contenido de cada curso o material en profundidad. A su vez se pueden realizar búsquedas desde la barra del header para encontrar palabras clave y mostrar los cursos que las tengan.

_Usuario registrado_

Siempre que haya habido un registro y su posterior logeo, este usuario podrá comentar, valorar y crear nuevos hilos en el foro, acceder a la realización de encuestas así como tener su apartado personal donde podrá ver los diferentes packs de contenidos que haya cogido. Esto se suma a todas las capacidades anteriores.

_Usuario administrador_

Este usuario pierde algunas funcionalidades con respecto a un usuario registrado, ya que no puede crear un hilo ni ver sus cursos canjeados al no tener la necesidad de adquirir ninguno. En cambio gana la posibilidad de eliminar diferentes hilos del sistema si se han creado y ve necesario el borrarlos dentro de su área personal.

---

##### Imágenes y Gráficos

En cuanto a las imágenes y gráficos tenemos que en la creación de un perfil para los usuario registrados, podrán tener su avatar seleccionado cargando una imagen desde su dispositivo, o las propias imágenes que un usuario puede elegir a la hora de crear los diferentes hilos que quiera.

---

##### Tecnología complementaria

A la hora de acceder a la página y registrarse, recibirá un correo de confirmación de este proceso donde el usuario podrá ver que efectivamente se ha registrado correctamente. Esto es gestionado mediante el uso de la API javaMail

---

##### Algoritmo de consultas

Para este apartado tenemos dos principales tecnologías:

-  Búsqueda de información en la web por medio de palabras clave. Donde cualquier usuario puede escribir fragmentos que quiera encontrar dentro de las descripciones de los cursos y verlos directamente mostrados en la pantalla.

-  Mediante una query personalizada cada usuario ve los cursos que ha comprado en su apartado personal.

Decidimos eliminar el proceso de sugerencias a cursos para poder incluirlo más adelante, pero nos centramos en funcionalidades a priori más sencillas como las que mencionamos, que sigan añadiendo valor a nuestra web.

---

##### Navegación

La navegación por nuestra web queda definida con los roles de cada usuario mediante el siguiente gráfico donde mostramos la disponibilidad y relación de cada template de forma gráfica. Cabe destacar que hay opciones dentro de cada template que son los que varían entre un usuario y otro como mencionamos en el apartado de los Tipos de Usuarios. Ya que un usuario no registrado verá el foro pero no puede votar y no ve el apartado para comentar, entre otras diferencias subyacentes.

## ![Screenshot 2023-03-12 132149](https://user-images.githubusercontent.com/63263060/224544378-cdd6b9a2-beb6-4e8a-a69b-27686349c32e.png)

---

##### Instrucciones de ejecución

De lo mencionado previamente teníamos una serie de entidades definidas que recogen las abstracciones de los diferentes elementos que tiene nuestra aplicación web que tendrán datos que se almacenarán en nuestras bases de datos. Estas entidades eran:

---

##### Diagrama de clases y templates

De lo mencionado previamente teníamos una serie de entidades definidas que recogen las abstracciones de los diferentes elementos que tiene nuestra aplicación web que tendrán datos que se almacenarán en nuestras bases de datos. Estas entidades eran:
Diagrama1:

![WhatsApp_Image_2023-03-05_at_14 12 06 (1)](https://user-images.githubusercontent.com/63263060/224544801-048f6b99-7b13-4328-b8dd-a9e2292772fd.jpeg)

---

> _La solución para todo lo necesario en el aula_
