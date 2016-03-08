package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment;

/**
 * Created by tlacouque on 20/01/2016.
 */

/* public class DetailHCFragmentTest extends ActivityInstrumentationTestCase2<CRUDCustomerActivity> {

    CRUDCustomerActivity activity;
    HealthCenter healthCenter;
    PurchasingCentral purchasingCentral;
    Holding holding;

    public DetailHCFragmentTest(Class<CRUDCustomerActivity> activityClass) {
        super(CRUDCustomerActivity.class);
    }

    public DetailHCFragmentTest() {
        super(CRUDCustomerActivity.class);
    }


    @Override
    public void setUp() throws Exception {
        activity = getActivity();
        initDb();
        initHC();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailHCFragment.KEY_HC_ARGS, healthCenter);
        final DetailHCFragment detailHCFragment = new DetailHCFragment();
        detailHCFragment.setArguments(bundle);
        activity.runOnUiThread(new Runnable() {
            public void run() {
                ((AppCompatActivity) activity).getSupportActionBar()
                        .setTitle(R.string.display_hc_fragment_title_action_bar);
                ((AppCompatActivity) activity).getFragmentManager().beginTransaction().addToBackStack("detailHc")
                        .replace(R.id.create_customer_fragment_container, detailHCFragment).commit();
            }
        });
        getInstrumentation().waitForIdleSync();
    }



    public void testInitView() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.detail_hc_fragment_name);
        assertEquals("testName", textView.getText().toString());
        TextView siret = (TextView) activity.findViewById(R.id.detail_hc_fragment_siret_number);
        assertNotNull(siret);
        assertEquals(26770008600056L, Long.parseLong(siret.getText().toString()));
        TextView finess = (TextView) activity.findViewById(R.id.detail_hc_fragment_finess_number);
        assertEquals(234735732L,Long.parseLong(finess.getText().toString()));
        TextView adress = (TextView) activity.findViewById(R.id.detail_hc_fragment_adress);
        assertEquals("1 rue de chalautre ,Provins",adress.getText().toString());
        TextView zipCode = (TextView) activity.findViewById(R.id.detail_hc_fragment_zip_code);
        assertEquals(77160L,Integer.decode(zipCode.getText().toString()).longValue());
        TextView bedNumber = (TextView) activity.findViewById(R.id.detail_hc_fragment_bed_number);
        assertEquals(200L,Integer.decode(bedNumber.getText().toString()).longValue());
        TextView webSite = (TextView) activity.findViewById(R.id.detail_hc_fragment_web_site);
        assertEquals("www.test.com",webSite.getText().toString());
    }

    public void testInitMap() throws Exception {
        MapView mapView = (MapView) activity.findViewById(R.id.detail_hc_fragment_map);
        assertNotNull(mapView);
    }

    public void initDb() {
        purchasingCentral = new PurchasingCentral();
        purchasingCentral.setId(0);
        purchasingCentral.setName("test");
        purchasingCentral.save();


        holding = new Holding();
        holding.setId(0);
        holding.setName("test");
        holding.save();
    }

    public void cleanDb() {
        purchasingCentral.delete();
        holding.delete();
    }

    private void initHC() {
        final String nameTest = "testName";
        final long siretNumberTest = 26770008600056L;
        final long finessNumberTest = 234735732L;
        final int streetNumberTest = 1;
        final String streetNameTest = "rue de chalautre";
        final String townNameTest = "Provins";
        final int zipCodeTest = 77160;
        final int bedNumberTest = 200;
        final String webSiteNameTest = "www.test.com";
        healthCenter = new HealthCenter();
        healthCenter.setName(nameTest);
        healthCenter.setSiretNumber(siretNumberTest);
        healthCenter.setFinessNumber(finessNumberTest);
        healthCenter.setStreetNumber(streetNumberTest);
        healthCenter.setStreetName(streetNameTest);
        healthCenter.setTown(townNameTest);
        healthCenter.setZipCode(zipCodeTest);
        healthCenter.setBedNumber(bedNumberTest);
        healthCenter.setWebSite(webSiteNameTest);
        healthCenter.setPurchasingCentralId(0);
        healthCenter.setHoldingId(0);
    }

    @Override
    public void tearDown() throws Exception {
        activity.finish();
        cleanDb();
    }
} */
