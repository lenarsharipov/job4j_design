package ru.job4j.ood.srp.generator.nosrp;

import ru.job4j.ood.srp.generator.model.Client;

public interface HtmlGenerator {
    String convertToHtml(Client client);
    void print(Client client);
}
