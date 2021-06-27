
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import steps.MyStep;


public class RunTest {

    private MyStep step;

    @BeforeEach
    public void testBefore() {
        step = new MyStep();
    }


    @Test
    public void test() throws InterruptedException {
        step.openURL("https://www.avito.ru");
        step.makeScreenShot();
        step.openCategory("Оргтехника и расходники");
        step.makeScreenShot();
        step.elementSearch("Принтер");
        step.makeScreenShot();
        step.clickRegion();
        step.makeScreenShot();
        step.enterCity("Владивосток");
        step.makeScreenShot();
        step.pressButtonShow();
        step.makeScreenShot();
        step.activateСheckbox();
        step.makeScreenShot();
        step.activateSort("Дороже");
        step.makeScreenShot();
        step.outputValues(3);
        step.makeScreenShot();
    }


}
