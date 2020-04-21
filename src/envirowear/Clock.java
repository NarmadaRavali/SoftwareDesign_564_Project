package src.envirowear;

import src.envirowear.thermostat.dto.Signal;

/**
 * Every components implementing this interface operates at same clock.
 */
public interface Clock {

    /**
     * Component implementing this interface does necessary work on receiving signal.
     * @param signal
     */
    Object onClockSignal(Signal signal);
}
