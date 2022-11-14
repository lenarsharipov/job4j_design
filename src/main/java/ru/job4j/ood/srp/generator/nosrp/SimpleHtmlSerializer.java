package ru.job4j.ood.srp.generator.nosrp;


import ru.job4j.ood.srp.generator.model.Client;
import org.apache.juneau.html.HtmlSerializer;

public class SimpleHtmlSerializer implements HtmlGenerator {

    @Override
    public String convertToHtml(Client client) {
        String html = "";
        try {
            html = HtmlSerializer.DEFAULT.serialize(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return html;
    }

    @Override
    public void print(Client client) {
        System.out.println(client);
    }

    public static void main(String[] args) {
        Client client = new Client("Ivan", "Ivanov", true);
        SimpleHtmlSerializer simpleHtmlSerializer = new SimpleHtmlSerializer();
        simpleHtmlSerializer.print(client);
        String rsl = simpleHtmlSerializer.convertToHtml(client);
        System.out.println(rsl);
    }
}
