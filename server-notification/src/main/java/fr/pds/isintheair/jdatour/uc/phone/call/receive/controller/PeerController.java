package fr.pds.isintheair.jdatour.uc.phone.call.receive.controller;

import fr.pds.isintheair.jdatour.uc.phone.call.receive.enumeration.DeviceType;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.peer.PeerHandlerSingleton;

import javax.websocket.Session;

public class PeerController {
    public static Session getPhoneSession(Session tabletSession) {
        PeerHandlerSingleton peerHandlerSingleton = PeerHandlerSingleton.getInstance();
        Integer userId = peerHandlerSingleton.findPeerUserId(DeviceType.TABLET, tabletSession);

        return peerHandlerSingleton.findPeerSession(DeviceType.PHONE, userId);
    }

    public static Session getTabletSession(Session phoneSession) {
        PeerHandlerSingleton peerHandlerSingleton = PeerHandlerSingleton.getInstance();
        Integer userId = peerHandlerSingleton.findPeerUserId(DeviceType.PHONE, phoneSession);

        return peerHandlerSingleton.findPeerSession(DeviceType.TABLET, userId);
    }
}
