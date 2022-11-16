package ru.job4j.ood.ocp.noocp.report;

import org.apache.juneau.html.HtmlSerializer;
import org.json.JSONObject;
import ru.job4j.ood.ocp.noocp.model.Client;

public class ReportGodObject {
    private final String delimiter = ";";

    public String getConsoleReport(Client client) {
        return client.toString();
    }

    public String getCsvReport(Client client) {
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

    public String getHtmlReport(Client client) {
        String html = "";
        try {
            html = HtmlSerializer.DEFAULT.serialize(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return html;
    }

    public String getJsonReport(Client client) {
        return new JSONObject(client).toString();
    }

    public static void main(String[] args) {
        ReportGodObject reportGodObject = new ReportGodObject();
        Client client = new Client("Ivan", "Ivanov", true);
        System.out.println(reportGodObject.getConsoleReport(client));
        System.out.println(reportGodObject.getHtmlReport(client));
        System.out.println(reportGodObject.getJsonReport(client));
        System.out.println(reportGodObject.getCsvReport(client));
    }

}
