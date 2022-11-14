package ru.job4j.ood.srp.generator.withsrp;

import ru.job4j.ood.srp.generator.model.Client;

public class SimpleHtmlGenerator implements HtmlGenerator {
    private final HtmlFormatGenerator htmlFormatGenerator;

    public SimpleHtmlGenerator(HtmlFormatGenerator htmlFormatGenerator) {
        this.htmlFormatGenerator = htmlFormatGenerator;
    }

    @Override
    public String convertToHtml(Client client) {
        return htmlFormatGenerator.generator(client);
    }

    public static void main(String[] args) {
        Client client = new Client("Ivan", "Ivanov", false);
        Client client2 = new Client("Yuri", "Dmitriev", true);
        SimpleHtmlGenerator simpleHtmlGenerator1 = new SimpleHtmlGenerator(new SimpleDefaultHtml());
        SimpleHtmlGenerator simpleHtmlGenerator2 = new SimpleHtmlGenerator(new SimpleRandomHtml());
        String html = simpleHtmlGenerator1.convertToHtml(client);
        String html2 = simpleHtmlGenerator2.convertToHtml(client2);
        System.out.println(html);
        System.out.println(html2);
    }
}
