package org.esup_portail.esup_stage.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.esup_portail.esup_stage.dto.view.Views;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Structure")
public class Structure extends ObjetMetier {

    @JsonView(Views.List.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStructure", nullable = false)
    private int id;

    @Column(length = 20)
    private String libCedex;

    @Column(length = 20)
    private String codeEtab;

    @NotNull
    @NotEmpty
    @Size(min = 14, max = 14)
    @JsonView(Views.List.class)
    @Column(length = 14)
    private String numeroSiret;

    @NotNull
    @JsonView(Views.List.class)
    @ManyToOne
    @JoinColumn(name = "codeNAF_N5")
    private NafN5 nafN5;

    @NotNull
    @NotEmpty
    @Size(max = 150)
    @JsonView(Views.List.class)
    @Column(nullable = false, length = 150)
    private String raisonSociale;

    @Lob
    private String activitePrincipale;

    @NotNull
    @NotEmpty
    @Size(max = 20)
    @Column(length = 20)
    private String telephone;

    @Column(length = 20)
    private String fax;

    @Size(max = 50)
    @Email
    @Column(length = 50)
    private String mail;

    @NotNull
    @NotEmpty
    @Size(max = 200)
    @URL
    @Column(length = 200)
    private String siteWeb;

    @Column(length = 50)
    private String groupe;

    @Column(length = 200)
    private String logo;

    @Column(nullable = false)
    private boolean estValidee;

    @Column(length = 50)
    private String loginValidation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateValidation;

    @Column(length = 50)
    private String loginStopValidation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStopValidation;

    @Temporal(TemporalType.DATE)
    private Date infosAJour;

    @Column(length = 50)
    private String loginInfosAJour;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idEffectif", nullable = false)
    private Effectif effectif;

    @NotNull
    @JsonView(Views.List.class)
    @ManyToOne
    @JoinColumn(name = "idStatutJuridique")
    private StatutJuridique statutJuridique;

    @NotNull
    @JsonView(Views.List.class)
    @ManyToOne
    @JoinColumn(name = "idTypeStructure", nullable = false)
    private TypeStructure typeStructure;

    @Column(length = 50)
    private String nomDirigeant;

    @Column(length = 50)
    private String prenomDirigeant;

    @Column(length = 1)
    private String temEnServStructure;

    @Column(length = 200)
    private String batimentResidence;

    @NotNull
    @NotEmpty
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String voie;

    @NotNull
    @NotEmpty
    @Size(max = 200)
    @JsonView(Views.List.class)
    @Column(length = 200)
    private String commune;

    @NotNull
    @Size(max = 10)
    @Column(length = 10)
    private String codePostal;

    @Column(length = 10)
    private String codeCommune;

    @NotNull
    @JsonView(Views.List.class)
    @ManyToOne
    @JoinColumn(name = "idPays", nullable = false)
    private Pays pays;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibCedex() {
        return libCedex;
    }

    public void setLibCedex(String libCedex) {
        this.libCedex = libCedex;
    }

    public String getCodeEtab() {
        return codeEtab;
    }

    public void setCodeEtab(String codeEtab) {
        this.codeEtab = codeEtab;
    }

    public String getNumeroSiret() {
        return numeroSiret;
    }

    public void setNumeroSiret(String numeroSiret) {
        this.numeroSiret = numeroSiret;
    }

    public NafN5 getNafN5() {
        return nafN5;
    }

    public void setNafN5(NafN5 nafN5) {
        this.nafN5 = nafN5;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getActivitePrincipale() {
        return activitePrincipale;
    }

    public void setActivitePrincipale(String activitePrincipale) {
        this.activitePrincipale = activitePrincipale;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean isEstValidee() {
        return estValidee;
    }

    public void setEstValidee(boolean estValidee) {
        this.estValidee = estValidee;
    }

    public String getLoginValidation() {
        return loginValidation;
    }

    public void setLoginValidation(String loginValidation) {
        this.loginValidation = loginValidation;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }

    public String getLoginStopValidation() {
        return loginStopValidation;
    }

    public void setLoginStopValidation(String loginStopValidation) {
        this.loginStopValidation = loginStopValidation;
    }

    public Date getDateStopValidation() {
        return dateStopValidation;
    }

    public void setDateStopValidation(Date dateStopValidation) {
        this.dateStopValidation = dateStopValidation;
    }

    public Date getInfosAJour() {
        return infosAJour;
    }

    public void setInfosAJour(Date infosAJour) {
        this.infosAJour = infosAJour;
    }

    public String getLoginInfosAJour() {
        return loginInfosAJour;
    }

    public void setLoginInfosAJour(String loginInfosAJour) {
        this.loginInfosAJour = loginInfosAJour;
    }

    public Effectif getEffectif() {
        return effectif;
    }

    public void setEffectif(Effectif effectif) {
        this.effectif = effectif;
    }

    public StatutJuridique getStatutJuridique() {
        return statutJuridique;
    }

    public void setStatutJuridique(StatutJuridique statutJuridique) {
        this.statutJuridique = statutJuridique;
    }

    public TypeStructure getTypeStructure() {
        return typeStructure;
    }

    public void setTypeStructure(TypeStructure typeStructure) {
        this.typeStructure = typeStructure;
    }

    public String getNomDirigeant() {
        return nomDirigeant;
    }

    public void setNomDirigeant(String nomDirigeant) {
        this.nomDirigeant = nomDirigeant;
    }

    public String getPrenomDirigeant() {
        return prenomDirigeant;
    }

    public void setPrenomDirigeant(String prenomDirigeant) {
        this.prenomDirigeant = prenomDirigeant;
    }

    public String getTemEnServStructure() {
        return temEnServStructure;
    }

    public void setTemEnServStructure(String temEnServStructure) {
        this.temEnServStructure = temEnServStructure;
    }

    public String getBatimentResidence() {
        return batimentResidence;
    }

    public void setBatimentResidence(String batimentResidence) {
        this.batimentResidence = batimentResidence;
    }

    public String getVoie() {
        return voie;
    }

    public void setVoie(String voie) {
        this.voie = voie;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getCodeCommune() {
        return codeCommune;
    }

    public void setCodeCommune(String codeCommune) {
        this.codeCommune = codeCommune;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }
}