package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int id;
    @XmlAttribute
    private boolean retired;

    private Stack stack;
    @XmlElementWrapper(name = "positions")
    @XmlElement(name = "position")
    private String[] positions;

    public Employee() {

    }

    public Employee(String name, int id, boolean retired, Stack stack, String... positions) {
        this.name = name;
        this.id = id;
        this.retired = retired;
        this.stack = stack;
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "name='" + name + '\''
                + ", id=" + id
                + ", retired=" + retired
                + ", stack=" + stack
                + ", positions=" + Arrays.toString(positions)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final Employee employee = new Employee("Tom", 1, false,
                new Stack("Java", "Maven", "Spring"),
                "Java Middle Developer", "Java Junior Developer");

        JAXBContext context = JAXBContext.newInstance(Employee.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employee, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}