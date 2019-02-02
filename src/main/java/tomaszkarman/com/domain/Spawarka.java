package tomaszkarman.com.domain;

import javax.persistence.*;

@Entity
@Table(name = "spawarka", schema = "tau", catalog = "")
public class Spawarka {
    private int id;
    private String name;
    private String model;
    private Integer kod;
    private String description;

    public Spawarka(String name, String model, Integer kod, String description) {
        this.name = name;
        this.model = model;
        this.kod = kod;
        this.description = description;
    }

    public Spawarka() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    public Integer getKod() {
        return kod;
    }

    public void setKod(Integer kod) {
        this.kod = kod;
    }

    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

