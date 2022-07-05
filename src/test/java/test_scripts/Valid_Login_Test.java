package test_scripts;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import generic.Base_Class;
import generic.FWUtility;
import page_classes.Login_Page;

public class Valid_Login_Test extends Base_Class {
	
	@Test
	public void testvalidlogin()
	{test = extent.startTest("testvalidlogin");
		String usrname = FWUtility.getdata(TEST_DATA_PATH, "ValidLogin", 1, 0);
		String pwd = FWUtility.getdata(TEST_DATA_PATH, "ValidLogin",1,1);
		Login_Page lp = new Login_Page(driver);
		lp.getusrnm(usrname);
		lp.getpwd(pwd);
		lp.clickSubmit();
		
		
	}
	 @Test
	 public void skipTest(){
		
	 test = extent.startTest("skipTest");
	 throw new SkipException("Skipping - This is not ready for testing ");
	 }
    
	@Test
	public void failtest()
	{ test = extent.startTest("failtest");
		Assert.assertTrue(false);
		Assert.assertFalse(true,"Failed");
	}

}
