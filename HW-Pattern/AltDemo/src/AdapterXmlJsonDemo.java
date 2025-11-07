public class AdapterXmlJsonDemo {
    public static void main(String[] args) {
        XmlDataService xmlService = new XmlDataService();           // источник данных в XML
        JsonDataClient adapter = new XmlToJsonAdapter(xmlService);  // адаптер
        JsonClient client = new JsonClient(adapter);                // клиент

        client.displayData();
    }
}
