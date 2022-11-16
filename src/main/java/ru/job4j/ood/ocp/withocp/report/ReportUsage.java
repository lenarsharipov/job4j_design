package ru.job4j.ood.ocp.withocp.report;

import ru.job4j.ood.ocp.withocp.model.Client;


public class ReportUsage {
    private final ReportGenerator<Client> report;

    public ReportUsage(ReportGenerator<Client> report) {
        this.report = report;
    }

    public String generate(Client client) {
        return report.generate(client);
    }

    public static void main(String[] args) {
        Client client = new Client("Ivan", "Ivanov", true);
        ReportUsage html = new ReportUsage(new HtmlReport());
        System.out.println(html.generate(client));

        ReportUsage console = new ReportUsage(new ConsoleReport());
        System.out.println(console.generate(client));

        ReportUsage csv = new ReportUsage(new CsvReport());
        System.out.println(csv.generate(client));

        ReportUsage json = new ReportUsage(new JsonReport());
        System.out.println(json.generate(client));

    }


}
