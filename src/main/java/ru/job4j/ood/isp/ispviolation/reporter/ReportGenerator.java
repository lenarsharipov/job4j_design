package ru.job4j.ood.isp.ispviolation.reporter;

public interface ReportGenerator<T> {
    String getHtmlFormat(T t);
    String getCsvFormat(T t);
    String getXmlFormat(T t);
    String getJsonFormat(T t);
}
