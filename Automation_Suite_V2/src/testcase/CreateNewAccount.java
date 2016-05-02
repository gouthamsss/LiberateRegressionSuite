package testcase;

import operations.Operations;
import operations.TestAction;
import operations.TestData;
import operations.TestReport;

public class CreateNewAccount
{
        static TestReport report = new TestReport("CreateNewAccount", "");

        static String AccountNumber;
        static String AccountFirstName = TestData.randomString();
        static String AccountLastName = TestData.randomString();
        //String CustomerID;
        static String ApplicationForm = "fixedResidential";
        static String CustomerType = "R";
        static String ServiceType = "PEL";
        static String ServicePackage = "ETFTESTING";
        static String CustomeIDType = "NI";
        static String Company = "CYN";
        static String MarketingCategory = "990000";
        static String Region = "CAY";
        static String AccountType = "TP";
        static String BillStatusArea = "BOT";

        static boolean SelectServicePackage = true;
        static boolean CustomerIDProvided = false;

        static boolean passed;

        static String xpath;
        static String xpath_1;
        static String xpath_2;

        static TestAction ta = new TestAction(Operations.getdriver());

        public static void execute()
        {
            //LoginLogout.login("http://172.21.73.80:8081/liberate-LONI01-S10/");
            LoginLogout.login("http://172.21.73.80:8083/liberate-LONI02-S06/");

            while (true)
            {
                passed = testStep_1();
                report.takeScreenshot();
                if (!passed)
                    break;
                passed = testStep_2();
                report.takeScreenshot();
                if (!passed)
                    break;
                passed = testStep_3();
                report.takeScreenshot();
                if (!passed)
                    break;
                passed = testStep_4();
                report.takeScreenshot();
                if (!passed)
                    break;
                passed = testStep_5();
                report.takeScreenshot();
                if (!passed)
                    break;
                passed = testStep_6();
                report.takeScreenshot();
                if (!passed)
                    break;

                report.createScreenshotDocument();
                break;
            }

            ta.closeSession();
        }

        private static boolean testStep_1()
        {
            xpath = "//*[text()[contains(.,'Customer Care')]]";
            passed = ta.waitUntil(xpath, 5);
            passed = ta.clickOn(xpath);

            return passed;
        }

        private static boolean testStep_2()
        {
            xpath = "//*[text()[contains(.,'Service Provisioning')]]";
            passed = ta.waitUntil(xpath, 5);
            passed = ta.clickOn(xpath);

            return passed;
        }

        private static boolean testStep_3()
        {
            xpath = "(//*[text()[contains(.,'Application Form:')]]/./following::select)[1]";
            passed = ta.waitUntil(xpath, 5);
            ta.closeOKpopup();
            passed = ta.selectBy(xpath, ApplicationForm);

            if (SelectServicePackage)
            {
                xpath = "(//*[text()[contains(.,'Customer Type:')]]/./following::select)[1]";
                passed = ta.selectBy(xpath, CustomerType);

                xpath = "(//*[text()[contains(.,'Service Type:')]]/./following::select)[1]";
                passed = ta.selectBy(xpath, ServiceType);

                xpath = "(//*[text()[contains(.,'Service Package:')]]/./following::select)[1]";
                ta.waitFor(1000);
                //TODO Below function not yet fixed 
                passed = ta.waitUntilSelectOptionLoads(xpath, 5);
                passed = ta.selectBy(xpath, ServicePackage);
            }

            xpath = "//input[@value='Proceed >>']";
            passed = ta.waitUntil(xpath, 2);
            passed = ta.clickOn(xpath);

            xpath_1 = "(//*[text()[contains(.,'First Name:')]]/./following::input)[1]";
            xpath_2 = "(//*[text()[contains(.,'ID:')]]/./following::select)[1]";

            xpath = xpath_1 + " | " + xpath_2;
            passed = ta.waitUntil(xpath, 10);

            if (!ta.elementExist(xpath_1))
            {
                passed = ProvideCustomerID();

                xpath = "(//input[@value='Proceed >>'])[last()]";
                passed = ta.clickOn(xpath);

                xpath = "//input[@value='Create new account']";
                passed = ta.waitUntil(xpath, 2);
                passed = ta.clickOn(xpath);
            }

            return passed;
        }

        private static boolean testStep_4()
        {
            passed = FillApplicationFixedResidential();

            return passed;
        }

        private static boolean testStep_5()
        {
            xpath = "//*[text()[contains(.,'Account Properties Details')]]";
            passed = ta.waitUntil(xpath,5);

            xpath = "(//input[@value='Confirm'])[last()]";
            passed = ta.clickOn(xpath);

            return passed;
        }

        private static boolean testStep_6()
        {
            xpath = "//*[text()='Service Order']";
            passed = ta.waitUntil(xpath, 5);

            xpath = "(//*[text()='Account Number:']/./following::*[text()])[1]";
            AccountNumber = ta.getDatafromPage(xpath);
            ta.log("Account number Created - " + AccountNumber);

            return passed;
        }


        //SUPPORT FUNCTIONS
        private static boolean FillApplicationFixedResidential()
        {
            //CUSTOMER INFORMATION
            xpath = "(//*[text()[contains(.,'Salutation:')]]/./following::select)[1]";
            passed = ta.waitUntil(xpath, 5);
            passed = ta.selectBy(xpath, 1);

            xpath = "(//*[text()[contains(.,'Surname:')]]/./following::input)[1]";
            passed = ta.waitUntil(xpath, 5);
            passed = ta.sendDatatoField(xpath, AccountLastName);

            xpath = "(//*[text()[contains(.,'First Name:')]]/./following::input)[1]";
            passed = ta.waitUntil(xpath, 5);
            passed = ta.sendDatatoField(xpath, AccountFirstName);

            xpath = "(//*[text()[contains(.,'Date Of Birth:')]]/./following::input)[1]";
            passed = ta.sendDatatoField(xpath, "02/02/1991");

            xpath = "(//*[text()[contains(.,'Nationality')]]/./following::select)[1]";
            passed = ta.selectBy(xpath, 1);

            //BILLING ADDRESS
            xpath = "(//*[text()[contains(.,'Address Type:')]]/./following::select)[1]";
            passed = ta.selectBy(xpath, "A");

            xpath = "(//*[text()[contains(.,'Address Type:')]]/./following::input[contains(@class,'MandatoryTextBox')])[1]";
            passed = ta.waitUntil(xpath, 2);
            passed = ta.sendDatatoField(xpath, AccountFirstName);

            //CUSTOMER CLASSIFICATION
            xpath = "(//*[text()[contains(.,'Company:')]]/./following::select)[1]";
            passed = ta.selectBy(xpath, Company);

            xpath = "(//*[text()[contains(.,'Customer Type:')]]/./following::select)[1]";
            if (ta.getValueFromSelect(xpath).equals("-- Select --"))
            {
                passed = ta.selectBy(xpath, CustomerType);
            }

            xpath = "(//*[text()[contains(.,'Marketing Category:')]]/./following::select)[1]";
            if (ta.getValueFromSelect(xpath).equals("-- Select --"))
            {
                passed = ta.selectBy(xpath, MarketingCategory);
            }

            xpath = "(//*[text()[contains(.,'Region:')]]/./following::select)[1]";
            if (ta.getValueFromSelect(xpath).equals("-- Select --"))
            {
                passed = ta.selectBy(xpath, Region);
            }

            xpath = "(//*[text()='Account Type:']/./following::select)[1]";
            if (ta.getValueFromSelect(xpath).equals("-- Select --"))
            {
                passed = ta.selectBy(xpath, AccountType);
            }

            xpath = "(//*[text()[contains(.,'Bill Stats Area')]]/./following::select)[1]";
            passed = ta.waitUntilSelectOptionLoads(xpath, 5);
            if (ta.getValueFromSelect(xpath).equals("-- Select --"))
            {
                xpath = "(//*[text()[contains(.,'Bill Stats Area:')]]/./following::input)[2]";
                passed = ta.clickOn(xpath);
                ta.waitFor(1000);
                xpath = "(//*[text()[contains(.,'Bill Stats Area:')]]/./following::select)[1]";
                passed = ta.selectBy(xpath, BillStatusArea);
            }

            //BILLING DETAILS
            xpath = "(//*[text()[contains(.,'Bill Frequency')]]/./following::select)[1]";
            if (ta.getValueFromSelect(xpath).equals("-- Select --"))
            {
                passed = ta.selectBy(xpath, 1);
            }

            xpath = "(//*[text()[contains(.,'Rental Period')]]/./following::select)[1]";
            if (ta.getValueFromSelect(xpath).equals("-- Select --"))
            {
                passed = ta.selectBy(xpath, 1);
            }

            xpath = "(//*[text()[contains(.,'Primary Day Of Billing')]]/./following::select)[1]";
            if (ta.getValueFromSelect(xpath).equals("-- Select --"))
            {
                passed = ta.selectBy(xpath, 1);
            }

            xpath = "(//*[text()[contains(.,'Bill Media:')]]/./following::select)[1]";
            if (ta.getValueFromSelect(xpath).equals("-- Select --"))
            {
                passed = ta.selectBy(xpath, "P");
            }

            xpath = "(//*[text()[contains(.,'Bill Language:')]]/./following::select)[1]";
            if (ta.getValueFromSelect(xpath).equals("-- Select --"))
            {
                passed = ta.selectBy(xpath, "E");
            }

            xpath = "(//*[text()[contains(.,'Bill Report:')]]/./following::select)[1]";
            if (ta.getValueFromSelect(xpath).equals("-- Select --"))
            {
                passed = ta.selectBy(xpath, 1);
            }

            xpath = "(//*[text()[contains(.,'Interim Bill Report:')]]/./following::select)[1]";
            if (ta.getValueFromSelect(xpath).equals("-- Select --"))
            {
                passed = ta.selectBy(xpath, 1);
            }

            xpath = "(//*[text()[contains(.,'Bill Sort Code:')]]/./following::select)[1]";
            if (ta.getValueFromSelect(xpath).equals("-- Select --"))
            {
                passed = ta.selectBy(xpath, 1);
            }

            //CUSTOMER ID
            if (!CustomerIDProvided)
            {
                ProvideCustomerID();
            }

            xpath = "(//input[@value='Next>>'])[last()]";
            passed = ta.clickOn(xpath);

            return passed;
        }

        private static boolean ProvideCustomerID()
        {
            xpath = "(//*[text()[contains(.,'ID:')]]/./following::select)[1]";
            passed = ta.selectBy(xpath, CustomeIDType);

            xpath = "(//*[text()[contains(.,'ID:')]]/./following::input[contains(@class,'MandatoryTextBox')])[1]";
            passed = ta.waitUntil(xpath, 2);
            passed = ta.sendDatatoField(xpath, AccountFirstName);

            CustomerIDProvided = true;

            return passed;
        }
}
