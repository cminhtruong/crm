package fr.pds.isintheair.crmtab.jbide.uc.registercall;

/**
 * Created by jbide on 21/01/2016.
 */
/* public class UcRegisterCallTest extends ActivityInstrumentationTestCase2<MainActivity> {



        MainActivity activity;

        public UcRegisterCallTest(Class<MainActivity> activityClass) {
            super(activityClass);
        }

        public UcRegisterCallTest() {
            super(MainActivity.class);
        }


        @Override
        public void setUp() throws Exception {
            super.setUp();
            activity = getActivity();
        }

    public void testShowNotificationListFrag() throws Exception {
        boolean bool = false;
        activity.showNotificationListFrag();
        getInstrumentation().waitForIdleSync();
        Fragment frag = activity.getFragmentManager().findFragmentById(R.id.container);
        //activity.getFragmentManager().findFragmentByTag(MainActivity.TagAddLogFragment);

        if (frag instanceof PendingLogsFragment) {
            bool = true;
        }
        assertTrue(bool);
    }

    public void testAddLogFragment() throws Exception {
        boolean bool = false;
        Constants.getInstance().getCurrentBusInstance().post(new DisplayAddLogFragmentEvent(new CallEndedEvent(CallType.INCOMING, "", "", "")));
        getInstrumentation().waitForIdleSync();
        Fragment frag = activity.getFragmentManager().findFragmentById(R.id.container);
        //activity.getFragmentManager().findFragmentByTag(MainActivity.TagAddLogFragment);

        if (frag instanceof AddLogFragment) {
            bool = true;
        }
        assertTrue(bool);
    }

    public void testCallDetailsFragment() throws Exception {
        boolean bool = false;
        Cra cra = new Cra();
        cra.setCalltype(CallType.INCOMING.name());
        cra.setClientname("ok");
        cra.setComments("ok");
        cra.setContactname("ok");
        cra.setDate("ok");
        cra.setDuration((long) 10);
        activity.onListFragmentInteraction(cra);
        getInstrumentation().waitForIdleSync();
        Fragment frag = activity.getFragmentManager().findFragmentById(R.id.container);
        //activity.getFragmentManager().findFragmentByTag(MainActivity.TagAddLogFragment);

        if (frag instanceof CallDetailsFragment) {
            bool = true;
        }
        assertTrue(bool);

    }

    public void testFieldsInCallDetailsFragment() throws Exception {
        boolean bool = false;
        Cra cra = new Cra();
        cra.setCalltype(CallType.INCOMING.name());
        cra.setClientname("ok");
        cra.setComments("ok");
        cra.setContactname("ok");
        cra.setDate("ok");
        cra.setDuration((long) 10);
        activity.onListFragmentInteraction(cra);
        getInstrumentation().waitForIdleSync();
        assertEquals(((EditText) activity.findViewById(R.id.edittextcontactname)).getText().toString(),"ok");
        assertEquals(((EditText) activity.findViewById(R.id.edittextcalltype)).getText().toString(), "INCOMING");
        assertEquals(((EditText) activity.findViewById(R.id.edittextcomments)).getText().toString(),"ok");
        assertEquals(((EditText) activity.findViewById(R.id.edittextclientname)).getText().toString(),"ok");
        assertEquals(((EditText) activity.findViewById(R.id.edittextduration)).getText().toString(),"10");
        assertEquals(((EditText) activity.findViewById(R.id.edittextdate)).getText().toString(),"ok");

    }





} */
