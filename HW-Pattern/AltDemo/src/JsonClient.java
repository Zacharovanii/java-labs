// Клиент, который работает только с JSON
class JsonClient {
    private JsonDataClient dataClient;

    public JsonClient(JsonDataClient dataClient) {
        this.dataClient = dataClient;
    }

    public void displayData() {
        System.out.println("Полученные данные в JSON формате:");
        System.out.println(dataClient.getDataAsJson());
    }
}
