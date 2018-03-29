package ru.voskhod.utils;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.voskhod.dao.interfaces.PhoneDao;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.List;


public class XmlUtil {

    @Autowired
    private PhoneDao phoneDaoIml;

    public String toXML(Document document) throws TransformerException, IOException {

        try {
            OutputFormat format = new OutputFormat(document);
            format.setLineWidth(65);
            format.setIndenting(true);
            format.setIndent(2);
            Writer out = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(out, format);
            serializer.serialize(document);
            return out.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void printXml(){
        phoneDaoIml.getPhones();
        List<String[]> listPhones = phoneDaoIml.getNotes();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = builder.newDocument();
        Element root = document.createElement("root");
        document.appendChild(root);
        for (String[] phones : listPhones) {
            Element person = document.createElement(phones[5]);
            person.setAttribute(phones[6], phones[7]);
            root.appendChild(person);
            Element data = document.createElement(phones[8]);
            data.setTextContent(phones[9]);
            person.appendChild(data);
            Element phone = document.createElement(phones[3]);
            phone.setTextContent(phones[4]);
            person.appendChild(phone);
        }

        File file = new File("C:\\Users\\Public\\file.xml");
        Writer writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(toXML(document));
            writer.close();
            System.out.println("ЭКСПОРТ в xml выполнен");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

