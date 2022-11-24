package ru.job4j.ood.isp.ispviolation.reporter;

public class CsvReport implements ReportGenerator<String> {
    @Override
    public String getHtmlFormat(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getCsvFormat(String s) {
        return "s in CSV format ";
    }

    @Override
    public String getXmlFormat(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getJsonFormat(String s) {
        throw new UnsupportedOperationException();
    }
}
