package ru.job4j.ood.srp.generator.withsrp;

import org.apache.juneau.html.HtmlSerializer;
import ru.job4j.ood.srp.generator.model.Client;

public class SimpleDefaultHtml implements HtmlFormatGenerator {
    @Override
    public String generator(Client client) {
            String html = "";
            try {
                html = HtmlSerializer.DEFAULT.serialize(client);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return html;
        }
}
