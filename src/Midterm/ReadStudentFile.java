package Midterm;

import java.io.File;
import java.util.concurrent.BlockingQueue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ReadStudentFile implements Runnable {
    private String fileName;
    private BlockingQueue<Student> queue;

    public ReadStudentFile(String fileName, BlockingQueue<Student> queue) {
        this.fileName = fileName;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File " + fileName + " not found.");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("Student");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element studentElement = (Element) nodeList.item(i);
                String id = getTextContent(studentElement, "id");
                String name = getTextContent(studentElement, "name");
                String address = getTextContent(studentElement, "address");
                String dateOfBirth = getTextContent(studentElement, "dateOfBirth");

                if (!id.isEmpty() && !name.isEmpty() && !address.isEmpty() && !dateOfBirth.isEmpty()) {
                    Student student = new Student(id, name, address, dateOfBirth);
                    queue.put(student);
                }                
                else {
                    System.out.println("Incomplete student data found. Skipping.");
                }
             
            }
                 } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }
    public BlockingQueue<Student> getQueue() {
        return queue;
    }
}
