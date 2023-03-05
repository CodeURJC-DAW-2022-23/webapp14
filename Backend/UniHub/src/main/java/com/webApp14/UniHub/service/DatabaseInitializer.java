package com.webApp14.UniHub.service;

import com.webApp14.UniHub.model.*;
import com.webApp14.UniHub.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class DatabaseInitializer {

    @Autowired
    private FormsRepository formsRepository;
    @Autowired
    private PackRepository packRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TagsRepository tagsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ThreadPicsRepository threadPicsRepository;

    @PostConstruct
    private void init() throws IOException, URISyntaxException{

        // Thread Pictures route GENERATION
        ThreadPics pic1 = new ThreadPics("/static/img/Forms-Icons/ballings.svg","img1");
        ThreadPics pic2 = new ThreadPics("/static/img/Forms-Icons/code.svg","img2");
        ThreadPics pic3 = new ThreadPics("/static/img/Forms-Icons/statistics.svg","img3");
        ThreadPics pic4 = new ThreadPics("/static/img/Forms-Icons/thunder_icon.svg","img4");
        threadPicsRepository.saveAll(Arrays.asList(pic1,pic2,pic3,pic4));


        // Tag CREATION
        Tags tag1 = new Tags("Programacion","tag-programming", new ArrayList<>());
        Tags tag2 = new Tags("Logica","tag-logic", new ArrayList<>());
        Tags tag3 = new Tags("Computadores","tag-hardware", new ArrayList<>());
        Tags tag4 = new Tags("Mates", "tag-math",new ArrayList<>());

        //********************************************************************
        // Pack1 CREATION
        Pack pack1 = new Pack("DAW", "img/Subject-Icons/ED.png", "Diseño de Aplicaciones Web", "Diseño y desarrollo de aplicaciones web modernas, utilizando lenguajes como HTML, CSS y JavaScript para crear aplicaciones tocando temas de arquitectura web, seguridad y bases de datos", """
                La asignatura de Diseño de Aplicaciones Web es una materia que forma parte de la carrera de ingeniería en informática o sistemas, y tiene como objetivo enseñar a los estudiantes a diseñar y desarrollar aplicaciones web modernas y eficientes.

                Durante el curso, los estudiantes aprenden sobre los diferentes lenguajes, herramientas y tecnologías que se utilizan en el desarrollo de aplicaciones web, incluyendo HTML, CSS, JavaScript, frameworks de desarrollo como Angular, React o Vue.js, y lenguajes de programación del lado del servidor como Java, PHP o Python.

                La asignatura cubre también temas como arquitectura web, diseño de interfaces de usuario, bases de datos, seguridad en aplicaciones web, optimización de rendimiento, integración con servicios web y APIs, entre otros.

                En resumen, la asignatura de diseño de aplicaciones web es fundamental para cualquier estudiante que quiera convertirse en un desarrollador web profesional, ya que les proporciona los conocimientos y habilidades necesarios para crear aplicaciones web de alta calidad y con un buen desempeño, cumpliendo con los estándares actuales de la industria.""",
                9.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag1.getPacks().add(pack1);
        tag2.getPacks().add(pack1);
        pack1.getTags().add(tag1);
        pack1.getTags().add(tag2);

        // Saving the pack1 with its tags
        packRepository.save(pack1);
        tagsRepository.saveAll(Arrays.asList(tag1,tag2));

        // Pack2 CREATION
        Pack pack2 = new Pack("DAA", "img/Subject-Icons/ED.png","Diseño y Análisis de Algoritmos", "Diseño y Análisis de Algoritmos enseña a diseñar algoritmos eficientes para resolver problemas computacionales. Esencial para estudiantes de Ciencias de la Computación o Ingeniería en Sistemas Computacionales" , """
                La asignatura de Diseño y Análisis de Algoritmos es una materia fundamental en la carrera de Ciencias de la Computación o Ingeniería en Sistemas Computacionales. Esta asignatura se enfoca en enseñar a los estudiantes cómo diseñar y analizar algoritmos eficientes para resolver problemas computacionales.

                Durante el curso, los estudiantes aprenden técnicas para diseñar algoritmos, incluyendo estrategias de dividir y conquistar, programación dinámica, algoritmos voraces, entre otras. También aprenden cómo analizar la eficiencia de los algoritmos, incluyendo la notación O-grande y el análisis amortizado.

                Además, los estudiantes aprenden a aplicar estas técnicas de diseño y análisis de algoritmos a una variedad de problemas, incluyendo problemas de ordenamiento, búsqueda, grafos, redes y optimización.

                Otras áreas de estudio en la asignatura pueden incluir el estudio de algoritmos de aproximación y algoritmos probabilísticos, así como el uso de herramientas computacionales para ayudar en el diseño y análisis de algoritmos.

                En resumen, la asignatura de Diseño y Análisis de Algoritmos es esencial para cualquier estudiante que desee convertirse en un profesional en Ciencias de la Computación o Ingeniería en Sistemas Computacionales. Les brinda las habilidades y herramientas necesarias para diseñar y analizar algoritmos eficientes, lo que es esencial en la resolución de problemas en una amplia variedad de campos, incluyendo la inteligencia artificial, el aprendizaje automático, la ciencia de datos, entre otros.""",
                12.99f, new ArrayList<>());

        tag2.getPacks().add(pack2);
        tag1.getPacks().add(pack2);
        tag4.getPacks().add(pack2);
        pack2.getTags().add(tag2);
        pack2.getTags().add(tag1);
        pack2.getTags().add(tag4);

        // Saving the pack2 with its tags
        packRepository.save(pack2);
        tagsRepository.saveAll(Arrays.asList(tag1,tag2,tag4));


        // Pack3 CREATION
        Pack pack3 = new Pack("ED", "img/Subject-Icons/ED.png", "Estructuras de Datos", "Estructuras de Datos es una asignatura que se imparte en carreras relacionadas con la informática y busca enseñar a los estudiantes a diseñar y utilizar estructuras de datos eficientes.", """
                Estructuras de Datos es una asignatura fundamental en la formación de cualquier profesional de la informática. Esta asignatura se enfoca en el estudio de las diferentes estructuras de datos y algoritmos que se pueden utilizar para resolver problemas de programación de manera eficiente. A lo largo del curso, los estudiantes aprenden a analizar, diseñar e implementar estructuras de datos y algoritmos, así como también a evaluar su eficiencia y complejidad.

                Entre los temas que se cubren en esta asignatura se encuentran: listas, pilas, colas, árboles, grafos, algoritmos de búsqueda y ordenamiento, entre otros. Los estudiantes aprenden a implementar estas estructuras de datos y algoritmos en diferentes lenguajes de programación, y a analizar su eficiencia en términos de tiempo y espacio.

                Además, Estructuras de Datos también suele incluir prácticas de laboratorio en las que los estudiantes aplican los conceptos aprendidos en la teoría a través de la resolución de problemas reales. Estas prácticas suelen requerir el uso de herramientas específicas, como compiladores, depuradores y herramientas de visualización de datos.

                En resumen, Estructuras de Datos es una asignatura fundamental para cualquier profesional de la informática, ya que proporciona las herramientas y técnicas necesarias para diseñar y desarrollar sistemas informáticos eficientes y escalables.""",
                9.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag3.getPacks().add(pack3);
        tag4.getPacks().add(pack3);
        pack3.getTags().add(tag3);
        pack3.getTags().add(tag4);

        // Saving the pack3 with its tags
        packRepository.save(pack3);
        tagsRepository.saveAll(Arrays.asList(tag3,tag4));


        // Pack4 CREATION
        Pack pack4 = new Pack("POO", "img/Subject-Icons/ED.png", "Programación Orientada a Objetos", "Es una materia fundamental en la cual se aprenden los principios y técnicas necesarias para desarrollar programas mediante la utilización de objetos y clases.", """
                La asignatura de Programación Orientada a Objetos (POO) se enfoca en enseñar a los estudiantes de informática y ciencias de la computación los principios fundamentales de la programación orientada a objetos. Así como las técnicas necesarias para desarrollar software utilizando esta metodología. La POO es una paradigma de programación que se basa en la idea de que un programa informático puede ser diseñado como un conjunto de objetos que interactúan entre sí para llevar a cabo tareas específicas.

                Durante el desarrollo de esta asignatura, los estudiantes adquieren conocimientos acerca de los conceptos básicos de la POO, tales como la encapsulación, la herencia, el polimorfismo y la abstracción. A través de la enseñanza de estos conceptos, se pretende que los estudiantes puedan diseñar y desarrollar sistemas informáticos complejos de manera eficiente y mantenible, utilizando técnicas como la modularidad y la reutilización de código.""",
                13.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag1.getPacks().add(pack4);
        pack4.getTags().add(tag1);

        // Saving the pack4 with its tags
        packRepository.save(pack4);
        tagsRepository.save(tag1);

        // Pack5 CREATION
        Pack pack5 = new Pack("ISI", "img/Subject-Icons/ED.png", "Ingeniería de Sistemas de Información", "¡Tenemos el pack perfecto para ti! Con un conjunto de recursos que te ayudará a comprender los conceptos clave y a dominar las técnicas necesarias para obtener una excelente calificación.", """
                Con nuestro pack, tendrás acceso a:

                Una selección de ejercicios y problemas resueltos para que puedas practicar y poner a prueba tus habilidades.
                Resúmenes de los temas más importantes, organizados de manera clara y concisa para que puedas estudiar de manera efectiva.
                Una guía de estudio detallada que te llevará paso a paso por todos los temas que debes dominar para el examen.""",
                13.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag1.getPacks().add(pack5);
        tag2.getPacks().add(pack5);
        pack5.getTags().add(tag1);
        pack5.getTags().add(tag2);

        // Saving the pack4 with its tags
        packRepository.save(pack5);
        tagsRepository.save(tag1);
        tagsRepository.save(tag2);


        // Pack6 CREATION
        Pack pack6 = new Pack("LDM", "img/Subject-Icons/ED.png", "Laboratorios de Dispositivos Móviles", "Domina el mundo móvil con nuestro pack de laboratorios de dispositivos móviles: todo lo que necesitas para aprender y experimentar con los últimos avances en tecnología móvil","¿Quieres convertirte en un experto en dispositivos móviles y aplicaciones? Con nuestro pack de laboratorios de dispositivos móviles, podrás aprender y experimentar con las últimas tecnologías móviles. Obtendrás acceso a una amplia variedad de laboratorios, desde la creación de aplicaciones móviles hasta la resolución de problemas y la implementación de soluciones. Además, contarás con el apoyo de nuestros tutores expertos, quienes estarán disponibles para ayudarte a resolver tus dudas y asistirte en todo lo que necesites. Con nuestro pack, estarás listo para enfrentar los desafíos del mundo móvil y llevar tus habilidades al siguiente nivel. ¡Compra ahora y comienza a explorar el mundo móvil de manera completa!",
                13.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag1.getPacks().add(pack6);
        tag2.getPacks().add(pack6);
        pack6.getTags().add(tag1);
        pack6.getTags().add(tag2);

        // Saving the pack4 with its tags
        packRepository.save(pack6);
        tagsRepository.save(tag1);
        tagsRepository.save(tag2);


        // Pack7 CREATION
        Pack pack7 = new Pack("AIC", "img/Subject-Icons/ED.png", "Arquitectura de Computadores", "Quítate una de las peores asignaturas de la carrera con nuestro completo pack de arquitectura e ingeniería de computadores","Nuestro pack de arquitectura e ingeniería de computadores es la herramienta que necesitas para desarrollar soluciones innovadoras y de alto rendimiento. Con una selección de recursos que incluyen ejercicios prácticos, resúmenes y guías de estudio detalladas, podrás aprender los conceptos fundamentales y las técnicas avanzadas necesarias para destacarte en el campo de la informática.",
                13.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag1.getPacks().add(pack7);
        tag2.getPacks().add(pack7);
        pack7.getTags().add(tag1);
        pack7.getTags().add(tag2);

        // Saving the pack4 with its tags
        packRepository.save(pack7);
        tagsRepository.save(tag1);
        tagsRepository.save(tag2);

        // Pack8 CREATION
        Pack pack8 = new Pack("MOEG", "img/Subject-Icons/ED.png", "Métodos Operativos y Estadísticos de Gestión", "","",
                13.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag1.getPacks().add(pack8);
        tag2.getPacks().add(pack8);
        pack8.getTags().add(tag1);
        pack8.getTags().add(tag2);

        // Saving the pack4 with its tags
        packRepository.save(pack8);
        tagsRepository.save(tag1);
        tagsRepository.save(tag2);


        // Pack9 CREATION
        Pack pack9 = new Pack("IC", "img/Subject-Icons/ED.png", "Ingeniería del Conocimiento", "Optimiza tus habilidades en la gestión empresarial con nuestro pack de Métodos operativos y estadísticos de gestión, que te brinda las herramientas necesarias para tomar decisiones inteligentes y basadas en datos","Nuestro pack de Métodos operativos y estadísticos de gestión te proporcionará las herramientas necesarias para optimizar tus habilidades en la gestión empresarial. Aprenderás a tomar decisiones inteligentes y basadas en datos, utilizando técnicas estadísticas y operativas avanzadas para analizar la información disponible y mejorar la eficiencia de los procesos empresariales. Con nuestra selección de recursos, incluyendo ejercicios prácticos y resúmenes de los temas más importantes, podrás estudiar de manera efectiva y asegurarte de dominar los conceptos fundamentales",
                13.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag1.getPacks().add(pack9);
        tag2.getPacks().add(pack9);
        pack9.getTags().add(tag1);
        pack9.getTags().add(tag2);

        // Saving the pack4 with its tags
        packRepository.save(pack9);
        tagsRepository.save(tag1);
        tagsRepository.save(tag2);


        // Pack10 CREATION
        Pack pack10 = new Pack("MULT", "img/Subject-Icons/ED.png", "Multimedia", "Desata tu creatividad con nuestro pack multimedia, que incluye todo lo que necesitas para crear contenido audiovisual de alta calidad, desde edición de video hasta producción musical y diseño gráfico","Nuestro pack multimedia es la herramienta perfecta para desatar tu creatividad y crear contenido audiovisual de alta calidad. Incluye una amplia selección de recursos que abarcan desde edición de video hasta producción musical y diseño gráfico, para que puedas crear contenido atractivo y de impacto visual. Con nuestra guía de estudio detallada y tutoriales en línea, podrás aprender nuevas habilidades y técnicas de manera efectiva, y contarás con el apoyo de nuestros expertos en todo momento. ¡Compra ahora y lleva tus habilidades creativas al siguiente nivel con nuestro pack multimedia!",
                13.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag1.getPacks().add(pack10);
        tag2.getPacks().add(pack10);
        pack10.getTags().add(tag1);
        pack10.getTags().add(tag2);

        // Saving the pack4 with its tags
        packRepository.save(pack10);
        tagsRepository.save(tag1);
        tagsRepository.save(tag2);

        // Pack11 CREATION
        Pack pack11 = new Pack("AIR", "img/Subject-Icons/ED.png", "Análisis e Ingeniería de Requisitos", "Domina las técnicas de análisis e ingeniería de requisitos con nuestro pack completo de recursos, que incluye ejercicios prácticos y guías de estudio detalladas"," Aprende cómo identificar, analizar y documentar los requisitos para cualquier proyecto de software, y adquiere las habilidades necesarias para desarrollar soluciones de software de alta calidad y que cumplan con las necesidades de los usuarios finales.\"",
                13.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag1.getPacks().add(pack11);
        tag2.getPacks().add(pack11);
        pack11.getTags().add(tag1);
        pack11.getTags().add(tag2);

        // Saving the pack4 with its tags
        packRepository.save(pack11);
        tagsRepository.save(tag1);
        tagsRepository.save(tag2);


        // Pack12 CREATION
        Pack pack12 = new Pack("IP", "img/Subject-Icons/ED.png", "Introducción a la Programación", "Aprende a programar desde cero con nuestro pack de introducción a la programación, que te brinda los conceptos fundamentales y las habilidades necesarias para empezar a crear tus propias aplicaciones. ¡Compra ahora y comienza a construir el futuro!","Aprende a programar desde cero con nuestro pack de introducción a la programación, que te brinda los conceptos fundamentales y las habilidades necesarias para empezar a crear tus propias aplicaciones. ¡Compra ahora y comienza a construir el futuro!",
                13.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag1.getPacks().add(pack12);
        tag2.getPacks().add(pack12);
        pack12.getTags().add(tag1);
        pack12.getTags().add(tag2);

        // Saving the pack4 with its tags
        packRepository.save(pack12);
        tagsRepository.save(tag1);
        tagsRepository.save(tag2);


        //********************************************************************



        // Basic Form CREATION
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy : HH:mm");
        String formattedDate = now.format(formatter);

        Forms forms = new Forms("¿Cómo hago relaciones Many to Many en Spring Boot?", "He estado mirando por encima y no consigo entender cómo puedo hacer una relacion de ese estilo, tampoco veo buenos ejemplos, help!",
                """
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sagittis nibh nec sodales lobortis. Integer tristique odio enim, eu placerat urna feugiat eget. Mauris vitae enim commodo, pulvinar justo et, faucibus est. Pellentesque molestie tempor malesuada. Sed quis urna iaculis, sagittis nibh a, commodo ante. Aliquam a lacus et nulla facilisis blandit. Sed lacinia nibh quis ligula rutrum varius. Vestibulum quis consequat tortor, nec feugiat lacus. Proin eleifend pharetra risus ut rutrum. Quisque ut ultricies augue.

                        Ut aliquam pulvinar metus. Ut a elementum nisi. Curabitur a nibh condimentum, venenatis metus ac, lobortis leo. In congue enim sed accumsan mollis. Suspendisse facilisis diam eu lectus tempor blandit. Vestibulum tempus turpis augue. Sed a arcu cursus, pulvinar mi sit amet, bibendum ipsum. Mauris tempus eleifend maximus. Suspendisse imperdiet at sapien non condimentum. Morbi commodo sodales erat.""", formattedDate,"Patron970", 0, "/static/img/Forms-Icons/code.svg");

        formsRepository.save(forms);


        // Admin user CREATION
        User admin = new User("admin123","admin@hotmail.com", passwordEncoder.encode("123"), "USER", "ADMIN");
        // Create a File object from the image file path
        File file = new File("src/main/resources/static/img/Profile-Pics/profile_img.png");

        // Read the image file into a byte array
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        byte[] imageBytes = outputStream.toByteArray();

        admin.setImage(imageBytes);

        // Save the user object to the database using the userRepository object
        userRepository.save(admin);
    }

}
