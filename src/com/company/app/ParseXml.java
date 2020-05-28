package com.company.app;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseXml {

    public List<Currency> parse() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            /* pobieranie pliku xml */
            DocumentBuilder builder = factory.newDocumentBuilder();
           Document doc = builder.parse("eurofxref-daily.xml");



            // pobieranie elementów pod tagu
            NodeList cubelist = doc.getElementsByTagName("Cube");

            // tworzenie listy walut
            List<Currency> currencies = new ArrayList<>();

            // utworznie zmiennej kontrolnej
            int cubeId = 1;

            for (int i = 0; i < cubelist.getLength(); i++) {
                Node node = cubelist.item(i); // przypisanie obiektu do Node
                if (node.getNodeType() == Node.ELEMENT_NODE) { // sprawdzenie czy typ elemetu jest poprawny
                    Element cube = (Element) node;

                    /* Pobieranie i przypisanie elementów*/
                    String currencyString = cube.getAttribute("currency");
                    String rateString = cube.getAttribute("rate");

                    // sprawdzenie czy element został przypisany
                    if (!rateString.isEmpty()) {
                        // zamiana zmiennej String na zmienna Double
                        Double rate = Double.parseDouble(rateString);

                        // tworzenie obiektu
                        Currency currency = new Currency(cubeId, currencyString, rate);

                        // dodanie obiektu do listy
                        currencies.add(currency);

                        // zwiekszenie zmiennej kontrolnej
                        cubeId++;
                    }
                }
            }
            return currencies;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

            // Utworznie wyjątku - zamknięcie programu
            System.out.print("Error! We can't find the xml file");
            System.exit(0);
        }

        return null;
    }

}
