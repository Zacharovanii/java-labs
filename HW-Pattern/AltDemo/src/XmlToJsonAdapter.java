// Адаптер: преобразует XML в JSON
class XmlToJsonAdapter implements JsonDataClient {
    private XmlDataService xmlService;

    public XmlToJsonAdapter(XmlDataService xmlService) {
        this.xmlService = xmlService;
    }

    @Override
    public String getDataAsJson() {
        String xml = xmlService.getDataAsXml();

        // преобразование XML в JSON (для примера)
        String json = xml
                .replace("<user>", "{")
                .replace("</user>", "}")
                .replace("<name>", "\"name\":\"")
                .replace("</name>", "\",")
                .replace("<age>", "\"age\":")
                .replace("</age>", "");
        return json;
    }

}
