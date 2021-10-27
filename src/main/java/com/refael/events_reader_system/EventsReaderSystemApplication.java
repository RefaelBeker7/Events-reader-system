package com.refael.events_reader_system;

import com.refael.events_reader_system.model.Product;
import com.refael.events_reader_system.model.ProductEvent;
import com.refael.events_reader_system.repository.ProductEventRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class EventsReaderSystemApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(EventsReaderSystemApplication.class, args);
        ProductEventRepository productEventRepository = configurableApplicationContext.getBean(ProductEventRepository.class);

        Date startDate = convertStringToDate("2021-07-30");
        Date endDate = convertStringToDate("2021-09-30");

        ProductEvent productEvent = new ProductEvent("471b29e7-a323-447b-88d8-935737e9ffd4", "מבטחים", "E1", Long.parseLong("11111"));
        Product productA = new Product("AAA", Double.parseDouble("2000"), startDate, endDate);
        Product productB = new Product("BBB", Double.parseDouble("2000"), startDate, endDate);

        List<Product> products = Arrays.asList(productA, productB);
        productEvent.setProducts(products);

        productEventRepository.save(productEvent);
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