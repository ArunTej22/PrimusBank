package DriverFactory;

import org.testng.annotations.Test;
import commonFunctions.FunctionLibrary;
import Constant.PBconstant;
import Utilities.ExcelFileUtil;

public class DriverScript extends PBconstant{
String inputpath ="F:\\AutomationTesting\\Automation_Frameworks\\TestInput\\HybridTest.xlsx";
String outputpath ="F:\\AutomationTesting\\Automation_Frameworks\\TestOutput\\HybridResults.xlsx";
String TCSheet="TestCases";
String TSSheet ="TestSteps";
@Test
public void startTest()throws Throwable
{
	boolean res= false;
	String tcres="";
	//create reference object
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of rows in TSSHeet TCSheet
	int TCCount =xl.rowCount(TCSheet);
	int TSCount = xl.rowCount(TSSheet);
	//iterate all rows in TCSheet
	for(int i=1;i<=TCCount;i++)
	{
		//store all moudule name into TCModule
		String TCModule =xl.getCellData(TCSheet, i, 1);
		//read execution mode cell
		String executionmode =xl.getCellData(TCSheet, i, 2);
		if(executionmode.equalsIgnoreCase("Y"))
		{
			//read tcid cell from TCSheet
			String tcid =xl.getCellData(TCSheet, i, 0);
			//iterate all rows in TSSHeet
			for(int j=1;j<=TSCount;j++)
			{
				//read tcid from TSShett
				String tsid =xl.getCellData(TSSheet, j, 0);
				if(tcid.equalsIgnoreCase(tsid))
				{
					//read keyword cell
					String keyword = xl.getCellData(TSSheet, j, 4);
					if(keyword.equalsIgnoreCase("AdminLogin"))
					{
						String username =xl.getCellData(TSSheet, j, 5);
						String password =xl.getCellData(TSSheet, j, 6);
					res=FunctionLibrary.verifyLogin(username, password);
					}
					else if(keyword.equalsIgnoreCase("NewBranchCreation"))
					{
						String bname =xl.getCellData(TSSheet, j, 5);
						String address1 =xl.getCellData(TSSheet, j, 6);
						String address2 =xl.getCellData(TSSheet, j, 7);
						String address3 =xl.getCellData(TSSheet, j, 8);
						String area =xl.getCellData(TSSheet, j, 9);
						String zip =xl.getCellData(TSSheet, j, 10);
						String country =xl.getCellData(TSSheet, j, 11);
						String state =xl.getCellData(TSSheet, j, 12);
						String city =xl.getCellData(TSSheet, j, 13);
						FunctionLibrary.clickBranches();
						res=FunctionLibrary.verifyNewBranch(bname, address1, address2, address3, area, zip, country, state, city);
					}
					else if(keyword.equalsIgnoreCase("UpdateBranch"))
					{
						String branchname =xl.getCellData(TSSheet, j, 5);
						String address =xl.getCellData(TSSheet, j, 6);
						String zipcode =xl.getCellData(TSSheet, j, 10);
						FunctionLibrary.clickBranches();
						res= FunctionLibrary.verifyBranchUpdate(branchname, address, zipcode);
					}
					else if(keyword.equalsIgnoreCase("AdminLogout"))
					{
						res=FunctionLibrary.verifyLogout();
					}
					//res id holding true or false
					String tsres="";
					if(res)
					{
						
						//write as pass into status cell in tSSheet
						tsres="Pass";
						xl.setCellData(TSSheet, j, 3, tsres, outputpath);
					}
					else
					{
						tsres="Fail";
						//write as Fail into status cell in tSSheet
						xl.setCellData(TSSheet, j, 3, tsres, outputpath);
					}
					tcres=tsres;
					
				}
			}
			//write tcres into TCSheet
			xl.setCellData(TCSheet, i, 3, tcres, outputpath);
		}
		else
		{
			//write as blocked into TCShet under status cell
			xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
		}
	}
	
	
}
}









