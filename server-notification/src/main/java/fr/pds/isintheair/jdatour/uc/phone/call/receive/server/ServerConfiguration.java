package fr.pds.isintheair.jdatour.uc.phone.call.receive.server;

import fr.pds.isintheair.ctruong.uc.prospect.websocket.ProspectNotifEndPoint;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by julie on 29/01/2016.
 */
public class ServerConfiguration implements ServerApplicationConfig {
    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> set) {
        return Collections.emptySet();
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> set) {
        return new HashSet<Class<?>>() {
            {
                add(NotifierEndpoint.class);
                add(ProspectNotifEndPoint.class);
            }
        };
    }
}
