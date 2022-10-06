package ru.job4j.serialization.json;

public class Stack {
    private final String programmingLanguage;
    private final String buildTool;
    private final String webFramework;

    public Stack(String programmingLanguage, String buildTool, String webFramework) {
        this.programmingLanguage = programmingLanguage;
        this.buildTool = buildTool;
        this.webFramework = webFramework;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public String getBuildTool() {
        return buildTool;
    }

    public String getWebFramework() {
        return webFramework;
    }

    @Override
    public String toString() {
        return "Stack{"
                + "programmingLanguage='" + programmingLanguage + '\''
                + ", buildTool='" + buildTool + '\''
                + ", webFramework='" + webFramework + '\''
                + '}';
    }
}