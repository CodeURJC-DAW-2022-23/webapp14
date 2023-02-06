# Fase 0

En esta fase vamos a definir y desarrollar nuestra aplicación web. Para ello hemos conformado un grupo de trabajo compuesto por:

- Alberto García Sroda
- Iván Sánchez Labrador
- Sergio López Cuesta

Siendo todos compañeros de otros años y sabiendo la afinidad que nos une, confiamos en nuestras habilidades para llevar a cabo este proyecto.

## UniHub

##### Descripción y requisitos principales

UniHub es el nombre que le hemos dado a nuestra página, la cual tiene como temática la distribución de packs y material educativo universitario. Esta tendrá varios apartados que incluyen elementos de interacción social los cuales trabajaremos a lo largo de futuras fases para tenerlos implementados.

La web ofrecerá a los usuarios la posibilidad de obtener, por medio de una transacción económica, recursos que les sirvan en su desarrollo académico. Estos packs estarán protegidos por el paywall ya mencionado, por lo que solo serán accesibles plenamente por los usuarios que se hayan registrado y que hayan realizado el pago correspondiente.

A su vez, contaremos con un apartado de foro en el cual los registrados podrán crear nuevos hilos para intercambiar opinones e información acerca de cualquier ocurrencia que tengan. Cabe destacar que los hilos serán visiblles para usuarios no registrados navegando en la web, pero no podrán interactuar con los mismos.

Por último y continuando con la importancia que le damos a la creación de una comunidad sana y que permita una escalabilidad y uso apropiado de la información, se dispondrá de un apartado en el que se puedan rellenar formularios y encuestas de diferentes características a los usuarios, pudiendo acceder a descuentos en determinados packs o recursos por el cumplimiento de estas. Al final lo que se busca es crear un ambiente donde cada usuario sienta que tiene un rincón personalizado que fomente el aprendizaje y la comunicación, con una plataforma que de cabida a crear una comunidad estable, que se retroalimente y pueda recurrir al uso de materiales que puedan ayudar a que otros o ellos mismos logren sus metas.

---

##### Entidades

De lo mencionado previamente tenemos una serie de entidades definidas que recogen las abstracciones de los diferentes elementos que tiene nuestra aplicación web que tendrán datos que se almacenarán en nuestras bases de datos. Estas entidades serían:
- Usuario
- Foro
- Encuesta
- Cursos

Las relaciones entre sí las podemos visualizar en el siguiente gráfico:

---

##### Tipos de usuarios

*Usuario anónimo*

Este usuario que no se registre y simplemente navegue por la página podrá visualizar el contenido del foro y los cursos disponibles para su compra, pero no podrá interactuar con estos elementos ni para responder o valorar en el foro, ni para realizar encuestas o acceder al contenido de cada curso o material.

*Usuario registrado*

Siempre que haya habido un registro, este usuario podrá comentar, valorar y crear nuevos hilos en el foro, acceder a la realización de encuestas, tener su apartado personal donde vea sus diferentes cursos o sugerencias, y podrá ver los diferentes packs de contenidos que tengamos disponibles, aunque tendrá que realizar un pago para poder acceder a ellos. Por ello los permisos de este usuario quedan definidos también.

*Usuario administrador*

Este usuario será el encargado de poder subir los cursos, teniendo acceso a areas de la web que quedarán ocultas de la navegación que puedan realizar los usuarios previos, como lo serían a su vez la realización de nuevas encuestas, acceso a estadísticas de dichas encuestas y gestión de usuarios registrados.

---

##### Imágenes y Gráficos

En cuanto a las imágenes y gráficos hemos dado pinceladas de estos previamente, donde mencionamos el acceso a gráficos de las encuestas, la creación de un perfil para los usuario registrados donde podrán tener su avatar o las propias imágenes que tendremos disponibles para cada pack de contenido.

---

##### Tecnología complementaria

A la hora de acceder al contenido de un pack mediante la realización de una transacción, el usuario recibirá una notificación en el correo que haya usado para registrarse. A su vez, al hacerlo, recibirá un correo de confirmación de este proceso para asegurar que se ha introducido un valor válido, en caso de no ser así, el acceso expirará.

---

##### Algoritmo de consultas

Para este apartado tenemos dos principales tecnologías:

* Búsqueda de información en la web por medio de palabras clave

  - Tendremos la posibilidad de buscar en el apartado de nuestro header visible en las principales pantallas, palabras clave que encuentren los contenidos y apartados deseados.
    
* Realización de sugerencias en base a cursos visitados y comprados
  - Al usuario registrado se le mostrará una ventana de sugerencias basadas en los elementos que visita o compra, coincidiendo por los tags que cada pack tiene asociados

---

> *La solución para todo lo necesario en el aula*
