package com.web.currency.service;

import com.web.currency.model.Currency;
import com.web.currency.repository.CurrencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FetchService {

    @Value("${url.path}")
    private String stringUrl;

    @Value("${date.tag}")
    private String dateTagName;

    @Value("${code.tag}")
    private String codeTagName;

    @Value("${rate.tag}")
    private String rateTagName;

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> getCurrency(String currencyCode, String dateFrom, String dateTo) {

        log.info("Getting currency for " + LocalDate.now());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = createBuilder(factory);

        URL constructedUrl = constructUrlFromString(currencyCode, dateFrom, dateTo);

        Document document = createDocument(builder, constructedUrl);

        NodeList nodeList = document.getDocumentElement().getChildNodes();

        List<Currency> currencies = createCurrencyList(nodeList);
        currencyRepository.saveAll(currencies);
        return currencies;
    }

    private Document createDocument(DocumentBuilder builder, URL constructedUrl) {
        Document document;
        try {
            document = builder.parse(constructedUrl.openStream());
        } catch (SAXException | IOException e) {
            log.error("Cannot parse response from " + constructedUrl);
            throw new RuntimeException(e.getMessage());
        }
        return document;
    }

    private DocumentBuilder createBuilder(DocumentBuilderFactory factory) {
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            log.error("Cannot create builder for XML parsing");
            throw new RuntimeException(e.getMessage());
        }
        return builder;
    }

    private URL constructUrlFromString(String currencyCode, String dateFrom, String dateTo) {
        URL constructedUrl;
        String constructedString = String.format(stringUrl + "ccy=%s&dtFrom=%s&dtTo=%s", currencyCode, dateFrom, dateTo);
        try {
            constructedUrl = new URL(constructedString);
        } catch (MalformedURLException e) {
            log.error("Cannot construct url for fetching api. Passed parameters = "
                    + currencyCode + dateFrom + dateTo);
            throw new RuntimeException(e.getMessage());
        }
        return constructedUrl;
    }

    private List<Currency> createCurrencyList(NodeList nodeList) {
        List<Currency> currencies = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String date = element.getElementsByTagName(dateTagName)
                        .item(0).getChildNodes().item(0).getNodeValue();

                Element child = (Element) element.getChildNodes();

                String code = child.getElementsByTagName(codeTagName)
                        .item(1).getChildNodes().item(0).getNodeValue();

                Double rate = Double.parseDouble(child.getElementsByTagName(rateTagName)
                        .item(1).getChildNodes().item(0).getNodeValue());

                currencies.add(new Currency(LocalDate.parse(date), code, rate));
            }
        }
        return currencies;
    }
}
