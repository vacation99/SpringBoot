package com.example.daniil.SpringBootTestApp;

import com.example.daniil.SpringBootTestApp.Models.Currency;
import com.example.daniil.SpringBootTestApp.Repo.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class Request {
    @Autowired
    CurrencyRepo currencyRepo;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            String request = restTemplate.getForObject(
                    "http://www.cbr.ru/scripts/XML_daily_eng.asp", String.class);
            parse(request);
        };
    }

    private ArrayList<String> arrayList = new ArrayList<>();

    private void parse(String url) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new ByteArrayInputStream(url.getBytes()));

        NodeList valutesElement = document.getElementsByTagName("ValCurs");

        Node foundedElement = valutesElement.item(0);
        //System.out.println("Элемент был найден");
        if (foundedElement.hasChildNodes()) {
            arrayList.add("ID00001");
            arrayList.add("001");
            arrayList.add("RUB");
            arrayList.add("1");
            arrayList.add("Russian ruble");
            arrayList.add("1");
            addCurrency(arrayList);
            arrayList.clear();
            printInfoAboutChildNodes(foundedElement.getChildNodes());
        }
    }

    private void printInfoAboutChildNodes(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {

            Node node = list.item(i);

            if (node.getNodeType() == Node.TEXT_NODE) {

                String textInformation = node.getNodeValue().replace("\n", "").trim();

                if (!textInformation.isEmpty()) {
//                    System.out.println("Внутри элемента найден текст: " + node.getNodeValue());
                    arrayList.add(node.getNodeValue());
                }
            }
            else {
//                System.out.println("Найден элемент: " + node.getNodeName() + ", его атрибуты: ");
                NamedNodeMap attributes = node.getAttributes();
                for (int k = 0; k < attributes.getLength(); k++) {
//                    System.out.println("Имя артрибута: " + attributes.item(k).getNodeName() + ", его значение: " + attributes.item(k).getNodeValue());
                    arrayList.add(attributes.item(k).getNodeValue());
                }
            }

            if (arrayList.size() == 6) {
                addCurrency(arrayList);
                arrayList.clear();
            }

            if (node.hasChildNodes())
                printInfoAboutChildNodes(node.getChildNodes());
        }
    }

    private void addCurrency(ArrayList<String> arrayList) {
        Currency currency = new Currency(arrayList.get(0), arrayList.get(1), arrayList.get(2), arrayList.get(3), arrayList.get(4), arrayList.get(5));
        currencyRepo.save(currency);
    }
}