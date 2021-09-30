package fr.dauphine.estage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import fr.dauphine.estage.dto.view.Views;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Convention")
public class Convention extends ObjetMetier {

    @JsonView(Views.List.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConvention", nullable = false)
    private int id;

    @JsonView(Views.List.class)
    @ManyToOne
    @JoinColumn(name = "idEtudiant", nullable = false)
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "idCentreGestion", nullable = false)
    private CentreGestion centreGestion;

    @JsonView(Views.List.class)
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "codeUFR", referencedColumnName = "codeUFR"),
            @JoinColumn(name = "codeUniversiteUFR", referencedColumnName = "codeUniversite"),
    })
    private Ufr ufr;

    @JsonView(Views.List.class)
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "codeEtape", referencedColumnName = "codeEtape"),
            @JoinColumn(name = "codeVersionEtape", referencedColumnName = "codeVersionEtape"),
            @JoinColumn(name = "codeUniversiteEtape", referencedColumnName = "codeUniversite"),
    })
    private Etape etape;

    @Column(length = 10)
    private String codeDepartement;

    @ManyToOne
    @JoinColumn(name = "idEnseignant")
    private Enseignant enseignant;

    @JsonView(Views.List.class)
    @ManyToOne
    @JoinColumn(name = "idStructure")
    private Structure structure;

    @ManyToOne
    @JoinColumn(name = "idService")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "idContact")
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "idSignataire")
    private Contact signataire;

    @ManyToOne
    @JoinColumn(name = "idTypeConvention", nullable = false)
    private TypeConvention typeConvention;

    @ManyToOne
    @JoinColumn(name = "idOffre")
    private Offre offre;

    @Column(nullable = false)
    private String sujetStage;

    @JsonView(Views.List.class)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDebutStage;

    @JsonView(Views.List.class)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFinStage;

    @Column(nullable = false)
    private boolean interruptionStage;

    @Temporal(TemporalType.DATE)
    private Date dateDebutInterruption;

    @Temporal(TemporalType.DATE)
    private Date dateFinInterruption;

    @Column(nullable = false)
    private String nbJoursHebdo; // TODO enum ?

    @ManyToOne
    @JoinColumn(name = "idTempsTravail", nullable = false)
    private TempsTravail tempsTravail;

    private String commentaireDureeTravail;

    @ManyToOne
    @JoinColumn(name = "codeLangueConvention", nullable = false)
    private LangueConvention langueConvention;

    @ManyToOne
    @JoinColumn(name = "idOrigineStage")
    private OrigineStage origineStage;

    @ManyToOne
    @JoinColumn(name = "idTheme", nullable = false)
    private Theme theme;

    private Boolean conventionStructure;

    @JsonView(Views.List.class)
    private Boolean validationPedagogique;

    @JsonView(Views.List.class)
    private Boolean validationConvention;

    @Column(nullable = false)
    private boolean conversionEnContrat;

    private String commentaireStage;

    @Column(length = 200)
    private String adresseEtudiant;

    @Column(length = 10)
    private String codePostalEtudiant;

    @Column(length = 80)
    private String villeEtudiant;

    @Column(length = 50)
    private String paysEtudiant;

    @Column(length = 100)
    private String courrielPersoEtudiant;

    @Column(length = 20)
    private String telEtudiant;

    @Column(length = 20)
    private String telPortableEtudiant;

    @ManyToOne
    @JoinColumn(name = "idIndemnisation", nullable = false)
    private Indemnisation indemnisation;

    @Column(length = 15)
    private String montantGratification;

    private String fonctionsEtTaches;

    private String details;

    @JsonView(Views.List.class)
    @Column(length = 10)
    private String annee;

    @ManyToOne
    @JoinColumn(name = "idAssurance", nullable = false)
    private Assurance assurance;

    @Column(length = 15)
    private String insee;

    @Column(length = 5)
    private String codeCaisse;

    @Column(nullable = false, length = 1)
    private String temConfSujetTeme;

    @Column(length = 5)
    private String nbHeuresHebdo;

    private Integer quotiteTravail;

    private String modeEncadreSuivi;

    @ManyToOne
    @JoinColumn(name = "idModeVersGratification")
    private ModeVersGratification modeVersGratification;

    private String avantagesNature;

    @ManyToOne
    @JoinColumn(name = "idNatureTravail", nullable = false)
    private NatureTravail natureTravail;

    @ManyToOne
    @JoinColumn(name = "idModeValidationStage", nullable = false)
    private ModeValidationStage modeValidationStage;

    @Column(length = 8)
    private String codeElp;

    @Column(length = 60)
    private String libelleELP;

    private BigDecimal creditECTS;

    private String travailNuitFerie;

    @Column(nullable = false)
    private int dureeStage;

    @Column(length = 100)
    private String nomEtabRef;

    @Column(length = 200)
    private String adresseEtabRef;

    @Column(length = 30)
    private String nomSignataireComposante;

    @Column(length = 60)
    private String qualiteSignataire;

    @Column(length = 100)
    private String libelleCPAM;

    @Column(length = 4)
    private String dureeExceptionnelle;

    @ManyToOne
    @JoinColumn(name = "idUniteDureeExceptionnelle")
    private UniteDuree uniteDureeExceptionnelle;

    @ManyToOne
    @JoinColumn(name = "idUniteGratification")
    private UniteGratification uniteGratification;

    @Column(length = 3)
    private String codeFinalite;

    @Column(length = 60)
    private String libelleFinalite;

    @Column(length = 1)
    private String codeCursusLMD;

    private Boolean priseEnChargeFraisMission;

    @Column(length = 1)
    private String codeRGI;

    @Column(length = 50)
    private String loginValidation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateValidation;

    @Column(length = 50)
    private String loginSignature;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSignature;

    private Boolean envoiMailEtudiant;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnvoiMailEtudiant;

    private Boolean envoiMailTuteurPedago;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnvoiMailTuteurPedago;

    private Boolean envoiMailTuteurPro;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnvoiMailTuteurPro;

    private String nbConges;
    private String competences;

    @ManyToOne
    @JoinColumn(name = "idUniteDureeGratification")
    private UniteDuree uniteDureeGratification;

    @Column(length = 50)
    private String monnaieGratification;

    @Column(length = 10)
    private String volumeHoraireFormation;

    @Column(length = 30)
    private String typePresence;

    @ManyToOne
    @JoinColumn(name = "idDevise")
    private Devise devise;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public CentreGestion getCentreGestion() {
        return centreGestion;
    }

    public void setCentreGestion(CentreGestion centreGestion) {
        this.centreGestion = centreGestion;
    }

    public Ufr getUfr() {
        return ufr;
    }

    public void setUfr(Ufr ufr) {
        this.ufr = ufr;
    }

    public Etape getEtape() {
        return etape;
    }

    public void setEtape(Etape etape) {
        this.etape = etape;
    }

    public String getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Contact getSignataire() {
        return signataire;
    }

    public void setSignataire(Contact signataire) {
        this.signataire = signataire;
    }

    public TypeConvention getTypeConvention() {
        return typeConvention;
    }

    public void setTypeConvention(TypeConvention typeConvention) {
        this.typeConvention = typeConvention;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public String getSujetStage() {
        return sujetStage;
    }

    public void setSujetStage(String sujetStage) {
        this.sujetStage = sujetStage;
    }

    public Date getDateDebutStage() {
        return dateDebutStage;
    }

    public void setDateDebutStage(Date dateDebutStage) {
        this.dateDebutStage = dateDebutStage;
    }

    public Date getDateFinStage() {
        return dateFinStage;
    }

    public void setDateFinStage(Date dateFinStage) {
        this.dateFinStage = dateFinStage;
    }

    public boolean isInterruptionStage() {
        return interruptionStage;
    }

    public void setInterruptionStage(boolean interruptionStage) {
        this.interruptionStage = interruptionStage;
    }

    public Date getDateDebutInterruption() {
        return dateDebutInterruption;
    }

    public void setDateDebutInterruption(Date dateDebutInterruption) {
        this.dateDebutInterruption = dateDebutInterruption;
    }

    public Date getDateFinInterruption() {
        return dateFinInterruption;
    }

    public void setDateFinInterruption(Date dateFinInterruption) {
        this.dateFinInterruption = dateFinInterruption;
    }

    public String getNbJoursHebdo() {
        return nbJoursHebdo;
    }

    public void setNbJoursHebdo(String nbJoursHebdo) {
        this.nbJoursHebdo = nbJoursHebdo;
    }

    public TempsTravail getTempsTravail() {
        return tempsTravail;
    }

    public void setTempsTravail(TempsTravail tempsTravail) {
        this.tempsTravail = tempsTravail;
    }

    public String getCommentaireDureeTravail() {
        return commentaireDureeTravail;
    }

    public void setCommentaireDureeTravail(String commentaireDureeTravail) {
        this.commentaireDureeTravail = commentaireDureeTravail;
    }

    public LangueConvention getLangueConvention() {
        return langueConvention;
    }

    public void setLangueConvention(LangueConvention langueConvention) {
        this.langueConvention = langueConvention;
    }

    public OrigineStage getOrigineStage() {
        return origineStage;
    }

    public void setOrigineStage(OrigineStage origineStage) {
        this.origineStage = origineStage;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Boolean getConventionStructure() {
        return conventionStructure;
    }

    public void setConventionStructure(Boolean conventionStructure) {
        this.conventionStructure = conventionStructure;
    }

    public Boolean getValidationPedagogique() {
        return validationPedagogique;
    }

    public void setValidationPedagogique(Boolean validationPedagogique) {
        this.validationPedagogique = validationPedagogique;
    }

    public Boolean getValidationConvention() {
        return validationConvention;
    }

    public void setValidationConvention(Boolean validationConvention) {
        this.validationConvention = validationConvention;
    }

    public boolean isConversionEnContrat() {
        return conversionEnContrat;
    }

    public void setConversionEnContrat(boolean conversionEnContrat) {
        this.conversionEnContrat = conversionEnContrat;
    }

    public String getCommentaireStage() {
        return commentaireStage;
    }

    public void setCommentaireStage(String commentaireStage) {
        this.commentaireStage = commentaireStage;
    }

    public String getAdresseEtudiant() {
        return adresseEtudiant;
    }

    public void setAdresseEtudiant(String adresseEtudiant) {
        this.adresseEtudiant = adresseEtudiant;
    }

    public String getCodePostalEtudiant() {
        return codePostalEtudiant;
    }

    public void setCodePostalEtudiant(String codePostalEtudiant) {
        this.codePostalEtudiant = codePostalEtudiant;
    }

    public String getVilleEtudiant() {
        return villeEtudiant;
    }

    public void setVilleEtudiant(String villeEtudiant) {
        this.villeEtudiant = villeEtudiant;
    }

    public String getPaysEtudiant() {
        return paysEtudiant;
    }

    public void setPaysEtudiant(String paysEtudiant) {
        this.paysEtudiant = paysEtudiant;
    }

    public String getCourrielPersoEtudiant() {
        return courrielPersoEtudiant;
    }

    public void setCourrielPersoEtudiant(String courrielPersoEtudiant) {
        this.courrielPersoEtudiant = courrielPersoEtudiant;
    }

    public String getTelEtudiant() {
        return telEtudiant;
    }

    public void setTelEtudiant(String telEtudiant) {
        this.telEtudiant = telEtudiant;
    }

    public String getTelPortableEtudiant() {
        return telPortableEtudiant;
    }

    public void setTelPortableEtudiant(String telPortableEtudiant) {
        this.telPortableEtudiant = telPortableEtudiant;
    }

    public Indemnisation getIndemnisation() {
        return indemnisation;
    }

    public void setIndemnisation(Indemnisation indemnisation) {
        this.indemnisation = indemnisation;
    }

    public String getMontantGratification() {
        return montantGratification;
    }

    public void setMontantGratification(String montantGratification) {
        this.montantGratification = montantGratification;
    }

    public String getFonctionsEtTaches() {
        return fonctionsEtTaches;
    }

    public void setFonctionsEtTaches(String fonctionsEtTaches) {
        this.fonctionsEtTaches = fonctionsEtTaches;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public Assurance getAssurance() {
        return assurance;
    }

    public void setAssurance(Assurance assurance) {
        this.assurance = assurance;
    }

    public String getInsee() {
        return insee;
    }

    public void setInsee(String insee) {
        this.insee = insee;
    }

    public String getCodeCaisse() {
        return codeCaisse;
    }

    public void setCodeCaisse(String codeCaisse) {
        this.codeCaisse = codeCaisse;
    }

    public String getTemConfSujetTeme() {
        return temConfSujetTeme;
    }

    public void setTemConfSujetTeme(String temConfSujetTeme) {
        this.temConfSujetTeme = temConfSujetTeme;
    }

    public String getNbHeuresHebdo() {
        return nbHeuresHebdo;
    }

    public void setNbHeuresHebdo(String nbHeuresHebdo) {
        this.nbHeuresHebdo = nbHeuresHebdo;
    }

    public Integer getQuotiteTravail() {
        return quotiteTravail;
    }

    public void setQuotiteTravail(Integer quotiteTravail) {
        this.quotiteTravail = quotiteTravail;
    }

    public String getModeEncadreSuivi() {
        return modeEncadreSuivi;
    }

    public void setModeEncadreSuivi(String modeEncadreSuivi) {
        this.modeEncadreSuivi = modeEncadreSuivi;
    }

    public ModeVersGratification getModeVersGratification() {
        return modeVersGratification;
    }

    public void setModeVersGratification(ModeVersGratification modeVersGratification) {
        this.modeVersGratification = modeVersGratification;
    }

    public String getAvantagesNature() {
        return avantagesNature;
    }

    public void setAvantagesNature(String avantagesNature) {
        this.avantagesNature = avantagesNature;
    }

    public NatureTravail getNatureTravail() {
        return natureTravail;
    }

    public void setNatureTravail(NatureTravail natureTravail) {
        this.natureTravail = natureTravail;
    }

    public ModeValidationStage getModeValidationStage() {
        return modeValidationStage;
    }

    public void setModeValidationStage(ModeValidationStage modeValidationStage) {
        this.modeValidationStage = modeValidationStage;
    }

    public String getCodeElp() {
        return codeElp;
    }

    public void setCodeElp(String codeElp) {
        this.codeElp = codeElp;
    }

    public String getLibelleELP() {
        return libelleELP;
    }

    public void setLibelleELP(String libelleELP) {
        this.libelleELP = libelleELP;
    }

    public BigDecimal getCreditECTS() {
        return creditECTS;
    }

    public void setCreditECTS(BigDecimal creditECTS) {
        this.creditECTS = creditECTS;
    }

    public String getTravailNuitFerie() {
        return travailNuitFerie;
    }

    public void setTravailNuitFerie(String travailNuitFerie) {
        this.travailNuitFerie = travailNuitFerie;
    }

    public int getDureeStage() {
        return dureeStage;
    }

    public void setDureeStage(int dureeStage) {
        this.dureeStage = dureeStage;
    }

    public String getNomEtabRef() {
        return nomEtabRef;
    }

    public void setNomEtabRef(String nomEtabRef) {
        this.nomEtabRef = nomEtabRef;
    }

    public String getAdresseEtabRef() {
        return adresseEtabRef;
    }

    public void setAdresseEtabRef(String adresseEtabRef) {
        this.adresseEtabRef = adresseEtabRef;
    }

    public String getNomSignataireComposante() {
        return nomSignataireComposante;
    }

    public void setNomSignataireComposante(String nomSignataireComposante) {
        this.nomSignataireComposante = nomSignataireComposante;
    }

    public String getQualiteSignataire() {
        return qualiteSignataire;
    }

    public void setQualiteSignataire(String qualiteSignataire) {
        this.qualiteSignataire = qualiteSignataire;
    }

    public String getLibelleCPAM() {
        return libelleCPAM;
    }

    public void setLibelleCPAM(String libelleCPAM) {
        this.libelleCPAM = libelleCPAM;
    }

    public String getDureeExceptionnelle() {
        return dureeExceptionnelle;
    }

    public void setDureeExceptionnelle(String dureeExceptionnelle) {
        this.dureeExceptionnelle = dureeExceptionnelle;
    }

    public UniteDuree getUniteDureeExceptionnelle() {
        return uniteDureeExceptionnelle;
    }

    public void setUniteDureeExceptionnelle(UniteDuree uniteDureeExceptionnelle) {
        this.uniteDureeExceptionnelle = uniteDureeExceptionnelle;
    }

    public UniteGratification getUniteGratification() {
        return uniteGratification;
    }

    public void setUniteGratification(UniteGratification uniteGratification) {
        this.uniteGratification = uniteGratification;
    }

    public String getCodeFinalite() {
        return codeFinalite;
    }

    public void setCodeFinalite(String codeFinalite) {
        this.codeFinalite = codeFinalite;
    }

    public String getLibelleFinalite() {
        return libelleFinalite;
    }

    public void setLibelleFinalite(String libelleFinalite) {
        this.libelleFinalite = libelleFinalite;
    }

    public String getCodeCursusLMD() {
        return codeCursusLMD;
    }

    public void setCodeCursusLMD(String codeCursusLMD) {
        this.codeCursusLMD = codeCursusLMD;
    }

    public Boolean getPriseEnChargeFraisMission() {
        return priseEnChargeFraisMission;
    }

    public void setPriseEnChargeFraisMission(Boolean priseEnChargeFraisMission) {
        this.priseEnChargeFraisMission = priseEnChargeFraisMission;
    }

    public String getCodeRGI() {
        return codeRGI;
    }

    public void setCodeRGI(String codeRGI) {
        this.codeRGI = codeRGI;
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

    public String getLoginSignature() {
        return loginSignature;
    }

    public void setLoginSignature(String loginSignature) {
        this.loginSignature = loginSignature;
    }

    public Date getDateSignature() {
        return dateSignature;
    }

    public void setDateSignature(Date dateSignature) {
        this.dateSignature = dateSignature;
    }

    public Boolean getEnvoiMailEtudiant() {
        return envoiMailEtudiant;
    }

    public void setEnvoiMailEtudiant(Boolean envoiMailEtudiant) {
        this.envoiMailEtudiant = envoiMailEtudiant;
    }

    public Date getDateEnvoiMailEtudiant() {
        return dateEnvoiMailEtudiant;
    }

    public void setDateEnvoiMailEtudiant(Date dateEnvoiMailEtudiant) {
        this.dateEnvoiMailEtudiant = dateEnvoiMailEtudiant;
    }

    public Boolean getEnvoiMailTuteurPedago() {
        return envoiMailTuteurPedago;
    }

    public void setEnvoiMailTuteurPedago(Boolean envoiMailTuteurPedago) {
        this.envoiMailTuteurPedago = envoiMailTuteurPedago;
    }

    public Date getDateEnvoiMailTuteurPedago() {
        return dateEnvoiMailTuteurPedago;
    }

    public void setDateEnvoiMailTuteurPedago(Date dateEnvoiMailTuteurPedago) {
        this.dateEnvoiMailTuteurPedago = dateEnvoiMailTuteurPedago;
    }

    public Boolean getEnvoiMailTuteurPro() {
        return envoiMailTuteurPro;
    }

    public void setEnvoiMailTuteurPro(Boolean envoiMailTuteurPro) {
        this.envoiMailTuteurPro = envoiMailTuteurPro;
    }

    public Date getDateEnvoiMailTuteurPro() {
        return dateEnvoiMailTuteurPro;
    }

    public void setDateEnvoiMailTuteurPro(Date dateEnvoiMailTuteurPro) {
        this.dateEnvoiMailTuteurPro = dateEnvoiMailTuteurPro;
    }

    public String getNbConges() {
        return nbConges;
    }

    public void setNbConges(String nbConges) {
        this.nbConges = nbConges;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public UniteDuree getUniteDureeGratification() {
        return uniteDureeGratification;
    }

    public void setUniteDureeGratification(UniteDuree uniteDureeGratification) {
        this.uniteDureeGratification = uniteDureeGratification;
    }

    public String getMonnaieGratification() {
        return monnaieGratification;
    }

    public void setMonnaieGratification(String monnaieGratification) {
        this.monnaieGratification = monnaieGratification;
    }

    public String getVolumeHoraireFormation() {
        return volumeHoraireFormation;
    }

    public void setVolumeHoraireFormation(String volumeHoraireFormation) {
        this.volumeHoraireFormation = volumeHoraireFormation;
    }

    public String getTypePresence() {
        return typePresence;
    }

    public void setTypePresence(String typePresence) {
        this.typePresence = typePresence;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }
}