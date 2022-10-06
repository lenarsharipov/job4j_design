package ru.job4j.serialization.xml;

public class Stack {
    private final String pL;
    private final String buildTool;
    private final String webFramework;

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