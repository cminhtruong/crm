package fr.pds.isintheair.jdatour.uc.phone.call.receive.peer;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.enumeration.DeviceType;

import javax.websocket.Session;

public class PeerHandlerSingleton {
    private BiMap<Integer, Session> phonePeer;
    private BiMap<Integer, Session> tabletPeer;
    private static PeerHandlerSingleton INSTANCE = null;

    private PeerHandlerSingleton() {
        phonePeer = HashBiMap.create();
        tabletPeer = HashBiMap.create();
    }

    public static synchronized PeerHandlerSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PeerHandlerSingleton();
        }

        return INSTANCE;
    }

    public synchronized void addPeer(DeviceType deviceType, Integer userId, Session session) {
        switch (deviceType) {
            case PHONE:
                phonePeer.put(userId, session);
                break;
            case TABLET:
                tabletPeer.put(userId, session);
                break;
        }
    }

    public synchronized Session findPeerSession(DeviceType deviceType, Integer userId) {
        switch (deviceType) {
            case PHONE:
                return phonePeer.get(userId);
            case TABLET:
                return tabletPeer.get(userId);
        }

        return null;
    }

    public synchronized Integer findPeerUserId(DeviceType deviceType, Session session) {
        switch (deviceType) {
            case PHONE:
                return phonePeer.inverse().get(session);
            case TABLET:
                return tabletPeer.inverse().get(session);
        }

        return null;
    }

    public synchronized void removePeerSession(Session session) {
        if (phonePeer.inverse().containsKey(session))
            phonePeer.inverse().remove(session);
        else
            tabletPeer.inverse().remove(session);
    }
}
