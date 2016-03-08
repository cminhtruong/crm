package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment;

/**
 * Created by tlacouque on 23/01/2016.
 */
/* public class DetailIndepFragmentTest extends ActivityInstrumentationTestCase2<CRUDCustomerActivity> {


    CRUDCustomerActivity activity;
    Independant independant;
    Specialty specialty;
    Company company;

    public DetailIndepFragmentTest(Class<CRUDCustomerActivity> activityClass) {
        super(CRUDCustomerActivity.class);
    }

    public DetailIndepFragmentTest() {
        super(CRUDCustomerActivity.class);
    }


    @Override
    public void setUp() throws Exception {
        activity = getActivity();
        initDb();
        initIndep();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailIndepFragment.KEY_INDEP_ARGS, independant);
        final DetailIndepFragment detailIndepFragment = new DetailIndepFragment();
        detailIndepFragment.setArguments(bundle);
        activity.runOnUiThread(new Runnable() {
            public void run() {
                ((AppCompatActivity) activity).getFragmentManager().beginTransaction().addToBackStack("detailIndep")
                        .replace(R.id.create_customer_fragment_container, detailIndepFragment).commit();
            }
        });
        getInstrumentation().waitForIdleSync();
    }



    public void testInitDetailIndepView() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.detail_indep_fragment_name);
        assertEquals("testName", textView.getText().toString());
        TextView siret = (TextView) activity.findViewById(R.id.detail_indep_fragment_siret_number);
        assertNotNull(siret);
        assertEquals(26770008600056L, Long.parseLong(siret.getText().toString()));
        TextView finess = (TextView) activity.findViewById(R.id.detail_indep_fragment_finess_number);
        assertEquals(234735732L, Long.parseLong(finess.getText().toString()));
        TextView adress = (TextView) activity.findViewById(R.id.detail_indep_fragment_adress);
        assertEquals("1 rue de chalautre ,Provins", adress.getText().toString());
        TextView zipCode = (TextView) activity.findViewById(R.id.detail_indep_fragment_zip_code);
        assertEquals(77160L, Integer.decode(zipCode.getText().toString()).longValue());
        TextView webSite = (TextView) activity.findViewById(R.id.detail_indep_fragment_web_site);
        assertEquals("www.test.com", webSite.getText().toString());
    }


    public void testInitMap() throws Exception {
        MapView mapView = (MapView) activity.findViewById(R.id.detail_indep_fragment_map);
        assertNotNull(mapView);
    }



    private void initIndep() {
        final String nameTest = "testName";
        final long siretNumberTest = 26770008600056L;
        final long finessNumberTest = 234735732L;
        final int streetNumberTest = 1;
        final String streetNameTest = "rue de chalautre";
        final String townNameTest = "Provins";
        final int zipCodeTest = 77160;
        final String webSiteNameTest = "www.test.com";
        independant = new Independant();
        independant.setName(nameTest);
        independant.setSiretNumber(siretNumberTest);
        independant.setFinessNumber(finessNumberTest);
        independant.setStreetNumber(streetNumberTest);
        independant.setStreetName(streetNameTest);
        independant.setTown(townNameTest);
        independant.setZipCode(zipCodeTest);
        independant.setWebSite(webSiteNameTest);
        independant.setCompanyId(0);
        independant.setSpecialtyId(0);
    }

    public void initDb() {
        specialty = new Specialty();
        specialty.setId(0);
        specialty.setName("test");
        specialty.save();


        company = new Company();
        company.setId(0);
        company.setName("test");
        company.save();
    }

    public void cleanDb() {
        specialty.delete();
        company.delete();
    }

    @Override
    public void tearDown() throws Exception {
        activity.finish();
        cleanDb();
    }

} */

