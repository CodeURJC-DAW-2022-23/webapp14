package com.webApp14.UniHub.service;

import com.webApp14.UniHub.model.Forms;
import com.webApp14.UniHub.model.Pack;
import com.webApp14.UniHub.model.Tags;
import com.webApp14.UniHub.model.User;
import com.webApp14.UniHub.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
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

    @PostConstruct
    private void init() throws IOException, URISyntaxException{

        Tags tag1 = new Tags("Programacion","tag-programming", new ArrayList<>());
        Tags tag2 = new Tags("Logica","tag-logic", new ArrayList<>());
        Tags tag3 = new Tags("Computadores","tag-hardware", new ArrayList<>());
        Tags tag4 = new Tags("Mates", "tag-math",new ArrayList<>());

        Pack pack1 = new Pack("DAW", "img/Subject-Icons/ED.png", "Diseño de Aplicaciones Web", "Diseño y desarrollo de aplicaciones web modernas, utilizando lenguajes como HTML, CSS y JavaScript para crear aplicaciones tocando temas de arquitectura web, seguridad y bases de datos","La asignatura de Diseño de Aplicaciones Web es una materia que forma parte de la carrera de ingeniería en informática o sistemas, y tiene como objetivo enseñar a los estudiantes a diseñar y desarrollar aplicaciones web modernas y eficientes.\n" +
                "\n" +
                "Durante el curso, los estudiantes aprenden sobre los diferentes lenguajes, herramientas y tecnologías que se utilizan en el desarrollo de aplicaciones web, incluyendo HTML, CSS, JavaScript, frameworks de desarrollo como Angular, React o Vue.js, y lenguajes de programación del lado del servidor como Java, PHP o Python.\n" +
                "\n" +
                "La asignatura cubre también temas como arquitectura web, diseño de interfaces de usuario, bases de datos, seguridad en aplicaciones web, optimización de rendimiento, integración con servicios web y APIs, entre otros.\n" +
                "\n" +
                "En resumen, la asignatura de diseño de aplicaciones web es fundamental para cualquier estudiante que quiera convertirse en un desarrollador web profesional, ya que les proporciona los conocimientos y habilidades necesarios para crear aplicaciones web de alta calidad y con un buen desempeño, cumpliendo con los estándares actuales de la industria.",
                9.99f, new ArrayList<>());

        // Agregar las relaciones ManyToMany
        tag1.getPacks().add(pack1);
        tag2.getPacks().add(pack1);
        pack1.getTags().add(tag1);
        pack1.getTags().add(tag2);

        // Guardar los cambios
        packRepository.save(pack1);
        tagsRepository.saveAll(Arrays.asList(tag1,tag2));


        Pack pack2 = new Pack("DAA", "img/Subject-Icons/ED.png","Diseño y Análisis de Algoritmos", "Diseño y Análisis de Algoritmos enseña a diseñar algoritmos eficientes para resolver problemas computacionales. Esencial para estudiantes de Ciencias de la Computación o Ingeniería en Sistemas Computacionales" ,"La asignatura de Diseño y Análisis de Algoritmos es una materia fundamental en la carrera de Ciencias de la Computación o Ingeniería en Sistemas Computacionales. Esta asignatura se enfoca en enseñar a los estudiantes cómo diseñar y analizar algoritmos eficientes para resolver problemas computacionales.\n" +
                "\n" +
                "Durante el curso, los estudiantes aprenden técnicas para diseñar algoritmos, incluyendo estrategias de dividir y conquistar, programación dinámica, algoritmos voraces, entre otras. También aprenden cómo analizar la eficiencia de los algoritmos, incluyendo la notación O-grande y el análisis amortizado.\n" +
                "\n" +
                "Además, los estudiantes aprenden a aplicar estas técnicas de diseño y análisis de algoritmos a una variedad de problemas, incluyendo problemas de ordenamiento, búsqueda, grafos, redes y optimización.\n" +
                "\n" +
                "Otras áreas de estudio en la asignatura pueden incluir el estudio de algoritmos de aproximación y algoritmos probabilísticos, así como el uso de herramientas computacionales para ayudar en el diseño y análisis de algoritmos.\n" +
                "\n" +
                "En resumen, la asignatura de Diseño y Análisis de Algoritmos es esencial para cualquier estudiante que desee convertirse en un profesional en Ciencias de la Computación o Ingeniería en Sistemas Computacionales. Les brinda las habilidades y herramientas necesarias para diseñar y analizar algoritmos eficientes, lo que es esencial en la resolución de problemas en una amplia variedad de campos, incluyendo la inteligencia artificial, el aprendizaje automático, la ciencia de datos, entre otros.",
                12.99f, new ArrayList<>());

        tag2.getPacks().add(pack2);
        tag1.getPacks().add(pack2);
        tag4.getPacks().add(pack2);
        pack2.getTags().add(tag2);
        pack2.getTags().add(tag1);
        pack2.getTags().add(tag4);

        packRepository.save(pack2);
        tagsRepository.saveAll(Arrays.asList(tag1,tag2,tag4));

        LocalDate fecha = LocalDate.of(2023, 1, 1);
        Forms forms = new Forms("¿Cómo hago relaciones Many to Many en Spring Boot?", "He estado mirando por encima y no consigo entender cómo puedo hacer una relacion de ese estilo, tampoco veo buenos ejemplos, help!",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sagittis nibh nec sodales lobortis. Integer tristique odio enim, eu placerat urna feugiat eget. Mauris vitae enim commodo, pulvinar justo et, faucibus est. Pellentesque molestie tempor malesuada. Sed quis urna iaculis, sagittis nibh a, commodo ante. Aliquam a lacus et nulla facilisis blandit. Sed lacinia nibh quis ligula rutrum varius. Vestibulum quis consequat tortor, nec feugiat lacus. Proin eleifend pharetra risus ut rutrum. Quisque ut ultricies augue.\n" +
                        "\n" +
                        "Ut aliquam pulvinar metus. Ut a elementum nisi. Curabitur a nibh condimentum, venenatis metus ac, lobortis leo. In congue enim sed accumsan mollis. Suspendisse facilisis diam eu lectus tempor blandit. Vestibulum tempus turpis augue. Sed a arcu cursus, pulvinar mi sit amet, bibendum ipsum. Mauris tempus eleifend maximus. Suspendisse imperdiet at sapien non condimentum. Morbi commodo sodales erat.", fecha,"Patron970");

        formsRepository.save(forms);

        User admin = new User("admin123","admin@hotmail.com","123", true);
        userRepository.save(admin);
    }

}
