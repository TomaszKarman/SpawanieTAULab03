package tomaszkarman.com.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tomaszkarman.com.domain.Spawarka;

import java.util.List;

@Repository
public interface SpawarkaDao extends CrudRepository<Spawarka, Integer> {

    Spawarka findByKod(Integer kod);
    Spawarka findById(int id);
    void deleteById(int id);
    List<Spawarka> findSpawarkaByDescriptionIsContaining(String value);
    void deleteAll();
}
