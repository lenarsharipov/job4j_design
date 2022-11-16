package ru.job4j.ood.ocp.withocp.report;

import ru.job4j.ood.ocp.withocp.model.Client;

public class ConsoleReport implements ReportGenerator<Client> {
    @Override
    public String generate(Client client) {
        return client.toString();
    }
}
