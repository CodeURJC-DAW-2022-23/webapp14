package com.webApp14.UniHub.service;

import com.webApp14.UniHub.model.*;
import com.webApp14.UniHub.repository.*;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Service
public class DatabaseInitializer {

    @Autowired
    private FormsRepository formsRepository;
    @Autowired
    private PackRepository packRepository;
    @Autowired
    private TagsRepository tagsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ThreadPicsRepository threadPicsRepository;

    @PostConstruct
    private void init() throws IOException{

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
        Pack pack1 = new Pack("DAW", "img/Subject-Icons/ED.png", "Diseno de Aplicaciones Web", "Diseno y desarrollo de aplicaciones web modernas, utilizando lenguajes como HTML, CSS y JavaScript para crear aplicaciones tocando temas de arquitectura web, seguridad y bases de datos",
                " La asignatura de Diseno de Aplicaciones Web es una materia que forma parte de la carrera de ingenieria en informatica o sistemas, y tiene como objetivo ensenar a los estudiantes a disenar y desarrollar aplicaciones web modernas y eficientes. Durante el curso, los estudiantes aprenden sobre los diferentes lenguajes, herramientas y tecnologias que se utilizan en el desarrollo de aplicaciones web, incluyendo HTML, CSS, JavaScript, frameworks de desarrollo como Angular, React o Vue.js, y lenguajes de programacion del lado del servidor como Java, PHP o Python. La asignatura cubre tambien temas como arquitectura web, diseno de interfaces de usuario, bases de datos, seguridad en aplicaciones web, optimizacion de rendimiento, integracion con servicios web y APIs, entre otros. En resumen, la asignatura de diseno de aplicaciones web es fundamental para cualquier estudiante que quiera convertirse en un desarrollador web profesional, ya que les proporciona los conocimientos y habilidades necesarios para crear aplicaciones web de alta calidad y con un buen desempeno, cumpliendo con los estandares actuales de la industria.",
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
        Pack pack2 = new Pack("DAA", "img/Subject-Icons/ED.png","Diseno y Analisis de Algoritmos", "Diseno y Analisis de Algoritmos ensena a disenar algoritmos eficientes para resolver problemas computacionales. Esencial para estudiantes de Ciencias de la Computacion o Ingenieria en Sistemas Computacionales" ,
                "La asignatura de Diseno y Analisis de Algoritmos es una materia fundamental en la carrera de Ciencias de la Computacion o Ingenieria en Sistemas Computacionales. Esta asignatura se enfoca en ensenar a los estudiantes como disenar y analizar algoritmos eficientes para resolver problemas computacionales. Durante el curso, los estudiantes aprenden tecnicas para disenar algoritmos, incluyendo estrategias de dividir y conquistar, programacion dinamica, algoritmos voraces, entre otras. Tambien aprenden como analizar la eficiencia de los algoritmos, incluyendo la notacion O-grande y el analisis amortizado. Ademas, los estudiantes aprenden a aplicar estas tecnicas de diseno y analisis de algoritmos a una variedad de problemas, incluyendo problemas de ordenamiento, busqueda, grafos, redes y optimizacion. Otras areas de estudio en la asignatura pueden incluir el estudio de algoritmos de aproximacion y algoritmos probabilisticos, asi como el uso de herramientas computacionales para ayudar en el diseno y analisis de algoritmos. En resumen, la asignatura de Diseno y Analisis de Algoritmos es esencial para cualquier estudiante que desee convertirse en un profesional en Ciencias de la Computacion o Ingenieria en Sistemas Computacionales. Les brinda las habilidades y herramientas necesarias para disenar y analizar algoritmos eficientes, lo que es esencial en la resolucion de problemas en una amplia variedad de campos, incluyendo la inteligencia artificial, el aprendizaje automatico, la ciencia de datos, entre otros.",
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
        Pack pack3 = new Pack("ED", "img/Subject-Icons/ED.png", "Estructuras de Datos", "Estructuras de Datos es una asignatura que se imparte en carreras relacionadas con la informatica y busca ensenar a los estudiantes a disenar y utilizar estructuras de datos eficientes.", "Estructuras de Datos es una asignatura fundamental en la formacion de cualquier profesional de la informatica. Esta asignatura se enfoca en el estudio de las diferentes estructuras de datos y algoritmos que se pueden utilizar para resolver problemas de programacion de manera eficiente. A lo largo del curso, los estudiantes aprenden a analizar, disenar e implementar estructuras de datos y algoritmos, asi como tambien a evaluar su eficiencia y complejidad.Entre los temas que se cubren en esta asignatura se encuentran: listas, pilas, colas, arboles, grafos, algoritmos de busqueda y ordenamiento, entre otros. Los estudiantes aprenden a implementar estas estructuras de datos y algoritmos en diferentes lenguajes de programacion, y a analizar su eficiencia en terminos de tiempo y espacio.Ademas, Estructuras de Datos tambien suele incluir practicas de laboratorio en las que los estudiantes aplican los conceptos aprendidos en la teoria a traves de la resolucion de problemas reales. Estas practicas suelen requerir el uso de herramientas especificas, como compiladores, depuradores y herramientas de visualizacion de datos.En resumen, Estructuras de Datos es una asignatura fundamental para cualquier profesional de la informatica, ya que proporciona las herramientas y tecnicas necesarias para disenar y desarrollar sistemas informaticos eficientes y escalables.",
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
        Pack pack4 = new Pack("POO", "img/Subject-Icons/ED.png", "Programacion Orientada a Objetos", "Es una materia fundamental en la cual se aprenden los principios y tecnicas necesarias para desarrollar programas mediante la utilizacion de objetos y clases.",
                "La asignatura de Programacion Orientada a Objetos (POO) se enfoca en ensenar a los estudiantes de informatica y ciencias de la computacion los principios fundamentales de la programacion orientada a objetos. Asi como las tecnicas necesarias para desarrollar software utilizando esta metodologia. La POO es una paradigma de programacion que se basa en la idea de que un programa informatico puede ser disenado como un conjunto de objetos que interactuan entre si para llevar a cabo tareas especificas. Durante el desarrollo de esta asignatura, los estudiantes adquieren conocimientos acerca de los conceptos basicos de la POO, tales como la encapsulacion, la herencia, el polimorfismo y la abstraccion. A traves de la ensenanza de estos conceptos, se pretende que los estudiantes puedan disenar y desarrollar sistemas informaticos complejos de manera eficiente y mantenible, utilizando tecnicas como la modularidad y la reutilizacion de codigo.",
                13.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag1.getPacks().add(pack4);
        pack4.getTags().add(tag1);

        // Saving the pack4 with its tags
        packRepository.save(pack4);
        tagsRepository.save(tag1);

        // Pack5 CREATION
        Pack pack5 = new Pack("ISI", "img/Subject-Icons/ED.png", "Ingenieria de Sistemas de Informacion", "¡Tenemos el pack perfecto para ti! Con un conjunto de recursos que te ayudara a comprender los conceptos clave y a dominar las tecnicas necesarias para obtener una excelente calificacion.",
                "Con nuestro pack, tendras acceso a: Una seleccion de ejercicios y problemas resueltos para que puedas practicar y poner a prueba tus habilidades. Resumenes de los temas mas importantes, organizados de manera clara y concisa para que puedas estudiar de manera efectiva. Una guia de estudio detallada que te llevara paso a paso por todos los temas que debes dominar para el examen.",
                13.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag3.getPacks().add(pack5);
        tag2.getPacks().add(pack5);
        pack5.getTags().add(tag3);
        pack5.getTags().add(tag2);

        // Saving the pack4 with its tags
        packRepository.save(pack5);
        tagsRepository.save(tag3);
        tagsRepository.save(tag2);


        // Pack6 CREATION
        Pack pack6 = new Pack("LDM", "img/Subject-Icons/ED.png", "Laboratorios de Dispositivos Moviles", "Domina el mundo movil con nuestro pack de laboratorios de dispositivos moviles: todo lo que necesitas para aprender y experimentar con los ultimos avances en tecnologia movil","Si quieres convertirte en un experto en dispositivos moviles y aplicaciones? Con nuestro pack de laboratorios de dispositivos moviles, podras aprender y experimentar con las ultimas tecnologias moviles. Obtendras acceso a una amplia variedad de laboratorios, desde la creacion de aplicaciones moviles hasta la resolucion de problemas y la implementacion de soluciones. Ademas, contaras con el apoyo de nuestros tutores expertos, quienes estaran disponibles para ayudarte a resolver tus dudas y asistirte en todo lo que necesites. Con nuestro pack, estaras listo para enfrentar los desafios del mundo movil y llevar tus habilidades al siguiente nivel. Compra ahora y comienza a explorar el mundo movil de manera completa!",
                13.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag1.getPacks().add(pack6);
        tag4.getPacks().add(pack6);
        pack6.getTags().add(tag1);
        pack6.getTags().add(tag4);

        // Saving the pack4 with its tags
        packRepository.save(pack6);
        tagsRepository.save(tag1);
        tagsRepository.save(tag4);


        // Pack7 CREATION
        Pack pack7 = new Pack("AIC", "img/Subject-Icons/ED.png", "Arquitectura de Computadores", "Quitate una de las peores asignaturas de la carrera con nuestro completo pack de arquitectura e ingenieria de computadores","Nuestro pack de arquitectura e ingenieria de computadores es la herramienta que necesitas para desarrollar soluciones innovadoras y de alto rendimiento. Con una seleccion de recursos que incluyen ejercicios practicos, resumenes y guias de estudio detalladas, podras aprender los conceptos fundamentales y las tecnicas avanzadas necesarias para destacarte en el campo de la informatica.",
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
        Pack pack8 = new Pack("MOEG", "img/Subject-Icons/ED.png", "Metodos Operativos y Estadisticos de Gestion", "Pack completo de materiales para la asignatura Metodos operativos y estadisticos de gestion, que incluye ejercicios practicos, resumenes y ejemplos explicativos para aprobar con exito","Domina los metodos operativos y estadisticos para mejorar la gestion empresarial con nuestro completo pack de materiales. Incluye apuntes detallados, ejercicios resueltos y examenes de anos anteriores para que puedas prepararte a fondo. Asegura tu exito en la asignatura con nuestro pack!”",
                13.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag3.getPacks().add(pack8);
        tag4.getPacks().add(pack8);
        pack8.getTags().add(tag3);
        pack8.getTags().add(tag4);

        // Saving the pack4 with its tags
        packRepository.save(pack8);
        tagsRepository.save(tag3);
        tagsRepository.save(tag4);


        // Pack9 CREATION
        Pack pack9 = new Pack("IC", "img/Subject-Icons/ED.png", "Ingenieria del Conocimiento", "Optimiza tus habilidades en la gestion empresarial con nuestro pack de Metodos operativos y estadisticos de gestion, que te brinda las herramientas necesarias para tomar decisiones inteligentes y basadas en datos","Nuestro pack de Metodos operativos y estadisticos de gestion te proporcionara las herramientas necesarias para optimizar tus habilidades en la gestion empresarial. Aprenderas a tomar decisiones inteligentes y basadas en datos, utilizando tecnicas estadisticas y operativas avanzadas para analizar la informacion disponible y mejorar la eficiencia de los procesos empresariales. Con nuestra seleccion de recursos, incluyendo ejercicios practicos y resumenes de los temas mas importantes, podras estudiar de manera efectiva y asegurarte de dominar los conceptos fundamentales",
                13.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag1.getPacks().add(pack9);
        tag4.getPacks().add(pack9);
        pack9.getTags().add(tag1);
        pack9.getTags().add(tag4);

        // Saving the pack4 with its tags
        packRepository.save(pack9);
        tagsRepository.save(tag1);
        tagsRepository.save(tag4);


        // Pack10 CREATION
        Pack pack10 = new Pack("MULT", "img/Subject-Icons/ED.png", "Multimedia", "Desata tu creatividad con nuestro pack multimedia, que incluye todo lo que necesitas para crear contenido audiovisual de alta calidad, desde edicion de video hasta produccion musical y diseno grafico","Nuestro pack multimedia es la herramienta perfecta para desatar tu creatividad y crear contenido audiovisual de alta calidad. Incluye una amplia seleccion de recursos que abarcan desde edicion de video hasta produccion musical y diseno grafico, para que puedas crear contenido atractivo y de impacto visual. Con nuestra guia de estudio detallada y tutoriales en linea, podras aprender nuevas habilidades y tecnicas de manera efectiva, y contaras con el apoyo de nuestros expertos en todo momento. Compra ahora y lleva tus habilidades creativas al siguiente nivel con nuestro pack multimedia!",
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
        Pack pack11 = new Pack("AIR", "img/Subject-Icons/ED.png", "Analisis e Ingenieria de Requisitos", "Domina las tecnicas de analisis e ingenieria de requisitos con nuestro pack completo de recursos, que incluye ejercicios practicos y guias de estudio detalladas"," Aprende como identificar, analizar y documentar los requisitos para cualquier proyecto de software, y adquiere las habilidades necesarias para desarrollar soluciones de software de alta calidad y que cumplan con las necesidades de los usuarios finales.\"",
                13.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag2.getPacks().add(pack11);
        tag1.getPacks().add(pack11);
        pack11.getTags().add(tag2);
        pack11.getTags().add(tag1);

        // Saving the pack4 with its tags
        packRepository.save(pack11);
        tagsRepository.save(tag2);
        tagsRepository.save(tag1);


        // Pack12 CREATION
        Pack pack12 = new Pack("IP", "img/Subject-Icons/ED.png", "Introduccion a la Programacion", "Aprende a programar desde cero con nuestro pack de introduccion a la programacion, que te brinda los conceptos fundamentales y las habilidades necesarias para empezar a crear tus propias aplicaciones.","Aprende a programar desde cero con nuestro pack de introduccion a la programacion, que te brinda los conceptos fundamentales y las habilidades necesarias para empezar a crear tus propias aplicaciones.",
                13.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag3.getPacks().add(pack12);
        tag1.getPacks().add(pack12);
        pack12.getTags().add(tag3);
        pack12.getTags().add(tag1);

        // Saving the pack4 with its tags
        packRepository.save(pack12);
        tagsRepository.save(tag1);
        tagsRepository.save(tag2);


        //********************************************************************



        // Basic Form CREATION
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy : HH:mm");
        String formattedDate = now.format(formatter);

        Forms forms = new Forms("¿Como hago relaciones Many to Many en Spring Boot?", "He estado mirando por encima y no consigo entender como puedo hacer una relacion de ese estilo, tampoco veo buenos ejemplos, help!", """
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
