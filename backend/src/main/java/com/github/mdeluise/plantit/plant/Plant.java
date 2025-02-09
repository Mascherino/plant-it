package com.github.mdeluise.plantit.plant;

import com.github.mdeluise.plantit.authentication.User;
import com.github.mdeluise.plantit.botanicalinfo.BotanicalInfo;
import com.github.mdeluise.plantit.diary.Diary;
import com.github.mdeluise.plantit.image.ImageTarget;
import com.github.mdeluise.plantit.image.PlantImage;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plants")
public class Plant implements Serializable, ImageTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date startDate;
    @NotBlank
    @Length(max = 30)
    private String personalName;
    private Date endDate;
    @Enumerated(EnumType.STRING)
    private PlantState plantState;
    @Length(max = 8500)
    private String note;
    @OneToOne(mappedBy = "target", cascade = CascadeType.ALL)
    private Diary diary;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "botanical_name_id", nullable = false)
    private BotanicalInfo botanicalInfo;
    @NotNull
    @OneToMany(mappedBy = "target")
    private Set<PlantImage> images = new HashSet<>();


    public Plant() {
        this.plantState = PlantState.PURCHASED;
    }


    public Plant(User owner) {
        this();
        this.owner = owner;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Date getStartDate() {
        return startDate;
    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public String getPersonalName() {
        return personalName;
    }


    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }


    public Date getEndDate() {
        return endDate;
    }


    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public PlantState getState() {
        return plantState;
    }


    public void setState(PlantState plantState) {
        this.plantState = plantState;
    }


    public String getNote() {
        return note;
    }


    public void setNote(String note) {
        this.note = note;
    }


    public Diary getDiary() {
        return diary;
    }


    public void setDiary(Diary diary) {
        this.diary = diary;
    }


    public User getOwner() {
        return owner;
    }


    public void setOwner(User owner) {
        this.owner = owner;
    }


    public BotanicalInfo getBotanicalInfo() {
        return botanicalInfo;
    }


    public void setBotanicalInfo(BotanicalInfo botanicalInfo) {
        this.botanicalInfo = botanicalInfo;
    }


    public Set<PlantImage> getImages() {
        return images;
    }


    public void setImages(Set<PlantImage> images) {
        this.images = images;
    }
}
