package ru.job4j.ood.ocp.withocp.report;

import ru.job4j.ood.ocp.withocp.model.Client;

public class CsvReport implements ReportGenerator<Client> {
    private final String delimiter = ";";

    @Override
    public String generate(Client client) {
        return new StringBuilder("Name").append(delimiter)
                .append("Surname").append(delimiter)
                .append("Active")
                .append(System.lineSeparator())
                .append(client.getName())
                .append(delimiter)
                .append(client.getSurname())
                .append(delimiter)
                .append(client.isActive())
                .append(delimiter)
                .toString();
    }
}
