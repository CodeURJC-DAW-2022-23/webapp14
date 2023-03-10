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
        Pack pack1 = new Pack("DAW", "img/Subject-Icons/ED.png", "Dise??o de Aplicaciones Web", "Dise??o y desarrollo de aplicaciones web modernas, utilizando lenguajes como HTML, CSS y JavaScript para crear aplicaciones tocando temas de arquitectura web, seguridad y bases de datos", """
                La asignatura de Dise??o de Aplicaciones Web es una materia que forma parte de la carrera de ingenier??a en inform??tica o sistemas, y tiene como objetivo ense??ar a los estudiantes a dise??ar y desarrollar aplicaciones web modernas y eficientes.

                Durante el curso, los estudiantes aprenden sobre los diferentes lenguajes, herramientas y tecnolog??as que se utilizan en el desarrollo de aplicaciones web, incluyendo HTML, CSS, JavaScript, frameworks de desarrollo como Angular, React o Vue.js, y lenguajes de programaci??n del lado del servidor como Java, PHP o Python.

                La asignatura cubre tambi??n temas como arquitectura web, dise??o de interfaces de usuario, bases de datos, seguridad en aplicaciones web, optimizaci??n de rendimiento, integraci??n con servicios web y APIs, entre otros.

                En resumen, la asignatura de dise??o de aplicaciones web es fundamental para cualquier estudiante que quiera convertirse en un desarrollador web profesional, ya que les proporciona los conocimientos y habilidades necesarios para crear aplicaciones web de alta calidad y con un buen desempe??o, cumpliendo con los est??ndares actuales de la industria.""",
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
        Pack pack2 = new Pack("DAA", "img/Subject-Icons/ED.png","Dise??o y An??lisis de Algoritmos", "Dise??o y An??lisis de Algoritmos ense??a a dise??ar algoritmos eficientes para resolver problemas computacionales. Esencial para estudiantes de Ciencias de la Computaci??n o Ingenier??a en Sistemas Computacionales" , """
                La asignatura de Dise??o y An??lisis de Algoritmos es una materia fundamental en la carrera de Ciencias de la Computaci??n o Ingenier??a en Sistemas Computacionales. Esta asignatura se enfoca en ense??ar a los estudiantes c??mo dise??ar y analizar algoritmos eficientes para resolver problemas computacionales.

                Durante el curso, los estudiantes aprenden t??cnicas para dise??ar algoritmos, incluyendo estrategias de dividir y conquistar, programaci??n din??mica, algoritmos voraces, entre otras. Tambi??n aprenden c??mo analizar la eficiencia de los algoritmos, incluyendo la notaci??n O-grande y el an??lisis amortizado.

                Adem??s, los estudiantes aprenden a aplicar estas t??cnicas de dise??o y an??lisis de algoritmos a una variedad de problemas, incluyendo problemas de ordenamiento, b??squeda, grafos, redes y optimizaci??n.

                Otras ??reas de estudio en la asignatura pueden incluir el estudio de algoritmos de aproximaci??n y algoritmos probabil??sticos, as?? como el uso de herramientas computacionales para ayudar en el dise??o y an??lisis de algoritmos.

                En resumen, la asignatura de Dise??o y An??lisis de Algoritmos es esencial para cualquier estudiante que desee convertirse en un profesional en Ciencias de la Computaci??n o Ingenier??a en Sistemas Computacionales. Les brinda las habilidades y herramientas necesarias para dise??ar y analizar algoritmos eficientes, lo que es esencial en la resoluci??n de problemas en una amplia variedad de campos, incluyendo la inteligencia artificial, el aprendizaje autom??tico, la ciencia de datos, entre otros.""",
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
        Pack pack3 = new Pack("ED", "img/Subject-Icons/ED.png", "Estructuras de Datos", "Estructuras de Datos es una asignatura que se imparte en carreras relacionadas con la inform??tica y busca ense??ar a los estudiantes a dise??ar y utilizar estructuras de datos eficientes.", """
                Estructuras de Datos es una asignatura fundamental en la formaci??n de cualquier profesional de la inform??tica. Esta asignatura se enfoca en el estudio de las diferentes estructuras de datos y algoritmos que se pueden utilizar para resolver problemas de programaci??n de manera eficiente. A lo largo del curso, los estudiantes aprenden a analizar, dise??ar e implementar estructuras de datos y algoritmos, as?? como tambi??n a evaluar su eficiencia y complejidad.

                Entre los temas que se cubren en esta asignatura se encuentran: listas, pilas, colas, ??rboles, grafos, algoritmos de b??squeda y ordenamiento, entre otros. Los estudiantes aprenden a implementar estas estructuras de datos y algoritmos en diferentes lenguajes de programaci??n, y a analizar su eficiencia en t??rminos de tiempo y espacio.

                Adem??s, Estructuras de Datos tambi??n suele incluir pr??cticas de laboratorio en las que los estudiantes aplican los conceptos aprendidos en la teor??a a trav??s de la resoluci??n de problemas reales. Estas pr??cticas suelen requerir el uso de herramientas espec??ficas, como compiladores, depuradores y herramientas de visualizaci??n de datos.

                En resumen, Estructuras de Datos es una asignatura fundamental para cualquier profesional de la inform??tica, ya que proporciona las herramientas y t??cnicas necesarias para dise??ar y desarrollar sistemas inform??ticos eficientes y escalables.""",
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
        Pack pack4 = new Pack("POO", "img/Subject-Icons/ED.png", "Programaci??n Orientada a Objetos", "Es una materia fundamental en la cual se aprenden los principios y t??cnicas necesarias para desarrollar programas mediante la utilizaci??n de objetos y clases.", """
                La asignatura de Programaci??n Orientada a Objetos (POO) se enfoca en ense??ar a los estudiantes de inform??tica y ciencias de la computaci??n los principios fundamentales de la programaci??n orientada a objetos. As?? como las t??cnicas necesarias para desarrollar software utilizando esta metodolog??a. La POO es una paradigma de programaci??n que se basa en la idea de que un programa inform??tico puede ser dise??ado como un conjunto de objetos que interact??an entre s?? para llevar a cabo tareas espec??ficas.

                Durante el desarrollo de esta asignatura, los estudiantes adquieren conocimientos acerca de los conceptos b??sicos de la POO, tales como la encapsulaci??n, la herencia, el polimorfismo y la abstracci??n. A trav??s de la ense??anza de estos conceptos, se pretende que los estudiantes puedan dise??ar y desarrollar sistemas inform??ticos complejos de manera eficiente y mantenible, utilizando t??cnicas como la modularidad y la reutilizaci??n de c??digo.""",
                13.99f, new ArrayList<>());

        // ManyToMany Relationships
        tag1.getPacks().add(pack4);
        pack4.getTags().add(tag1);

        // Saving the pack4 with its tags
        packRepository.save(pack4);
        tagsRepository.save(tag1);

        // Pack5 CREATION
        Pack pack5 = new Pack("ISI", "img/Subject-Icons/ED.png", "Ingenier??a de Sistemas de Informaci??n", "??Tenemos el pack perfecto para ti! Con un conjunto de recursos que te ayudar?? a comprender los conceptos clave y a dominar las t??cnicas necesarias para obtener una excelente calificaci??n.", """
                Con nuestro pack, tendr??s acceso a:

                Una selecci??n de ejercicios y problemas resueltos para que puedas practicar y poner a prueba tus habilidades.
                Res??menes de los temas m??s importantes, organizados de manera clara y concisa para que puedas estudiar de manera efectiva.
                Una gu??a de estudio detallada que te llevar?? paso a paso por todos los temas que debes dominar para el examen.""",
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
        Pack pack6 = new Pack("LDM", "img/Subject-Icons/ED.png", "Laboratorios de Dispositivos M??viles", "Domina el mundo m??vil con nuestro pack de laboratorios de dispositivos m??viles: todo lo que necesitas para aprender y experimentar con los ??ltimos avances en tecnolog??a m??vil","??Quieres convertirte en un experto en dispositivos m??viles y aplicaciones? Con nuestro pack de laboratorios de dispositivos m??viles, podr??s aprender y experimentar con las ??ltimas tecnolog??as m??viles. Obtendr??s acceso a una amplia variedad de laboratorios, desde la creaci??n de aplicaciones m??viles hasta la resoluci??n de problemas y la implementaci??n de soluciones. Adem??s, contar??s con el apoyo de nuestros tutores expertos, quienes estar??n disponibles para ayudarte a resolver tus dudas y asistirte en todo lo que necesites. Con nuestro pack, estar??s listo para enfrentar los desaf??os del mundo m??vil y llevar tus habilidades al siguiente nivel. ??Compra ahora y comienza a explorar el mundo m??vil de manera completa!",
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
        Pack pack7 = new Pack("AIC", "img/Subject-Icons/ED.png", "Arquitectura de Computadores", "Qu??tate una de las peores asignaturas de la carrera con nuestro completo pack de arquitectura e ingenier??a de computadores","Nuestro pack de arquitectura e ingenier??a de computadores es la herramienta que necesitas para desarrollar soluciones innovadoras y de alto rendimiento. Con una selecci??n de recursos que incluyen ejercicios pr??cticos, res??menes y gu??as de estudio detalladas, podr??s aprender los conceptos fundamentales y las t??cnicas avanzadas necesarias para destacarte en el campo de la inform??tica.",
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
        Pack pack8 = new Pack("MOEG", "img/Subject-Icons/ED.png", "M??todos Operativos y Estad??sticos de Gesti??n", "Pack completo de materiales para la asignatura M??todos operativos y estad??sticos de gesti??n, que incluye ejercicios pr??cticos, res??menes y ejemplos explicativos para aprobar con ??xito","Domina los m??todos operativos y estad??sticos para mejorar la gesti??n empresarial con nuestro completo pack de materiales. Incluye apuntes detallados, ejercicios resueltos y ex??menes de a??os anteriores para que puedas prepararte a fondo. ??Asegura tu ??xito en la asignatura con nuestro pack!???",
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
        Pack pack9 = new Pack("IC", "img/Subject-Icons/ED.png", "Ingenier??a del Conocimiento", "Optimiza tus habilidades en la gesti??n empresarial con nuestro pack de M??todos operativos y estad??sticos de gesti??n, que te brinda las herramientas necesarias para tomar decisiones inteligentes y basadas en datos","Nuestro pack de M??todos operativos y estad??sticos de gesti??n te proporcionar?? las herramientas necesarias para optimizar tus habilidades en la gesti??n empresarial. Aprender??s a tomar decisiones inteligentes y basadas en datos, utilizando t??cnicas estad??sticas y operativas avanzadas para analizar la informaci??n disponible y mejorar la eficiencia de los procesos empresariales. Con nuestra selecci??n de recursos, incluyendo ejercicios pr??cticos y res??menes de los temas m??s importantes, podr??s estudiar de manera efectiva y asegurarte de dominar los conceptos fundamentales",
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
        Pack pack10 = new Pack("MULT", "img/Subject-Icons/ED.png", "Multimedia", "Desata tu creatividad con nuestro pack multimedia, que incluye todo lo que necesitas para crear contenido audiovisual de alta calidad, desde edici??n de video hasta producci??n musical y dise??o gr??fico","Nuestro pack multimedia es la herramienta perfecta para desatar tu creatividad y crear contenido audiovisual de alta calidad. Incluye una amplia selecci??n de recursos que abarcan desde edici??n de video hasta producci??n musical y dise??o gr??fico, para que puedas crear contenido atractivo y de impacto visual. Con nuestra gu??a de estudio detallada y tutoriales en l??nea, podr??s aprender nuevas habilidades y t??cnicas de manera efectiva, y contar??s con el apoyo de nuestros expertos en todo momento. ??Compra ahora y lleva tus habilidades creativas al siguiente nivel con nuestro pack multimedia!",
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
        Pack pack11 = new Pack("AIR", "img/Subject-Icons/ED.png", "An??lisis e Ingenier??a de Requisitos", "Domina las t??cnicas de an??lisis e ingenier??a de requisitos con nuestro pack completo de recursos, que incluye ejercicios pr??cticos y gu??as de estudio detalladas"," Aprende c??mo identificar, analizar y documentar los requisitos para cualquier proyecto de software, y adquiere las habilidades necesarias para desarrollar soluciones de software de alta calidad y que cumplan con las necesidades de los usuarios finales.\"",
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
        Pack pack12 = new Pack("IP", "img/Subject-Icons/ED.png", "Introducci??n a la Programaci??n", "Aprende a programar desde cero con nuestro pack de introducci??n a la programaci??n, que te brinda los conceptos fundamentales y las habilidades necesarias para empezar a crear tus propias aplicaciones.","Aprende a programar desde cero con nuestro pack de introducci??n a la programaci??n, que te brinda los conceptos fundamentales y las habilidades necesarias para empezar a crear tus propias aplicaciones.",
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

        Forms forms = new Forms("??C??mo hago relaciones Many to Many en Spring Boot?", "He estado mirando por encima y no consigo entender c??mo puedo hacer una relacion de ese estilo, tampoco veo buenos ejemplos, help!",
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
