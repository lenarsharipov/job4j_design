package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "stack")
public class Stack {
    @XmlAttribute
    private String pL;
    @XmlAttribute
    private String buildTool;
    @XmlAttribute
    private String webFramework;

    public Stack() {

    }

    public Stack(String pL, String buildTool, String webFramework) {
        this.pL = pL;
        this.buildTool = buildTool;
        this.webFramework = webFramework;
    }

    @Override
    public String toString() {
        return "Stack{"
                + "programmingLanguage='" + pL + '\''
                + ", buildTool='" + buildTool + '\''
                + ", webFramework='" + webFramework + '\''
                + '}';
    }
}