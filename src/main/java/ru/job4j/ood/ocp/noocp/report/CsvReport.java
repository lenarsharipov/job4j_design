package ru.job4j.ood.ocp.noocp.report;

import ru.job4j.ood.ocp.noocp.model.Client;

public class CsvReport extends Report {

    @Override
    String generate(Client client) {
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
