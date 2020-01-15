package ru.epam.javacore.lesson_12_io_nio.lesson.a_3_xml.model.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.epam.javacore.lesson_11_generics_and_io.homework.common.solutions.utils.FileUtils;
import ru.epam.javacore.lesson_12_io_nio.lesson.a_3_xml.model.Child;
import ru.epam.javacore.lesson_12_io_nio.lesson.a_3_xml.model.Person;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DomParser {
    private File file;

    public DomParser(File file) {
        this.file = file;
    }

    public static void main(String[] args) throws Exception {
        File file = null;
        try {
            String fileName = "/ru/epam/javacore/lesson_12_io_nio/person.xml";
            file = FileUtils.createFileFromResource("aa", "PERSON_XML", fileName);
            DomParser domParser = new DomParser(file);
            Person parsed = domParser.parse();
            System.out.println();
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }

    public Person parse() throws Exception {
        Person person = new Person();

        Document doc = getDocumentFromFile();

        Node root = doc.getElementsByTagName("person").item(0);
        Node name = ((Element) root).getElementsByTagName("name").item(0);
        Node lastName = ((Element) root).getElementsByTagName("lastName").item(0);

        person.setName(name.getTextContent());
        person.setLastName(lastName.getTextContent());


        Node childrenRoot = ((Element) root).getElementsByTagName("children").item(0);
        NodeList children = childrenRoot.getChildNodes();


        for (int i = 0; i < children.getLength(); i++) {
            Node item = children.item(i);
            String tagName = item.getNodeName();

            if (tagName.equals("child")) {

                Element child = (Element) children.item(i);

                List<Child> personChildren = person.getChildren();
                if (personChildren == null) {
                    personChildren = new ArrayList<>();
                    person.setChildren(personChildren);
                }

                Child personChild = new Child();
                personChild.setHobby(child.getElementsByTagName("hobby").item(0).getTextContent());
                personChild.setName(child.getElementsByTagName("name").item(0).getTextContent());
                personChild.setSchoolBoy("y".equals(child.getAttribute("schoolBoy")));
                personChildren.add(personChild);
            }
        }
        return person;
    }

    private Document getDocumentFromFile() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        return builder.parse(file);
    }
}
