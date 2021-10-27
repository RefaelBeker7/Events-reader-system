package com.refael.events_reader_system.service;

import com.refael.events_reader_system.ReadFileFromResourcesUsingGetResource;
import com.refael.events_reader_system.model.Product;
import com.refael.events_reader_system.model.ProductEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class XMLService {

    ReadFileFromResourcesUsingGetResource instance
            = new ReadFileFromResourcesUsingGetResource();

    private final Logger logger = LoggerFactory.getLogger(XMLService.class);
    public ProductEventService productEventService;
    public ProductService productService;

    public ProductEvent parseXmlFile() {

        ProductEvent productEvent = null;

        try {
            // end point that returns XML response
            // String URL = "http://www";
            File inputFile = instance.getResourceFile("xml-file/menora.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);

            logger.debug("Start normalize xml file");
            // normalize XML response
            doc.getDocumentElement().normalize();
            String sourceCompany = doc.getElementsByTagName("SourceCompany").item(0).getTextContent();
            NodeList eventsList = doc.getElementsByTagName("Event");

            for (int i = 0; i < eventsList.getLength(); i++) {
                Node node = eventsList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;

                    productEvent = new ProductEvent(
                            elem.getElementsByTagName("Id").item(0).getTextContent(),
                            elem.getElementsByTagName("sourceCompany").item(0).getTextContent(),
                            elem.getElementsByTagName("Type").item(0).getTextContent(),
                            Long.parseLong(elem.getElementsByTagName("InsuredId").item(0).getTextContent()));
                    NodeList productsList = doc.getElementsByTagName("Event");
                    for (int j = 0; j < productsList.getLength(); j++) {
                        Product product = new Product(
                                elem.getElementsByTagName("Type").item(0).getTextContent(),
                                Double.parseDouble(elem.getElementsByTagName("Price").item(0).getTextContent()),
                                convertStringToDate(elem.getElementsByTagName("StartDate").item(0).getTextContent()),
                                convertStringToDate(elem.getElementsByTagName("EndDate").item(0).getTextContent())
                        );
                        productService.addProduct(product);
                        logger.debug("Add product: {}", product);
                    }
                    productEventService.addProductEvent(productEvent);
                    logger.debug("Add Event: {}", productEvent);
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return productEvent;
    }

    private static Date convertStringToDate(String startDateString) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(startDateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
