package ru.job4j.ood.ocp.noocp.report;

import ru.job4j.ood.ocp.noocp.model.Client;

public class ConsoleReport extends Report {

    @Override
    String generate(Client client) {
        return client.toString();
    }

}
