package org.esup_portail.esup_stage.model;

import javax.persistence.*;

@Entity
@Table(name = "TypeConvention")
public class TypeConvention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTypeConvention", nullable = false)
    private int id;

    @Column(name = "libelleTypeConvention", nullable = false, length = 100)
    private String libelle;

    @Column(nullable = false, length = 20)
    private String codeCtrl;

    @Column(name = "temEnServTypeConvention", nullable = false, length = 1)
    private String temEnServ;

    private Boolean modifiable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCodeCtrl() {
        return codeCtrl;
    }

    public void setCodeCtrl(String codeCtrl) {
        this.codeCtrl = codeCtrl;
    }

    public String getTemEnServ() {
        return temEnServ;
    }

    public void setTemEnServ(String temEnServ) {
        this.temEnServ = temEnServ;
    }

    public Boolean getModifiable() {
        return modifiable;
    }

    public void setModifiable(Boolean modifiable) {
        this.modifiable = modifiable;
    }
}