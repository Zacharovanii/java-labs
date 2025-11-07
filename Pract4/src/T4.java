interface Exporter {
    void export(String data);
}

class JsonExporter implements Exporter {
    @Override
    public void export(String data) {
        System.out.println("{ \"data\": \"" + data + "\" }");
    }
}

class XmlExporter implements Exporter {
    @Override
    public void export(String data) {
        System.out.println("<data>" + data + "</data>");
    }
}

class ReportGenerator {
    public void generate(String reportData, Exporter exporter) {
        exporter.export(reportData);
    }
}

public class T4 {
    public static void main(String[] args) {
        ReportGenerator generator = new ReportGenerator();
        generator.generate(
                "Отчёт за месяц", new JsonExporter());
        generator.generate("Отчёт за месяц", new XmlExporter());
    }
}
