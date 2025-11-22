import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TemperatureController implements ActionListener, TemperatureChangeListener {
    private final TemperatureSensor model;
    private final TemperatureView view;
    private final int OVERHEAT_LIMIT = 30;
    private final int OVERCOOLED_LIMIT = 10;

    public TemperatureController(TemperatureSensor model, TemperatureView view) {
        this.model = model;
        this.view = view;

        // Подписка контроллера как слушателя событий
        model.addTemperatureChangeListener(this);

        // Обработка кнопок
        view.getIncButton().addActionListener(this);
        view.getDecButton().addActionListener(this);
        view.getResetButton().addActionListener(this);

        updateView(model.getTemperature());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == view.getIncButton()) model.setTemperature(model.getTemperature() + 5);
        if (src == view.getDecButton()) model.setTemperature(model.getTemperature() - 5);
        if (src == view.getResetButton()) model.setTemperature(20);
    }

    @Override
    public void temperatureChanged(TemperatureChangedEvent event) {
        int temp = event.getNewTemperature();
        updateView(temp);
    }

    private void updateView(int temperature) {
        boolean overheated = temperature > OVERHEAT_LIMIT;
        boolean overcooled = temperature < OVERCOOLED_LIMIT;
        view.updateTemperatureDisplay(temperature, overheated, overcooled);
    }
}
