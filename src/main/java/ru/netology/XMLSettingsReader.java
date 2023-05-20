package ru.netology;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLSettingsReader {
    protected boolean isLoad;
    protected String loadFile;
    protected String loadFormat;

    protected boolean isSave;
    protected String saveFile;
    protected String saveFormat;

    protected boolean isLog;
    protected String logFile;

    public XMLSettingsReader(File xmlFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        Element root = doc.getDocumentElement();
        Element loadSetting = (Element) root.getElementsByTagName("load").item(0);
        Element saveSetting = (Element) root.getElementsByTagName("save").item(0);
        Element logSetting = (Element) root.getElementsByTagName("log").item(0);

        isLoad = Boolean.parseBoolean(loadSetting.getElementsByTagName("enabled").item(0).getTextContent());
        loadFile = loadSetting.getElementsByTagName("loadFormat").item(0).getTextContent();
        loadFormat = loadSetting.getElementsByTagName("format").item(0).getTextContent();

        isSave = Boolean.parseBoolean(saveSetting.getElementsByTagName("enabled").item(0).getTextContent());
        saveFile = saveSetting.getElementsByTagName("loadFormat").item(0).getTextContent();
        saveFormat = saveSetting.getElementsByTagName("format").item(0).getTextContent();

        isLog = Boolean.parseBoolean(logSetting.getElementsByTagName("enabled").item(0).getTextContent());
        logFile = logSetting.getElementsByTagName("loadFormat").item(0).getTextContent();


    }
}
