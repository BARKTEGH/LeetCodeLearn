package designPattern.Observer.observerImpl;

import designPattern.Observer.Observer;
import designPattern.Observer.Subject;

public class CurrentConditionsDisplay implements Observer {
    public CurrentConditionsDisplay(Subject weatherData) {
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println("CurrentConditionsDisplay.update: " + temp + " " + humidity + " " + pressure);
    }
}
