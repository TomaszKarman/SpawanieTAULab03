package tomaszkarman.com.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tomaszkarman.com.domain.Spawarka;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@Rollback
@Commit
@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpawarkaDaoTest {

    @Autowired
    SpawarkaDao spawarkaDao;

    @Test
    public void addNewSpawarka() {

        Random generator = new Random();
        Integer randomKod = generator.nextInt(100000) + 1;

        Spawarka spawarka = new Spawarka("Audi", "B8", randomKod, "Lorem ipsum");
        spawarkaDao.save(spawarka);

        assertEquals(spawarka.getName(), spawarkaDao.findByKod(randomKod).getName());
    }

    @Test
    public void updateSpawarka() {

        Random generator = new Random();
        Integer randomKod = generator.nextInt(100000) + 1;

        Spawarka spawarka = new Spawarka("Audi", "B8", randomKod, "Lorem ipsum");
        spawarkaDao.save(spawarka);

        Spawarka addedSpawarkaToDb = spawarkaDao.findByKod(randomKod);

        addedSpawarkaToDb.setName("skoda");
        spawarkaDao.save(addedSpawarkaToDb);

        assertEquals(addedSpawarkaToDb, spawarkaDao.findById(addedSpawarkaToDb.getId()));
    }

    @Test
    public void checkDelete() {

        Random generator = new Random();
        Integer randomKod = generator.nextInt(100000) + 1;

        Spawarka spawarka = new Spawarka("Audi", "B8", randomKod, "Lorem ipsum");
        spawarkaDao.save(spawarka);

        Spawarka addedSpawarkaToDb = spawarkaDao.findByKod(randomKod);
        spawarkaDao.deleteById(addedSpawarkaToDb.getId());

        assertNull(spawarkaDao.findById(addedSpawarkaToDb.getId()));
    }

    @Test
    public void checkFindByText() {

        for (int i = 0; i < 10; i++) {
            Random generator = new Random();
            Integer randomKod = generator.nextInt(100000) + 1;

            Spawarka spawarka = new Spawarka("Audi", "B8", randomKod, "Lorem");
            spawarkaDao.save(spawarka);
        }

        List<Spawarka> spawarkaList = spawarkaDao.findSpawarkaByDescriptionIsContaining("Lorem");
        assertTrue(spawarkaList.size() > 10);
    }
}