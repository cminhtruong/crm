package fr.pds.isintheair.jdatour.uc.phone.call.receive.controller;


import fr.pds.isintheair.jdatour.uc.phone.call.receive.server.NotifierEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.logging.Logger;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MessageController.class, Logger.class})
public class NotifierEndpointTest {
    @Test
    public void testMessageIsHandledWhenReceiving() throws Exception {
        PowerMockito.mockStatic(MessageController.class);
        PowerMockito.mockStatic(Logger.class);

        Logger logger = mock(Logger.class);
        doNothing().when(logger).info(anyString());
        PowerMockito.when(Logger.getLogger(anyString())).thenReturn(logger);


        NotifierEndpoint notifierEndpoint = new NotifierEndpoint();
        notifierEndpoint.onMessage(anyString(), anyObject());

        PowerMockito.verifyStatic();
        MessageController.handleMessage(anyObject(), anyObject());
    }
}
