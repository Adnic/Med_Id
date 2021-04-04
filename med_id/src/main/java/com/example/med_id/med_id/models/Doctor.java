package com.example.med_id.med_id.models;

import javax.persistence.*;

@Entity
@Table(name = "m_doctor")
public class Doctor extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long Id;

    @OneToOne
    @JoinColumn(name = "biodata_id", insertable = false, updatable = false)
    public Biodata biodata;

    @Column(name = "biodata_id", nullable = true)
    private long BiodataId;

    @Column(name = "str", length = 50, nullable = true)
    private String Str;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Biodata getBiodata() {
        return biodata;
    }

    public void setBiodata(Biodata biodata) {
        this.biodata = biodata;
    }

    public long getBiodataId() {
        return BiodataId;
    }

    public void setBiodataId(long biodataId) {
        BiodataId = biodataId;
    }

    public String getStr() {
        return Str;
    }

    public void setStr(String str) {
        Str = str;
    }
}
