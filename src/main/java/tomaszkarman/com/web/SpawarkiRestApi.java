package tomaszkarman.com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tomaszkarman.com.dao.SpawarkaDao;
import tomaszkarman.com.domain.Spawarka;
import tomaszkarman.com.services.SpawarkaService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
public class SpawarkiRestApi {

    @Autowired
    private SpawarkaService spawarkaService;

    @GetMapping(value = "/all-spawarki")
    public List<Spawarka> getAllSpawarki() {
        return (List<Spawarka>) spawarkaService.getAllItems();
    }

    @GetMapping(value = "/generate")
    public List<Spawarka> generateDate() {
        spawarkaService.generateSampleData();
        return (List<Spawarka>) spawarkaService.getAllItems();
    }

    @GetMapping(value = "/get-by-id/{id}")
    public Spawarka getSpawarkaById(@PathVariable("id") int id) {
        return spawarkaService.findById(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Spawarka addNewSpawarka(@RequestBody Spawarka spawarka) {
        return spawarkaService.saveSpawarka(spawarka);
    }

    @PostMapping(value = "/update/{id}/{name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Spawarka updateNameById(@PathVariable("id") int id, @PathVariable("name") String name) {
        Spawarka spawarka = spawarkaService.findById(id);
        spawarka.setName(name);
        return spawarkaService.saveSpawarka(spawarka);
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") int id) {
        spawarkaService.deleteById(id);
        return (spawarkaService.findById(id) == null) ? "Potwierdzam usuniecie" : "Spawarka nie zostala usunieta";
    }

    @GetMapping(value = "/delete-all")
    public String deleteAll() {
        spawarkaService.deleteAll();
        return (spawarkaService.getAllItems().size() == 0) ? "Potwierdzam usuniecie wszystkich spawarek" : "Spawarki nie zostaly usuniete";
    }
}
