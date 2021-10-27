package com.refael.events_reader_system;

import com.refael.events_reader_system.model.ProductEvent;
import com.refael.events_reader_system.repository.ProductEventRepository;
import com.refael.events_reader_system.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@SpringBootTest
class EventsReaderSystemApplicationTests {

    @Autowired
    private ProductEventRepository productEventRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {

    }

    @Test
    void testFindAll() throws Exception {
        productEventRepository.findAll();
        productRepository.findAll();
    }

    @Test
    void loadXmlFile() throws IOException {
        File file = ResourceUtils.getFile("classpath:xml-file/menora.xml");

        //File is found
        System.out.println("File Found : " + file.exists());

        //Read File Content
        String content = new String(Files.readAllBytes(file.toPath()));
        System.out.println(content);
    }

}
