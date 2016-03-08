package fr.pds.isintheair.jdatour.uc.phone.call.receive.controller;

import fr.pds.isintheair.jdatour.uc.phone.call.receive.entity.Call;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.entity.Message;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.entity.MessageMeta;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.enumeration.DeviceType;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.enumeration.MessageType;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.helper.JSONHelper;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.peer.PeerHandlerSingleton;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class CallControllerTest {
    Session phoneSession = Mockito.mock(Session.class);
    Session tabletSession = Mockito.mock(Session.class);

    PeerHandlerSingleton peerHandlerSingleton;

    @Before
    public void setUp() {
        peerHandlerSingleton = spy(PeerHandlerSingleton.getInstance());

        peerHandlerSingleton.addPeer(DeviceType.PHONE, 42, phoneSession);
        peerHandlerSingleton.addPeer(DeviceType.TABLET, 42, tabletSession);

        when(phoneSession.getBasicRemote()).thenReturn(Mockito.mock(RemoteEndpoint.Basic.class));
        doReturn(42).when(peerHandlerSingleton).findPeerUserId(DeviceType.TABLET, tabletSession);
    }

    @Test
    public void testCallLaunched() throws Exception {
        CallController.call(tabletSession, "0610772364");
        verify(phoneSession.getBasicRemote(), times(1)).sendText(anyString());
    }

    @Test
    public void testCallLaunchedCorrectly() throws Exception {
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.CALL).build();
        Call call = new Call("0610772364");
        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).addCall(call).build();

        CallController.call(tabletSession, "0610772364");

        verify(phoneSession.getBasicRemote(), times(1)).sendText(JSONHelper.serialize(message, Message.class));
    }
}