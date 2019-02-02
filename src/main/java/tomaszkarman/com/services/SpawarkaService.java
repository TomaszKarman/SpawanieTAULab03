package tomaszkarman.com.services;

import org.springframework.stereotype.Service;
import tomaszkarman.com.dao.SpawarkaDao;
import tomaszkarman.com.domain.Spawarka;

import java.util.List;
import java.util.UUID;

@Service
public class SpawarkaService {

    private SpawarkaDao spawarkaDao;

    public SpawarkaService(SpawarkaDao spawarkaDao) {
        this.spawarkaDao = spawarkaDao;
    }

    public Spawarka findById(int id) {
        return spawarkaDao.findById(id);
    }

    public Spawarka findByKod(Integer kod) {
        return spawarkaDao.findByKod(kod);
    }

    public Spawarka saveSpawarka(Spawarka spawarka) {
        return spawarkaDao.save(spawarka);
    }

    public List<Spawarka> getAllItems() {
        return (List<Spawarka>) spawarkaDao.findAll();
    }

    public void deleteById(int id) {
        spawarkaDao.deleteById(id);
    }

    public void generateSampleData() {
        for (int i = 1; i <= 20; i++) {
            Spawarka spawarka = new Spawarka("Audi", "A4", 3320 + i, "Lorem ipsum sole " + UUID.randomUUID());
            spawarka.setId(i);
            spawarkaDao.save(spawarka);
        }
    }

    public void deleteAll() {
        spawarkaDao.deleteAll();
    }
}

