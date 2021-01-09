package projet.utils;

/**
 * Utilisation du pattern observer/observe
 */
public interface Observer {
        public void update(Subject subj);
        public void update(Subject subj, Object data);
}
