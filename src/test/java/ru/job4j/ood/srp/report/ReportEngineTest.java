package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.formatter.XmlReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new ReportEngine(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenItReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Oleg", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker2);
        String delimiter = ";";
        Report engine = new ItReportEngine(store, parser, delimiter);
        StringBuilder expect = new StringBuilder();
        expect.append("Name").append(delimiter)
                .append("Hired").append(delimiter)
                .append("Fired").append(delimiter)
                .append("Salary").append(delimiter)
                .append(System.lineSeparator())
                .append(worker.getName()).append(delimiter)
                .append(parser.parse(worker.getHired())).append(delimiter)
                .append(parser.parse(worker.getFired())).append(delimiter)
                .append(worker.getSalary()).append(delimiter)
                .append(System.lineSeparator())
                .append(worker2.getName()).append(delimiter)
                .append(parser.parse(worker2.getHired())).append(delimiter)
                .append(parser.parse(worker2.getFired())).append(delimiter)
                .append(worker2.getSalary()).append(delimiter)
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenXmlReportGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Oleg", now, now, 200);
        Employee worker3 = new Employee("Anna", now, now, 300);
        DateTimeParser<Calendar> parser = new XmlReportDateTimeParser();
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        Report engine = new XmlReportEngine(store, JAXBContext.newInstance(Employee.Employees.class));
        StringBuilder expect = new StringBuilder();
        expect.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("<employees>")
                    .append("<employees>")
                        .append("<fired>").append(parser.parse(worker.getFired())).append("</fired>")
                        .append("<hired>").append(parser.parse(worker.getFired())).append("</hired>")
                        .append("<name>").append(worker.getName()).append("</name>")
                        .append("<salary>").append(worker.getSalary()).append("</salary>")
                    .append("</employees>")
                    .append("<employees>")
                        .append("<fired>").append(parser.parse(worker2.getFired())).append("</fired>")
                        .append("<hired>").append(parser.parse(worker2.getFired())).append("</hired>")
                        .append("<name>").append(worker2.getName()).append("</name>")
                        .append("<salary>").append(worker2.getSalary()).append("</salary>")
                    .append("</employees>")
                .append("</employees>");
        assertThat(engine.generate(em -> em.getSalary() < 300)).isEqualTo(expect.toString());
    }

    @Test
    public void whenJsonReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Oleg", now, now, 200);
        Employee worker3 = new Employee("Anna", now, now, 300);
        Employee worker4 = new Employee("Olga", now, now, 400);
        Employee worker5 = new Employee("Dmitriy", now, now, 500);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        store.add(worker4);
        store.add(worker5);
        Report engine = new JsonReportEngine(store);
        StringBuilder expect = new StringBuilder();
        expect.append("[{")
                .append("\"name\":\"").append(worker4.getName()).append("\",")
                .append("\"hired\":")
                .append("{\"year\":").append(worker4.getHired().get(Calendar.YEAR)).append(",")
                .append("\"month\":").append(worker4.getHired().get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(worker4.getHired().get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(worker4.getHired().get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":").append(worker4.getHired().get(Calendar.MINUTE)).append(",")
                .append("\"second\":").append(worker4.getHired().get(Calendar.SECOND))
                .append("},")
                .append("\"fired\":")
                .append("{\"year\":").append(worker4.getFired().get(Calendar.YEAR)).append(",")
                .append("\"month\":").append(worker4.getFired().get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(worker4.getFired().get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(worker4.getFired().get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":").append(worker4.getFired().get(Calendar.MINUTE)).append(",")
                .append("\"second\":").append(worker4.getFired().get(Calendar.SECOND))
                .append("},")
                .append("\"salary\":").append(worker4.getSalary())
                .append("},")
                .append("{")
                .append("\"name\":\"").append(worker5.getName()).append("\",")
                .append("\"hired\":")
                .append("{\"year\":").append(worker5.getHired().get(Calendar.YEAR)).append(",")
                .append("\"month\":").append(worker5.getHired().get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(worker5.getHired().get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(worker5.getHired().get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":").append(worker5.getHired().get(Calendar.MINUTE)).append(",")
                .append("\"second\":").append(worker5.getHired().get(Calendar.SECOND))
                .append("},")
                .append("\"fired\":")
                .append("{\"year\":").append(worker5.getFired().get(Calendar.YEAR)).append(",")
                .append("\"month\":").append(worker5.getFired().get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(worker5.getFired().get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(worker5.getFired().get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":").append(worker5.getFired().get(Calendar.MINUTE)).append(",")
                .append("\"second\":").append(worker5.getFired().get(Calendar.SECOND))
                .append("},")
                .append("\"salary\":").append(worker5.getSalary())
                .append("}]");
        assertThat(engine.generate(em -> em.getSalary() > 300)).isEqualTo(expect.toString());
    }

    @Test
    public void whenAccountingReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Currency target = Currency.USD;
        CurrencyConverter currencyConverter = new InMemoryCurrencyConverter();
        Report engine =
                new AccountingReportEngine(store, parser, currencyConverter, target);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(currencyConverter.convert(Currency.RUB, worker.getSalary(), target))
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenHrReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Dmitriy", now, now, 200);
        Employee worker3 = new Employee("Anna", now, now, 300);
        Employee worker4 = new Employee("Olga", now, now, 400);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        store.add(worker4);
        Comparator<Employee> comparator = (o1, o2) -> (int) (o1.getSalary() - o2.getSalary());
        Report engine = new HrReportEngine(store, comparator.reversed());
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker4.getName()).append(" ")
                .append(worker4.getSalary())
                .append(System.lineSeparator())
                .append(worker3.getName()).append(" ")
                .append(worker3.getSalary())
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

}
