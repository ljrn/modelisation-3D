package projet.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilisation du pattern observer/observe
 */
public abstract class Subject {
    protected List<Observer> attached;
    /*
     * Constructeur de la classe Subject
     */
    public Subject() {
        attached = new ArrayList<>();
    }
    /**
     * Attache un observer au Sujet courant
     * @param obs Observer à attacher
     */
    public void attach(Observer obs) {
        if (! attached.contains( obs)) {
            attached.add(obs);
        }
    }
    /**
     * Détache un observer au Sujet courant
     * @param obs Observer à détacher
     */
    public void detach(Observer obs) {
        if (attached.contains( obs)) {
            attached.remove(obs);
        }
    }
    /**
     * Notifie les Observers d'une modification
     */
    public void notifyObservers() {
        for (Observer o : attached) {
            o.update(this);
        }
    }

    /**
     * Notifie les Observers d'une modification
     * @param data Données pouvant influer sur la mise à jour de l'observer
     */
    public void notifyObservers(Object data) {
        for (Observer o : attached) {
            o.update(this, data);
        }
    }

}
