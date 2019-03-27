import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.simpleFunctionsOfSite;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

//@RunWith(Parameterized.class)
public class loginTest {
//    private static String browser;
    private static simpleFunctionsOfSite simpleFunctionsOfSite = new simpleFunctionsOfSite();


//    public loginTest(String browser) {
//        this.browser = browser;
//    }

//   @Parameterized.Parameters
//   public static ArrayList<String> browsers(){
//        ArrayList<String> browsers = new ArrayList<>();
////        browsers.add("chrome");
//        browsers.add("firefox");
//        return browsers;
//   }

   @BeforeClass
   public static void setUp(){
        Configuration.screenshots=true;
        Configuration.browser="firefox";
        Configuration.startMaximized=true;
        open("http://dark-world.ru/");
        simpleFunctionsOfSite.login("shmel9311@gmail.com", "458854qwe");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
   }

    @Test
    @DisplayName("Type text on search field")
    public void testWithSearch(){
        simpleFunctionsOfSite.typeTextOnSearchField("Burzum");
    }

    @Test
    @DisplayName("Click on menu button")
    public void testWithMenu(){
        simpleFunctionsOfSite.clickOnElementOfMenu("Видео");
    }

    @Test
    @DisplayName("Move to Video and click on Concerts")
    public void testWithHover(){
        simpleFunctionsOfSite.moveToElementOfMenu("Видео", "Концерты");
    }

    @Test
    @DisplayName("Select style")
    public void testWithDropMenu(){
        simpleFunctionsOfSite.clickOnElementOfMenu("Группы");
        simpleFunctionsOfSite.selectItemFromDropDownMenu("Sympho Black");
    }

    @Test
    @DisplayName("Select style")
    public void testWithDropMenuFailed(){
        simpleFunctionsOfSite.clickOnElementOfMenu("Группы");
        simpleFunctionsOfSite.selectItemFromDropDownMenu("Sympho Blakc");
    }

    @After
    public void tearDownAfterTest(){
        simpleFunctionsOfSite.clickOnElementOfMenu("Главная");
    }

    @AfterClass
    public static void tearDown(){
        close();

    }

    @Rule
    public TestWatcher screenShotOnFailure = new TestWatcher() {

        @Override
        protected void succeeded(Description description) {
            makeScreenShotOnFailure();
        }

        @Override
        protected void skipped(AssumptionViolatedException e, Description description) {
            makeScreenShotOnFailure();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            makeScreenShotOnFailure();
        }

        @Attachment("ScreenShot")
        public byte[] makeScreenShotOnFailure() {
            return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        }

    };




}
