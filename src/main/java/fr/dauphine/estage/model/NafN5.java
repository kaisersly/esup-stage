package fr.dauphine.estage.model;

import javax.persistence.*;

@Entity
@Table(name = "NAF_N5")
public class NafN5 {

    @Id
    @Column(name = "codeNAF_N5", nullable = false)
    private String code;

    @Column(name = "libelleNAF_N5", length = 150)
    private String libelle;

    @Column(name = "temEnServNAF_N5", length = 1)
    private String temEnServ;

    @ManyToOne
    @JoinColumn(name = "codeNAF_N1", nullable = false)
    private NafN1 nafN1;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getTemEnServ() {
        return temEnServ;
    }

    public void setTemEnServ(String temEnServ) {
        this.temEnServ = temEnServ;
    }

    public NafN1 getNafN1() {
        return nafN1;
    }

    public void setNafN1(NafN1 nafN1) {
        this.nafN1 = nafN1;
    }
}
