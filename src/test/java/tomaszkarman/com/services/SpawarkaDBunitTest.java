package tomaszkarman.com.services;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import tomaszkarman.com.domain.Spawarka;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class SpawarkaDBunitTest {

    @Autowired
    private SpawarkaService spawarkaService;

    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "ds-1.xml")
    public void testCreateRead() {

        Spawarka spawarka = new Spawarka("Audi", "A4", 2221, "Lorem ipsum");
        spawarka.setId(1);
        spawarkaService.saveSpawarka(spawarka);
        assertEquals(spawarkaService.findById(1).getName(), "Audi");
    }


    @Test
    @DatabaseSetup("ds-0.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "ds-2.xml")
    public void testUpdate() {

        Spawarka spawarka = new Spawarka("Audi", "A4", 2221, "Lorem ipsum");
        spawarka.setId(1);
        spawarkaService.saveSpawarka(spawarka);

        Spawarka foundByIdSpawarka = spawarkaService.findById(1);
        foundByIdSpawarka.setName("BMW");
        spawarkaService.saveSpawarka(foundByIdSpawarka);

        assertEquals(spawarkaService.findById(1).getName(), "BMW");
    }

    @Test
    @DatabaseSetup("ds-3.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "ds-4.xml")
    public void deleteTest() {

        for (int i = 1; i <= 10; i++) {
            Spawarka spawarka = new Spawarka("BMW", "A4", 2220 + i, "Lorem ipsum");
            spawarka.setId(i);
            spawarkaService.saveSpawarka(spawarka);
        }

        assertEquals(10, spawarkaService.getAllItems().size());

        spawarkaService.deleteById(1);
        spawarkaService.deleteById(2);
        spawarkaService.deleteById(3);
        spawarkaService.deleteById(4);
        spawarkaService.deleteById(5);

        assertEquals(5, spawarkaService.getAllItems().size());
        assertEquals("BMW", spawarkaService.findById(6).getName());
    }
}
