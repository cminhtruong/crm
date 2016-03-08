package fr.pds.isintheair.jdatour.uc.phone.call.receive.controller;

import fr.pds.isintheair.jdatour.uc.phone.call.receive.entity.Message;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.entity.MessageMeta;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.entity.Register;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.enumeration.DeviceType;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.enumeration.MessageType;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.peer.PeerHandlerSingleton;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.websocket.Session;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class MessageControllerTest {
    Session phoneSession = Mockito.mock(Session.class);
    Session tabletSession = Mockito.mock(Session.class);

    PeerHandlerSingleton peerHandlerSingleton;

    @Before
    public void setUp() {
    }

    @Test
    public void testMessageRegisterPhoneAddPeer() {
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.REGISTER_PHONE).build();
        Register register = new Register(42);
        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).addRegister(register).build();

        MessageController.handleMessage(message, phoneSession);

        assertNotNull(PeerHandlerSingleton.getInstance().findPeerSession(DeviceType.PHONE, 42));
        assertEquals((int) PeerHandlerSingleton.getInstance().findPeerUserId(DeviceType.PHONE, phoneSession), 42);
    }

    @Test
    public void testMessageRegisterTabletAddPeer() {
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.REGISTER_TABLET).build();
        Register register = new Register(42);
        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).addRegister(register).build();

        MessageController.handleMessage(message, tabletSession);

        assertNotNull(PeerHandlerSingleton.getInstance().findPeerSession(DeviceType.TABLET, 42));
        assertEquals((int) PeerHandlerSingleton.getInstance().findPeerUserId(DeviceType.TABLET, tabletSession), 42);
    }
}
