package ComputerScience.DesignPattern;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    public void update();
}

interface Subject {
    public void register(Observer obj);
    public void unregister(Observer obj);
    public void notifyObservers();
    public String getUpdate(Observer obj);
}

class Topic implements Subject {
    private List<Observer> observers;
    private String message;

    public Topic() {
        this.observers = new ArrayList<>();
        this.message = "";
    }

    @Override
    public void register(Observer obj) {
        if (!observers.contains(obj)) {
            observers.add(obj);
        }
    }

    @Override
    public void unregister(Observer obj) {
        if (observers.contains(obj)) {
            observers.remove(obj);
        }
    }

    @Override
    public void notifyObservers() {
        this.observers.forEach(Observer::update);
    }

    @Override
    public String getUpdate(Observer obj) {
        return this.message;
    }

    public void postMessage(String msg) {
        System.out.println("Message sent to Topic: " + msg);
        this.message = msg;
        notifyObservers();
    }
}

class TopicSubscriber implements Observer {
    private String name;
    private Subject topic;

    public TopicSubscriber(String name, Subject topic) {
        this.name = name;
        this.topic = topic;
    }

    @Override
    public void update() {
        String msg = topic.getUpdate(this);
        System.out.println(name + ":: got message >> " + msg);
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        Topic topic = new Topic();
        Observer o1 = new TopicSubscriber("A", topic);
        Observer o2 = new TopicSubscriber("B", topic);
        Observer o3 = new TopicSubscriber("C", topic);

        topic.register(o1); topic.register(o2); topic.register(o3);

        topic.postMessage("amumu is op champion!");

    }
}
