package ru.job4j.ood.ocp.withocp.report;

public interface ReportGenerator<T> {
    String generate(T t);
}
