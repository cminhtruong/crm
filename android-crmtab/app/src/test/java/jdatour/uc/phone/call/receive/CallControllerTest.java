package jdatour.uc.phone.call.receive;

import org.junit.Test;
import org.mockito.Mockito;

import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.CallController;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.Call;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.Message;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.MessageMeta;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.enumeration.MessageType;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.websocket.WebSocketConnectionHandlerSingleton;


/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/24/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

/* public class CallControllerTest {
    @Test
    public void testCallSendsMessage() {
        WebSocketConnectionHandlerSingleton webSocketConnectionHandlerSingleton = Mockito.spy(WebSocketConnectionHandlerSingleton.getInstance());

        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.REGISTER_PHONE).build();
        Call        call        = new Call("0610772364");
        Message     message     = new Message.MessageBuilder().addMessageMeta(messageMeta).addCall(call).build();

        WebSocketConnectionHandlerSingleton.getInstance().connect();
        CallController.call("0610772364");
        Mockito.verify(webSocketConnectionHandlerSingleton, Mockito.times(1)).sendMessage(message);
    }
} */
