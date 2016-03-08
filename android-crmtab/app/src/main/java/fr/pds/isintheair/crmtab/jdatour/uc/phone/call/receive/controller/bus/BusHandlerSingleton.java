package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class BusHandlerSingleton {
    private static BusHandlerSingleton mInstance = new BusHandlerSingleton();
    private final Bus bus;

    private BusHandlerSingleton() {
        bus = new Bus(ThreadEnforcer.ANY);
    }

    public static synchronized BusHandlerSingleton getInstance() {
        if (mInstance == null) {
            mInstance = new BusHandlerSingleton();
        }

        return mInstance;
    }

    public Bus getBus() {
        return bus;
    }
}
