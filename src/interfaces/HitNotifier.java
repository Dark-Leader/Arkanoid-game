package interfaces;

/**
 * represents a hit notifier - when it hits a block -> send update to all listeners.
 */
public interface HitNotifier {
    /**
     * add listener to list of listeners.
     * @param hl HitListener.
     */
    void addEventListener(HitListener hl);
    /**
     * remove listener from list of listeners.
     * @param hl HitListener.
     */
    void removeEventListener(HitListener hl);
}
