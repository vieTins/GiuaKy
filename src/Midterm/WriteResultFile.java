package Midterm;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteResultFile {
    public static void writeResultFile(List<Student> students, String fileName) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("Students");
            document.appendChild(rootElement);
            
            for (Student student : students) {
                Element stuElement = document.createElement("Student");
                rootElement.appendChild(stuElement);
                
                Element age = document.createElement("Age");
                age.appendChild(document.createTextNode(String.valueOf(student.getAge())));
                stuElement.appendChild(age);
                
                Element prime = document.createElement("isPrime");
                prime.appendChild(document.createTextNode(String.valueOf(student.isPrime())));
                stuElement.appendChild(prime);
                
                Element maHoa = document.createElement("Sum");
                maHoa.appendChild(document.createTextNode(student.getGiaTriMaHoa()));
                stuElement.appendChild(maHoa);
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);
            
            System.out.println("Lưu thành công " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
