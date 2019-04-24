package com.weather.service;

import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class WeatherController {

    private final WeatherAssembler assembler;

    WeatherController(WeatherAssembler assembler) {
        this.assembler = assembler;
    }

    @CrossOrigin(origins = "http://localhost:9090")
    @GetMapping("/weather/{location}")
    public Resource<WeatherItem> weather(@PathVariable String location) {
        String currTemp = getCurrentTemperature(location);
        String tomorrowTemp = getTomorrowsTemperature(location);

        WeatherItem item = new WeatherItem(location, Double.valueOf(currTemp), Double.valueOf(tomorrowTemp));
        return assembler.toResource(item);
    }

    private String getCurrentTemperature(String location) {
        // Get current time and round down about one hour, just to be sure
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        now = now.minusHours(1);
        int lastTenMinutes = (int) (Math.floor(now.getMinute()/10.0) * 10);
        now = now.withMinute(lastTenMinutes == 60 ? 0 : lastTenMinutes).withSecond(0).truncatedTo(ChronoUnit.SECONDS);
        final String time = now.format(ISO_LOCAL_DATE_TIME);

        // Prepare parameters
        Map<String, String> params = new HashMap<String, String>();
        params.put("location", location);
        params.put("time", time);

        final String uri = "http://opendata.fmi.fi/wfs?service=WFS&version=2.0.0&request=getFeature&storedquery_id=fmi::observations::weather::timevaluepair&place={location}&parameters=t2m&starttime={time}";

        return fetchTemperature(uri, params);
    }

    private String getTomorrowsTemperature(String location) {
        // Get tomorrow's time
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        now = now.plusDays(1).withMinute(0).withSecond(0).truncatedTo(ChronoUnit.SECONDS);
        final String time = now.format(ISO_LOCAL_DATE_TIME);

        // Prepare parameters
        Map<String, String> params = new HashMap<String, String>();
        params.put("location", location);
        params.put("time", time);

        final String uri = "http://opendata.fmi.fi/wfs?service=WFS&version=2.0.0&request=getFeature&storedquery_id=fmi::forecast::harmonie::hybrid::point::timevaluepair&place={location}&parameters=Temperature&starttime={time}&endtime={time}";

        return fetchTemperature(uri, params);
    }

    private String fetchTemperature(String uri, Map<String, String> params) {
        RestTemplate restTemplate = new RestTemplate();
        //WeatherResultItem result = restTemplate.getForObject(uri, WeatherResultItem.class, params);
        String result = restTemplate.getForObject(uri, String.class, params);
        String temperature = "";

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            final InputSource is = new InputSource(new StringReader(result));
            is.setEncoding("UTF-8");
            Document document = builder.parse(is);

            // OMG.....
            NodeList nodes = document.getElementsByTagNameNS("*", "MeasurementTVP");
            temperature = nodes.item(nodes.getLength()-1).getChildNodes().item(3).getTextContent();
            //System.out.println(temperature);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temperature;
    }
}
