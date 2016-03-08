package fr.pds.isintheair.phonintheair;

/*@RunWith(PowerMockRunner.class)
@PrepareForTest({MessageController.class, SharedPreferencesHelper.class})
public class CallWebSocketHandlerTest {
    @Test
    public void testRegisterCalledOnOpen() {
        PowerMockito.mockStatic(MessageController.class);
        PowerMockito.mockStatic(SharedPreferencesHelper.class);

        PowerMockito.when(SharedPreferencesHelper.readInteger(anyString(), anyInt())).thenReturn(42);

        CallWebSocketHandler callWebSocketHandler = new CallWebSocketHandler();
        callWebSocketHandler.onOpen();

        PowerMockito.verifyStatic();
        MessageController.sendRegisterMessage(anyInt());
    }
} */
