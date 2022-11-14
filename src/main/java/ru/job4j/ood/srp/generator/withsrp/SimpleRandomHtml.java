package ru.job4j.ood.srp.generator.withsrp;

import org.apache.juneau.html.HtmlSerializer;
import org.apache.juneau.html.HtmlSerializerBuilder;
import ru.job4j.ood.srp.generator.model.Client;

public class SimpleRandomHtml implements HtmlFormatGenerator {
    @Override
    public String generator(Client client) {
        String html = "";
        try {
            HtmlSerializer serializer = new HtmlSerializerBuilder().ws().build();
            html = serializer.serialize(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return html;
    }
}
