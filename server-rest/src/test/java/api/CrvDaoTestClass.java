package api;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/*@RunWith(MockitoJUnitRunner.class)
 public class CrvDaoTestClass {
    private CrvMorphiaDao CrvDaoObject;
	private Report report;

	@Before
	public void initDao(){
		System.out.println("Launching tests...");
		CrvDaoObject = new CrvMorphiaDao();
		report = new Report();
	}
	
	@Test
	public void testDaoUrlConfiguration(){
		assertEquals("localhost", Config.URL);

	}
	@Test
	public void testDaoPortConfiguration(){
		assertEquals(27017, Config.PORT);

	}
	
	@Test
	public void testDaoConnection(){
		assertEquals(true, CrvDaoObject.ConnectDB());

	}
	

	
	@Test
	public void testDaoCreate(){
		//create mock
		//CrvDaoObject = new CrvMorphiaDao();
		
		  
		
		report.setId("1");
		report.setClient("1");
		report.setComment("test comment");
		report.setCommercial("0778801708");
		report.setContact("1");
		report.setDate("02/01/2015");
		report.setSatisfaction("Oui");
		report.setVisit("1");
		
		
		assertEquals(true, CrvDaoObject.createOrModifyCrv(report));

	}
	@Test
	public void testDaoCreateError(){
		
		//testing report object without any values 
		//the method should return false which means no bugs and no insertion in DB
		assertFalse(CrvDaoObject.createOrModifyCrv(report)== false);
	}
	
	@Test
	public void testDaoCreateWithSameId(){
		//create mock
		//CrvDaoObject = new CrvMorphiaDao();
		
		  
		
		report.setId("1");
		report.setClient("1");
		report.setComment("test comment");
		report.setCommercial("0778801708");
		report.setContact("1");
		report.setDate("02/01/2015");
		report.setSatisfaction("Oui");
		report.setVisit("1");
		
		
		assertTrue(CrvDaoObject.createOrModifyCrv(report)==true);
		
		//same report id but with different comment value
		report.setComment("test comment");
		//should always return true without bugs
		assertTrue(CrvDaoObject.createOrModifyCrv(report)==true);
		
		//same report id but with another different comment value
		report.setComment("test comment");
		//should always return true without bugs
		assertTrue(CrvDaoObject.createOrModifyCrv(report)==true);

	}
} */
