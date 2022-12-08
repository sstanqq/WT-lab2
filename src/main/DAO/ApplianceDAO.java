package main.DAO;

import main.model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ApplianceDAO {
    private static final String DATABASE_PATH = "src/resources/appliance.xml";

    /**
     * <p>This method returns the found kettles
     * </p>
     * @return list of kettles
     */
    public List<Kettle> findKettles() {
        Document document = getDocument();
        return getKettles(document.getDocumentElement().getChildNodes());
    }

    /**
     * <p>This method сreates a DOM document tree
     * </p>
     * @return DOM document tree
     */
    private Document getDocument() {
        Document document = null;
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(DATABASE_PATH);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
        }
        return document;
    }

    /**
     * <p>This method finds all kettles
     * </p>
     * @param appliances elements of the root element
     * @return list of kettles
     */
    private List<Kettle> getKettles(NodeList appliances) {
        List<Kettle> kettles = new ArrayList<>();

        for (int i = 0; i < appliances.getLength(); i++) {
            Node appliance = appliances.item(i);

            if (appliance.getNodeType() != Node.TEXT_NODE) {

                Map<String, Object> potentialKettle = getPotentialKettle(appliance.getChildNodes());

                if (potentialKettle.get("type").equals("Kettle")) {
                    int price = Integer.parseInt(potentialKettle.get("price").toString());
                    double volume = Double.parseDouble(potentialKettle.get("volume").toString());
                    int powerConsumption = Integer.parseInt(potentialKettle.get("powerConsumption").toString());

                    kettles.add(new Kettle(price, powerConsumption, volume));
                }
            }
        }
        return kettles;
    }

    private Map<String, Object> getPotentialKettle(NodeList applianceProps) {
        Map<String, Object> potentialKettle = new HashMap<>();

        for (int j = 0; j < applianceProps.getLength(); j++) {
            Node applianceProp = applianceProps.item(j);

            if (applianceProp.getNodeType() != Node.TEXT_NODE) {
                potentialKettle.put(applianceProp.getNodeName(),
                        applianceProp.getChildNodes().item(0).getTextContent());
            }
        }
        return potentialKettle;
    }

    public Appliance findCheapestAppliance() {
        Document document = getDocument();
        Node root = document.getDocumentElement();

        NodeList appliances = root.getChildNodes();
        Integer minPrice = null;
        Map<String, Object> potentialAppliance = new HashMap<>();
        Map<String, Object> potentialApplianceTemporary = new HashMap<>();

        for (int i = 0; i < appliances.getLength(); i++) {
            Node appliance = appliances.item(i);

            if (appliance.getNodeType() != Node.TEXT_NODE) {
                changePotentialApplianceTemporary(potentialApplianceTemporary, appliance.getChildNodes());

                int price = Integer.parseInt(potentialApplianceTemporary.get("price").toString());

                if (minPrice != null) {
                    if (minPrice > price) {
                        minPrice = price;
                        potentialAppliance = new HashMap<>(potentialApplianceTemporary);
                    } else {
                        potentialApplianceTemporary = new HashMap<>();
                    }
                } else {
                    minPrice = price;
                }
            }
        }

        return getCheapestAppliance(potentialAppliance);
    }

    private Appliance getCheapestAppliance(Map<String, Object> potentialAppliance) {
        String type = (String) potentialAppliance.get("type");

        switch (type) {
            case "Kettle" -> {
                int price = Integer.parseInt(potentialAppliance.get("price").toString());
                double volume = Double.parseDouble(potentialAppliance.get("volume").toString());
                int powerConsumption = Integer.parseInt(potentialAppliance.get("powerConsumption").toString());

                return new Kettle(price, powerConsumption, volume);
            }
            case "Laptop" -> {
                int price = Integer.parseInt(potentialAppliance.get("price").toString());
                double batteryCapacity = Double.parseDouble(potentialAppliance.get("batteryCapacity").toString());
                OS os = OS.valueOf(potentialAppliance.get("os").toString());
                int memoryRom = Integer.parseInt(potentialAppliance.get("memoryRom").toString());
                int systemMemory = Integer.parseInt(potentialAppliance.get("systemMemory").toString());
                double CPU = Double.parseDouble(potentialAppliance.get("CPU").toString());
                int displayInchs = Integer.parseInt(potentialAppliance.get("displayInchs").toString());

                return new Laptop(price, batteryCapacity, os, memoryRom, systemMemory, CPU, displayInchs);
            }
            case "Oven" -> {
                int price = Integer.parseInt(potentialAppliance.get("price").toString());
                int powerConsumption = Integer.parseInt(potentialAppliance.get("powerConsumption").toString());
                int weight = Integer.parseInt(potentialAppliance.get("weight").toString());
                int capacity = Integer.parseInt(potentialAppliance.get("capacity").toString());
                int depth = Integer.parseInt(potentialAppliance.get("depth").toString());
                int height = Integer.parseInt(potentialAppliance.get("height").toString());
                int width = Integer.parseInt(potentialAppliance.get("width").toString());

                return new Oven(price, powerConsumption, weight, capacity, depth, height, width);
            }
            default -> throw new RuntimeException();
        }
    }

    private void changePotentialApplianceTemporary(Map<String, Object> potentialApplianceTemporary, NodeList applianceProps) {
        for (int j = 0; j < applianceProps.getLength(); j++) {
            Node applianceProp = applianceProps.item(j);

            if (applianceProp.getNodeType() != Node.TEXT_NODE) {
                potentialApplianceTemporary.put(applianceProp.getNodeName(),
                        applianceProp.getChildNodes().item(0).getTextContent());
            }
        }
    }
}
