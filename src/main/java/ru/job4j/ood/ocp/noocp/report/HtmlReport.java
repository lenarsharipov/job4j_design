package ru.job4j.ood.ocp.noocp.report;

import org.apache.juneau.html.HtmlSerializer;
import ru.job4j.ood.ocp.noocp.model.Client;


public class HtmlReport extends Report {

    @Override
    public String generate(Client client) {
        String html = "";
        try {
            html = HtmlSerializer.DEFAULT.serialize(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return html;
    }
}
