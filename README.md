# Fase 2

En esta fase hemos transformado nuestros archivos html y css para corresponder mejor con la idea de la aplicación que teníamos. Creamos un proyecto con Spring, añadiendo las diferentes dependencias necesarias para este. Posteriormente añadimos como templates los html, que ahora usarán la sintaxis de mustache para poder cargar elementos de formá dinámica en la propia página.

Tras esto y centrándonos en la arquitectura MVC (modelo, vista, controlador), empezamos a crear controladores para gestionar las diferentes URLs de las páginas, permitiendo a los usuarios interactuar, registrarse, crear foros, descargar contenido e incluso implementando las funcionalidades de AJAX para cargar cursos. Por lo que hemos acabado con una carpeta Backend donde tendríamos todos los diferentes apartados que se relacionan entre sí y que gestionan la carga y guardado de datos en la propia base de datos mySQL, la carga de elementos dinámicos y específicos a cada usuario y otras funcionalidades que comentaremos a lo largo de esta memoria.

En cuanto a los miembros del grupo, su participación y commits significativos y partiendo de la base de que todos conocemos el funcionamiento de los apartados que no se mencionan en nuestros commits significativos:

-  Alberto García Sroda

##### Participación y Contribución

Entre mis contribuciones destacaría la de implementar AJAX, la funcionalidad del Foro, arreglos visuales con el CSS para mantener los estilos visualmente atractivos hacia el usuario y además el centrarme en el apartados del Area Cliente para visualizar cursos comprados o eliminar hilos según el tipo de usuario que entre en el apartado de cliente. Mis commits más significativos son:

1. https://github.com/CodeURJC-DAW-2022-23/webapp14/commit/a800897c1f5272934f3de17340dcd56d8037c64f
2. https://github.com/CodeURJC-DAW-2022-23/webapp14/commit/294435b45df4add2288d2f9dad076b86f249ff6f
3. https://github.com/CodeURJC-DAW-2022-23/webapp14/commit/3aaa6f4802ea6e73586fcbc74808fefe2754967b
4. https://github.com/CodeURJC-DAW-2022-23/webapp14/commit/867b49cac03e8d883fc02a85f8bc8cffc99d386d
5. https://github.com/CodeURJC-DAW-2022-23/webapp14/commit/4a03cced2282cc8909fe97cb9a857cc8209035bc

---

-  Iván Sánchez Labrador

##### Participación y Contribución

Entre mis contribuciones destacaría la parte correspondiente a la seguridad de la aplicación web con Spring Boot Security, la implementación de la tecnología extra correspondiente al envío de un correo electrónico de confirmación de registro para el usuario, la funcionalidad de descarga de un pdf con los materiales del curso seleccionado, la carga de imágenes del usuario para poder modificar su avatar dentro del area cliente.También he participado activamente en el desarrollo de la mayoría de los archivos del frontend para crear una interfaz de usuario atractiva y fácil de usar. Mis commits más significativos son:

1. https://github.com/CodeURJC-DAW-2022-23/webapp14/commit/cf1b733eb3eb4c794b1e7ec28add742601a5e3a9
2. https://github.com/CodeURJC-DAW-2022-23/webapp14/commit/7477ff440fc34006f82191fc190819aa53d378ba
3. https://github.com/CodeURJC-DAW-2022-23/webapp14/commit/318c1cf44b35b3f410d4b26fb31de95393609654#diff-c18ee21c2ccb2e35368ec10eb2351b79349dee5df6ab59142e8a4e0f3c0d651e
4. https://github.com/CodeURJC-DAW-2022-23/webapp14/commit/8b0945d5b7d32d0399e015ecf6b699a2e222c05b
5. https://github.com/CodeURJC-DAW-2022-23/webapp14/commit/ec8bf2605608f3653df6be9ed9ce3932c93b24a3

---

-  Sergio López Cuesta

##### Participación y Contribución

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

Pero al llevar a cabo un análisis más profundo vimos que estas se ramificaban un poco más, por lo que añadimos una entidad Tags para guardar la información de cada uno de estos, también dividimos la información del foro para tener Forms y Post, así como otra que tuviese las imágenes que se pueden seleccionar para un hilo con ThreadPics.

Las relaciones entre sí ya actualizadas se pueden ver en el siguiente gráfico:

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

##### Diagrama de clases y templates

Video demo mostrando el comportamiento y funcionalidad de la aplicación web
https://youtu.be/KWRKlhbISiY

---

##### Instrucciones de ejecución

En cuanto a los pasos para poder descargar el código del repositorio, el primero sería clonarlo a través de github. La versión mínima de java que se debería tener instalada es la 17, el modelVersion seria 4.0.0, la versión de MySQL es la 8.0.32 y la versión de springboot es la 2.7.7. El proyecto se configuró y creo a través de la siguiente página web: https://start.spring.io/

---

##### Diagrama de clases y templates

De lo mencionado previamente teníamos una serie de entidades definidas que recogen las abstracciones de los diferentes elementos que tiene nuestra aplicación web que tendrán datos que se almacenarán en nuestras bases de datos. Estas entidades eran:
Diagrama1:

![WhatsApp_Image_2023-03-05_at_14 12 06 (1)](https://user-images.githubusercontent.com/63263060/224544801-048f6b99-7b13-4328-b8dd-a9e2292772fd.jpeg)

En cuanto al diagrama de clases y templates se puede visualizar en la siguiente imagen:

![WhatsApp Image 2023-03-10 at 14 03 31](https://user-images.githubusercontent.com/79569564/224545059-0acd15ed-ccff-4468-bec5-1b082a763e51.jpeg)

---

> _La solución para todo lo necesario en el aula_
