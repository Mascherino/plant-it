package com.github.mdeluise.plantit.botanicalinfo;

import com.github.mdeluise.plantit.image.BotanicalInfoImage;
import com.github.mdeluise.plantit.image.ImageTarget;
import com.github.mdeluise.plantit.plant.Plant;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "botanical_infos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "botanical_info_type", discriminatorType = DiscriminatorType.INTEGER, columnDefinition = "TINYINT(1)"
)
public class BotanicalInfo implements Serializable, ImageTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String scientificName;
    private String family;
    private String genus;
    private String species;
    @NotNull
    @OneToMany(mappedBy = "botanicalInfo")
    private Set<Plant> plants = new HashSet<>();
    @OneToOne(mappedBy = "target", cascade = CascadeType.ALL)
    private BotanicalInfoImage image;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getScientificName() {
        return scientificName;
    }


    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }


    public String getFamily() {
        return family;
    }


    public void setFamily(String family) {
        this.family = family;
    }


    public String getGenus() {
        return genus;
    }


    public void setGenus(String genus) {
        this.genus = genus;
    }


    public String getSpecies() {
        return species;
    }


    public void setSpecies(String species) {
        this.species = species;
    }


    public Set<Plant> getPlants() {
        return plants;
    }


    public void setPlants(Set<Plant> plants) {
        this.plants = plants;
    }


    public BotanicalInfoImage getImage() {
        return image;
    }


    public void setImage(BotanicalInfoImage image) {
        this.image = image;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BotanicalInfo)) {
            return false;
        }
        final BotanicalInfo that = (BotanicalInfo) o;
        return Objects.equals(id, that.id) || Objects.equals(species, that.species);
    }


    @Override
    public int hashCode() {
        return Objects.hash(species);
    }
}
