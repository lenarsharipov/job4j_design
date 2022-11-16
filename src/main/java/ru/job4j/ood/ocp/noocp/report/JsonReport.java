package ru.job4j.ood.ocp.noocp.report;

import org.json.JSONObject;
import ru.job4j.ood.ocp.noocp.model.Client;

public class JsonReport extends Report {
    @Override
    String generate(Client client) {
        return new JSONObject(client).toString();
    }
}
