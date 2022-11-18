package ru.job4j.ood.ocp.noocp.report;

import ru.job4j.ood.ocp.noocp.model.Client;

public class ReportUsage {
    private final ConsoleReport consoleReport;
    private final CsvReport csvReport;
    private final HtmlReport htmlReport;
    private final JsonReport jsonReport;

    public ReportUsage(ConsoleReport consoleReport,
                       CsvReport csvReport,
                       HtmlReport htmlReport,
                       JsonReport jsonReport) {
        this.consoleReport = consoleReport;
        this.csvReport = csvReport;
        this.htmlReport = htmlReport;
        this.jsonReport = jsonReport;
    }

    public String getConsoleReport(Client client) {
        return consoleReport.generate(client);
    }

    public String getCsvReport(Client client) {
        return csvReport.generate(client);
    }

    public String getHtmlReport(Client client) {
        return htmlReport.generate(client);
    }

    public String getJsonReport(Client client) {
        return jsonReport.generate(client);
    }

    public static void main(String[] args) {
        ReportUsage reportUsage = new ReportUsage(
                new ConsoleReport(), new CsvReport(), new HtmlReport(), new JsonReport());
        Client ivan = new Client("Ivan", "Ivanov", true);
        System.out.println(reportUsage.getConsoleReport(ivan));
        System.out.println(reportUsage.getHtmlReport(ivan));
        System.out.println(reportUsage.getCsvReport(ivan));
        System.out.println(reportUsage.getJsonReport(ivan));
    }

}