package Midterm;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLCreater {
    public static void main(String[] args) {
        createXMLFile("‪D:/JavaOPP/Mid-Term/students.xml");
    }

    public static void createXMLFile(String filename) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Students");
            doc.appendChild(rootElement);

            String[][] studentsData = {
                    {"1", "John Doe", "123 Main Street", "2000-01-01"},
                    {"2", "Jane Smith", "456 Oak Avenue", "1998-05-15"},
                    {"3", "Michael Johnson", "789 Elm Drive", "2002-11-30"}
            };

            for (String[] studentData : studentsData) {
                Element studentElement = doc.createElement("Student");
                rootElement.appendChild(studentElement);

                Element idElement = doc.createElement("id");
                idElement.appendChild(doc.createTextNode(studentData[0]));
                studentElement.appendChild(idElement);

                Element nameElement = doc.createElement("name");
                nameElement.appendChild(doc.createTextNode(studentData[1]));
                studentElement.appendChild(nameElement);

                Element addressElement = doc.createElement("address");
                addressElement.appendChild(doc.createTextNode(studentData[2]));
                studentElement.appendChild(addressElement);

                Element dobElement = doc.createElement("dateOfBirth");
                dobElement.appendChild(doc.createTextNode(studentData[3]));
                studentElement.appendChild(dobElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);
            System.out.println("File XML đã được tạo thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
