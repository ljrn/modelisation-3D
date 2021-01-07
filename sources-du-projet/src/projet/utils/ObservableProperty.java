package projet.utils;


public class ObservableProperty extends Subject {

	protected Object value;
	/**
	 * @return Valeur d'un objet
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Assigne la valeur val à value et notifie les observers
	 * @param val
	 */
	public void setValue(Object val) {
		value = val;
		notifyObservers(val);
	}
}
