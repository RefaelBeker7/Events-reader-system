package com.refael.events_reader_system.controller;

import com.refael.events_reader_system.model.ProductEvent;
import com.refael.events_reader_system.service.XMLService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/xml-file")
public class XmlController {

    public XMLService xmlService;

    @GetMapping(value = "/read")
    public ResponseEntity<ProductEvent> readXmlFile() {
        ProductEvent productEvent = xmlService.parseXmlFile();
        return new ResponseEntity<>(productEvent, HttpStatus.OK);
    }

    @GetMapping(value = "/fileExist")
    public ResponseEntity fileXmlExist() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:xml-file/menora.xml");

        return (file.exists()) ?
                new ResponseEntity<>("File exist", HttpStatus.OK) :
                new ResponseEntity<>("File not exist", HttpStatus.OK);
    }

}
