package fr.pds.isintheair.jdatour.uc.phone.call.receive.controller;

import fr.pds.isintheair.jdatour.uc.phone.call.receive.enumeration.DeviceType;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.peer.PeerHandlerSingleton;

import javax.websocket.Session;

public class RegisterController {
    public static void register(DeviceType deviceType, Integer userId, Session session) {
        PeerHandlerSingleton.getInstance().addPeer(deviceType, userId, session);
    }
}