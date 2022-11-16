package ru.job4j.ood.ocp.withocp.report;

import org.json.JSONObject;
import ru.job4j.ood.ocp.withocp.model.Client;

public class JsonReport implements ReportGenerator<Client> {
    @Override
    public String generate(Client client) {
        return new JSONObject(client).toString();
    }
}
