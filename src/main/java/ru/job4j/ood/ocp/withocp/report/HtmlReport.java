package ru.job4j.ood.ocp.withocp.report;

import org.apache.juneau.html.HtmlSerializer;
import ru.job4j.ood.ocp.withocp.model.Client;

public class HtmlReport implements ReportGenerator<Client> {

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
