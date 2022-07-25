package org.esup_portail.esup_stage.service.impression.context;

import org.esup_portail.esup_stage.model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ImpressionContext {
    private ConventionContext convention = new ConventionContext();
    private CentreGestionContext centreGestion = new CentreGestionContext();
    private ContactContext contact = new ContactContext();
    private EnseignantContext enseignant = new EnseignantContext();
    private EtudiantContext etudiant = new EtudiantContext();
    private ServiceContext service = new ServiceContext();
    private SignataireContext signataire = new SignataireContext();
    private StructureContext structure = new StructureContext();
    private AvenantContext avenant = new AvenantContext();

    public ImpressionContext() {
    }

    public ImpressionContext(Convention convention, Avenant avenant, CentreGestion centreEtablissement) {
        if (convention != null) {
            this.convention = new ConventionContext(convention);
            this.centreGestion = new CentreGestionContext(convention.getCentreGestion(), centreEtablissement);
            this.contact = new ContactContext(convention.getContact());
            this.enseignant = new EnseignantContext(convention.getEnseignant());
            this.etudiant = new EtudiantContext(convention.getEtudiant());
            this.service = new ServiceContext(convention.getService());
            this.signataire = new SignataireContext(convention.getSignataire());
            this.structure = new StructureContext(convention.getStructure());
        }
        if (avenant != null) {
            this.avenant = new AvenantContext(avenant);
        }
    }

    public ConventionContext getConvention() {
        return convention;
    }

    public void setConvention(ConventionContext convention) {
        this.convention = convention;
    }

    public CentreGestionContext getCentreGestion() {
        return centreGestion;
    }

    public void setCentreGestion(CentreGestionContext centreGestion) {
        this.centreGestion = centreGestion;
    }

    public ContactContext getContact() {
        return contact;
    }

    public void setContact(ContactContext contact) {
        this.contact = contact;
    }

    public EnseignantContext getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(EnseignantContext enseignant) {
        this.enseignant = enseignant;
    }

    public EtudiantContext getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(EtudiantContext etudiant) {
        this.etudiant = etudiant;
    }

    public ServiceContext getService() {
        return service;
    }

    public void setService(ServiceContext service) {
        this.service = service;
    }

    public SignataireContext getSignataire() {
        return signataire;
    }

    public void setSignataire(SignataireContext signataire) {
        this.signataire = signataire;
    }

    public StructureContext getStructure() {
        return structure;
    }

    public void setStructure(StructureContext structure) {
        this.structure = structure;
    }

    public AvenantContext getAvenant() {
        return avenant;
    }

    public void setAvenant(AvenantContext avenant) {
        this.avenant = avenant;
    }

    public static class ConventionContext {
        private String id;
        private String adresseEtabRef;
        private String adresseEtudiant;
        private String annee;
        private String assuranceLibelle;
        private String avantagesNature;
        private String codeCaisse;
        private String codeCursusLMD;
        private String codeDepartement;
        private String codePostalEtudiant;
        private String commentaireDureeTravail;
        private String courrielPersoEtudiant;
        private String creditECTS;
        private String dateDebutInterruption;
        private String dateDebutStage;
        private String dateFinInterruption;
        private String dateFinStage;
        private String details;
        private String dureeStageHeure;
        private String dureeStageHeurePeriode;
        private String dureeStage;
        private String etapeLibelle;
        private String fonctionsEtTaches;
        private String interruptionStage;
        private String libelleCPAM;
        private String libelleFinalite;
        private String modeEncadreSuivi;
        private String modeValidationStageLibelle;
        private String modeVersGratificationLibelle;
        private String montantGratification;
        private String natureTravailLibelle;
        private String nbHeuresHebdo;
        private String nbJoursHebdo;
        private String nomEtabRef;
        private String nomSignataireComposante;
        private String origineStageLibelle;
        private String paysEtudiant;
        private String qualiteSignataire;
        private String sujetStage;
        private String telEtudiant;
        private String telPortableEtudiant;
        private String tempsTravailLibelle;
        private String themeLibelle;
        private String travailNuitFerie;
        private String ufrLibelle;
        private String uniteGratificationLibelle;
        private String villeEtudiant;
        private String volumeHoraireFormation;
        private String competences;

        public ConventionContext() { }

        public ConventionContext(Convention convention) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            this.id = String.valueOf(convention.getId());
            this.adresseEtabRef = convention.getAdresseEtabRef();
            this.adresseEtudiant = convention.getAdresseEtudiant();
            this.annee = convention.getAnnee();
            this.assuranceLibelle = convention.getAssurance() != null ? convention.getAssurance().getLibelle() : null;
            this.avantagesNature = convention.getAvantagesNature();
            this.codeCaisse = convention.getCodeCaisse();
            this.codeCursusLMD = convention.getCodeCursusLMD();
            this.codeDepartement = convention.getCodeDepartement();
            this.codePostalEtudiant = convention.getCodePostalEtudiant();
            this.commentaireDureeTravail = convention.getCommentaireDureeTravail();
            this.courrielPersoEtudiant = convention.getCourrielPersoEtudiant();
            this.creditECTS = String.valueOf(convention.getCreditECTS());
            this.dateDebutInterruption = convention.getDateDebutInterruption() != null ? df.format(convention.getDateDebutInterruption()) : null;
            this.dateDebutStage = convention.getDateDebutStage() != null ? df.format(convention.getDateDebutStage()) : null;
            this.dateFinInterruption = convention.getDateFinInterruption() != null ? df.format(convention.getDateFinInterruption()) : null;
            this.dateFinStage = convention.getDateFinStage() != null ? df.format(convention.getDateFinStage()) : null;
            this.details = convention.getDetails();
            this.dureeStageHeure = convention.getDureeExceptionnelle();
            this.dureeStage = String.valueOf(convention.getDureeStage());
            this.etapeLibelle = convention.getEtape() != null ? convention.getEtape().getLibelle() : null;
            this.fonctionsEtTaches = convention.getFonctionsEtTaches();
            this.interruptionStage = (convention.getInterruptionStage() != null && convention.getInterruptionStage())  ? "Oui" : "Non";
            this.libelleCPAM = convention.getLibelleCPAM();
            this.libelleFinalite = convention.getLibelleFinalite();
            this.modeEncadreSuivi = convention.getModeEncadreSuivi();
            this.modeValidationStageLibelle = convention.getNomenclature() != null ? convention.getNomenclature().getModeValidationStage() : null;
            this.modeVersGratificationLibelle = convention.getNomenclature() != null ? convention.getNomenclature().getModeVersGratification() : null;
            this.montantGratification = convention.getMontantGratification();
            this.natureTravailLibelle = convention.getNomenclature() != null ? convention.getNomenclature().getNatureTravail() : null;
            this.nbHeuresHebdo = convention.getNbHeuresHebdo();
            this.nbJoursHebdo = convention.getNbJoursHebdo() != null ? convention.getNbJoursHebdo().getValue() : null;
            this.nomEtabRef = convention.getNomEtabRef();
            this.nomSignataireComposante = convention.getNomSignataireComposante();
            this.origineStageLibelle = convention.getNomenclature() != null ? convention.getNomenclature().getOrigineStage() : null;
            this.paysEtudiant = convention.getPaysEtudiant();
            this.qualiteSignataire = convention.getQualiteSignataire();
            this.sujetStage = convention.getSujetStage();
            this.telEtudiant = convention.getTelEtudiant();
            this.telPortableEtudiant = convention.getTelPortableEtudiant();
            this.tempsTravailLibelle = convention.getNomenclature() != null ? convention.getNomenclature().getTempsTravail() : null;
            this.themeLibelle = convention.getNomenclature() != null ? convention.getNomenclature().getTheme() : null;
            this.travailNuitFerie = convention.getTravailNuitFerie();
            this.ufrLibelle = convention.getUfr() != null ? convention.getUfr().getLibelle() : null;
            this.uniteGratificationLibelle = convention.getNomenclature() != null ? convention.getNomenclature().getUniteGratification() : null;
            this.villeEtudiant = convention.getVilleEtudiant();
            this.volumeHoraireFormation = convention.getVolumeHoraireFormation();
            this.dureeStageHeurePeriode = convention.getDureeExceptionnellePeriode();
            this.competences = convention.getCompetences();
        }

        public String getId() {
            return id != null ? id : "";
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAdresseEtabRef() {
            return adresseEtabRef != null ? adresseEtabRef : "";
        }

        public void setAdresseEtabRef(String adresseEtabRef) {
            this.adresseEtabRef = adresseEtabRef;
        }

        public String getAdresseEtudiant() {
            return adresseEtudiant != null ? adresseEtudiant : "";
        }

        public void setAdresseEtudiant(String adresseEtudiant) {
            this.adresseEtudiant = adresseEtudiant;
        }

        public String getAnnee() {
            return annee != null ? annee : "";
        }

        public void setAnnee(String annee) {
            this.annee = annee;
        }

        public String getAssuranceLibelle() {
            return assuranceLibelle != null ? assuranceLibelle : "";
        }

        public void setAssuranceLibelle(String assuranceLibelle) {
            this.assuranceLibelle = assuranceLibelle;
        }

        public String getAvantagesNature() {
            return avantagesNature != null ? avantagesNature : "";
        }

        public void setAvantagesNature(String avantagesNature) {
            this.avantagesNature = avantagesNature;
        }

        public String getCodeCaisse() {
            return codeCaisse != null ? codeCaisse : "";
        }

        public void setCodeCaisse(String codeCaisse) {
            this.codeCaisse = codeCaisse;
        }

        public String getCodeCursusLMD() {
            return codeCursusLMD != null ? codeCursusLMD : "";
        }

        public void setCodeCursusLMD(String codeCursusLMD) {
            this.codeCursusLMD = codeCursusLMD;
        }

        public String getCodeDepartement() {
            return codeDepartement != null ? codeDepartement : "";
        }

        public void setCodeDepartement(String codeDepartement) {
            this.codeDepartement = codeDepartement;
        }

        public String getCodePostalEtudiant() {
            return codePostalEtudiant != null ? codePostalEtudiant : "";
        }

        public void setCodePostalEtudiant(String codePostalEtudiant) {
            this.codePostalEtudiant = codePostalEtudiant;
        }

        public String getCommentaireDureeTravail() {
            return commentaireDureeTravail != null ? commentaireDureeTravail : "";
        }

        public void setCommentaireDureeTravail(String commentaireDureeTravail) {
            this.commentaireDureeTravail = commentaireDureeTravail;
        }

        public String getCourrielPersoEtudiant() {
            return courrielPersoEtudiant != null ? courrielPersoEtudiant : "";
        }

        public void setCourrielPersoEtudiant(String courrielPersoEtudiant) {
            this.courrielPersoEtudiant = courrielPersoEtudiant;
        }

        public String getCreditECTS() {
            return creditECTS != null ? creditECTS : "";
        }

        public void setCreditECTS(String creditECTS) {
            this.creditECTS = creditECTS;
        }

        public String getDateDebutInterruption() {
            return dateDebutInterruption != null ? dateDebutInterruption : "";
        }

        public void setDateDebutInterruption(String dateDebutInterruption) {
            this.dateDebutInterruption = dateDebutInterruption;
        }

        public String getDateDebutStage() {
            return dateDebutStage != null ? dateDebutStage : "";
        }

        public void setDateDebutStage(String dateDebutStage) {
            this.dateDebutStage = dateDebutStage;
        }

        public String getDateFinInterruption() {
            return dateFinInterruption != null ? dateFinInterruption : "";
        }

        public void setDateFinInterruption(String dateFinInterruption) {
            this.dateFinInterruption = dateFinInterruption;
        }

        public String getDateFinStage() {
            return dateFinStage != null ? dateFinStage : "";
        }

        public void setDateFinStage(String dateFinStage) {
            this.dateFinStage = dateFinStage;
        }

        public String getDetails() {
            return details != null ? details : "";
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getDureeStageHeure() {
            return dureeStageHeure != null ? dureeStageHeure : "";
        }

        public void setDureeStageHeure(String dureeStageHeure) {
            this.dureeStageHeure = dureeStageHeure;
        }

        public String getDureeStageHeurePeriode() {
            return dureeStageHeurePeriode != null ? dureeStageHeurePeriode : "";
        }

        public void setDureeStageHeurePeriode(String dureeStageHeurePeriode) {
            this.dureeStageHeurePeriode = dureeStageHeurePeriode;
        }

        public String getDureeStage() {
            return dureeStage != null ? dureeStage : "";
        }

        public void setDureeStage(String dureeStage) {
            this.dureeStage = dureeStage;
        }

        public String getEtapeLibelle() {
            return etapeLibelle != null ? etapeLibelle : "";
        }

        public void setEtapeLibelle(String etapeLibelle) {
            this.etapeLibelle = etapeLibelle;
        }

        public String getFonctionsEtTaches() {
            return fonctionsEtTaches != null ? fonctionsEtTaches : "";
        }

        public void setFonctionsEtTaches(String fonctionsEtTaches) {
            this.fonctionsEtTaches = fonctionsEtTaches;
        }

        public String getInterruptionStage() {
            return interruptionStage != null ? interruptionStage : "";
        }

        public void setInterruptionStage(String interruptionStage) {
            this.interruptionStage = interruptionStage;
        }

        public String getLibelleCPAM() {
            return libelleCPAM != null ? libelleCPAM : "";
        }

        public void setLibelleCPAM(String libelleCPAM) {
            this.libelleCPAM = libelleCPAM;
        }

        public String getLibelleFinalite() {
            return libelleFinalite != null ? libelleFinalite : "";
        }

        public void setLibelleFinalite(String libelleFinalite) {
            this.libelleFinalite = libelleFinalite;
        }

        public String getModeEncadreSuivi() {
            return modeEncadreSuivi != null ? modeEncadreSuivi : "";
        }

        public void setModeEncadreSuivi(String modeEncadreSuivi) {
            this.modeEncadreSuivi = modeEncadreSuivi;
        }

        public String getModeValidationStageLibelle() {
            return modeValidationStageLibelle != null ? modeValidationStageLibelle : "";
        }

        public void setModeValidationStageLibelle(String modeValidationStageLibelle) {
            this.modeValidationStageLibelle = modeValidationStageLibelle;
        }

        public String getModeVersGratificationLibelle() {
            return modeVersGratificationLibelle != null ? modeVersGratificationLibelle : "";
        }

        public void setModeVersGratificationLibelle(String modeVersGratificationLibelle) {
            this.modeVersGratificationLibelle = modeVersGratificationLibelle;
        }

        public String getMontantGratification() {
            return montantGratification != null ? montantGratification : "";
        }

        public void setMontantGratification(String montantGratification) {
            this.montantGratification = montantGratification;
        }

        public String getNatureTravailLibelle() {
            return natureTravailLibelle != null ? natureTravailLibelle : "";
        }

        public void setNatureTravailLibelle(String natureTravailLibelle) {
            this.natureTravailLibelle = natureTravailLibelle;
        }

        public String getNbHeuresHebdo() {
            return nbHeuresHebdo != null ? nbHeuresHebdo : "";
        }

        public void setNbHeuresHebdo(String nbHeuresHebdo) {
            this.nbHeuresHebdo = nbHeuresHebdo;
        }

        public String getNbJoursHebdo() {
            return nbJoursHebdo != null ? nbJoursHebdo : "";
        }

        public void setNbJoursHebdo(String nbJoursHebdo) {
            this.nbJoursHebdo = nbJoursHebdo;
        }

        public String getNomEtabRef() {
            return nomEtabRef != null ? nomEtabRef : "";
        }

        public void setNomEtabRef(String nomEtabRef) {
            this.nomEtabRef = nomEtabRef;
        }

        public String getNomSignataireComposante() {
            return nomSignataireComposante != null ? nomSignataireComposante : "";
        }

        public void setNomSignataireComposante(String nomSignataireComposante) {
            this.nomSignataireComposante = nomSignataireComposante;
        }

        public String getOrigineStageLibelle() {
            return origineStageLibelle != null ? origineStageLibelle : "";
        }

        public void setOrigineStageLibelle(String origineStageLibelle) {
            this.origineStageLibelle = origineStageLibelle;
        }

        public String getPaysEtudiant() {
            return paysEtudiant != null ? paysEtudiant : "";
        }

        public void setPaysEtudiant(String paysEtudiant) {
            this.paysEtudiant = paysEtudiant;
        }

        public String getQualiteSignataire() {
            return qualiteSignataire != null ? qualiteSignataire : "";
        }

        public void setQualiteSignataire(String qualiteSignataire) {
            this.qualiteSignataire = qualiteSignataire;
        }

        public String getSujetStage() {
            return sujetStage != null ? sujetStage : "";
        }

        public void setSujetStage(String sujetStage) {
            this.sujetStage = sujetStage;
        }

        public String getTelEtudiant() {
            return telEtudiant != null ? telEtudiant : "";
        }

        public void setTelEtudiant(String telEtudiant) {
            this.telEtudiant = telEtudiant;
        }

        public String getTelPortableEtudiant() {
            return telPortableEtudiant != null ? telPortableEtudiant : "";
        }

        public void setTelPortableEtudiant(String telPortableEtudiant) {
            this.telPortableEtudiant = telPortableEtudiant;
        }

        public String getTempsTravailLibelle() {
            return tempsTravailLibelle != null ? tempsTravailLibelle : "";
        }

        public void setTempsTravailLibelle(String tempsTravailLibelle) {
            this.tempsTravailLibelle = tempsTravailLibelle;
        }

        public String getThemeLibelle() {
            return themeLibelle != null ? themeLibelle : "";
        }

        public void setThemeLibelle(String themeLibelle) {
            this.themeLibelle = themeLibelle;
        }

        public String getTravailNuitFerie() {
            return travailNuitFerie != null ? travailNuitFerie : "";
        }

        public void setTravailNuitFerie(String travailNuitFerie) {
            this.travailNuitFerie = travailNuitFerie;
        }

        public String getUfrLibelle() {
            return ufrLibelle != null ? ufrLibelle : "";
        }

        public void setUfrLibelle(String ufrLibelle) {
            this.ufrLibelle = ufrLibelle;
        }

        public String getUniteGratificationLibelle() {
            return uniteGratificationLibelle != null ? uniteGratificationLibelle : "";
        }

        public void setUniteGratificationLibelle(String uniteGratificationLibelle) {
            this.uniteGratificationLibelle = uniteGratificationLibelle;
        }

        public String getVilleEtudiant() {
            return villeEtudiant != null ? villeEtudiant : "";
        }

        public void setVilleEtudiant(String villeEtudiant) {
            this.villeEtudiant = villeEtudiant;
        }

        public String getVolumeHoraireFormation() {
            return volumeHoraireFormation != null ? volumeHoraireFormation : "";
        }

        public void setVolumeHoraireFormation(String volumeHoraireFormation) {
            this.volumeHoraireFormation = volumeHoraireFormation;
        }

        public String getCompetences() {
            return competences != null ? competences : "";
        }

        public void setCompetences(String competences) {
            this.competences = competences;
        }
    }

    public static class CentreGestionContext {
        private String adresse;
        private String codePostal;
        private String codeUniversite;
        private String commune;
        private String mail;
        private String nomCentre;
        private String nomViseur;
        private String prenomViseur;
        private String qualiteViseur;
        private String telephone;
        private String voie;
        private String prenomPresidentEtab;
        private String nomPresidentEtab;
        private String qualitePresidentEtab;

        public CentreGestionContext() { }

        public CentreGestionContext(CentreGestion centreGestion, CentreGestion centreEtablissement) {
            this.adresse = centreGestion.getAdresse();
            this.codePostal = centreGestion.getCodePostal();
            this.codeUniversite = centreGestion.getCodeUniversite();
            this.commune = centreGestion.getCommune();
            this.mail = centreGestion.getMail();
            this.nomCentre = centreGestion.getNomCentre();
            this.nomViseur = centreGestion.getNomViseur();
            this.prenomViseur = centreGestion.getPrenomViseur();
            this.qualiteViseur = centreGestion.getQualiteViseur();
            this.telephone = centreGestion.getTelephone();
            this.voie = centreGestion.getVoie();
            if (centreEtablissement != null) {
                this.prenomPresidentEtab = centreEtablissement.getPrenomViseur();
                this.nomPresidentEtab = centreEtablissement.getNomViseur();
                this.qualitePresidentEtab = centreEtablissement.getQualiteViseur();
            }
        }

        public String getAdresse() {
            return adresse != null ? adresse : "";
        }

        public void setAdresse(String adresse) {
            this.adresse = adresse;
        }

        public String getCodePostal() {
            return codePostal != null ? codePostal : "";
        }

        public void setCodePostal(String codePostal) {
            this.codePostal = codePostal;
        }

        public String getCodeUniversite() {
            return codeUniversite != null ? codeUniversite : "";
        }

        public void setCodeUniversite(String codeUniversite) {
            this.codeUniversite = codeUniversite;
        }

        public String getCommune() {
            return commune != null ? commune : "";
        }

        public void setCommune(String commune) {
            this.commune = commune;
        }

        public String getMail() {
            return mail != null ? mail : "";
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getNomCentre() {
            return nomCentre != null ? nomCentre : "";
        }

        public void setNomCentre(String nomCentre) {
            this.nomCentre = nomCentre;
        }

        public String getNomViseur() {
            return nomViseur != null ? nomViseur : "";
        }

        public void setNomViseur(String nomViseur) {
            this.nomViseur = nomViseur;
        }

        public String getPrenomViseur() {
            return prenomViseur != null ? prenomViseur : "";
        }

        public void setPrenomViseur(String prenomViseur) {
            this.prenomViseur = prenomViseur;
        }

        public String getQualiteViseur() {
            return qualiteViseur != null ? qualiteViseur : "";
        }

        public void setQualiteViseur(String qualiteViseur) {
            this.qualiteViseur = qualiteViseur;
        }

        public String getTelephone() {
            return telephone != null ? telephone : "";
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getVoie() {
            return voie != null ? voie : "";
        }

        public void setVoie(String voie) {
            this.voie = voie;
        }

        public String getPrenomPresidentEtab() {
            return prenomPresidentEtab != null ? prenomPresidentEtab : "";
        }

        public void setPrenomPresidentEtab(String prenomPresidentEtab) {
            this.prenomPresidentEtab = prenomPresidentEtab;
        }

        public String getNomPresidentEtab() {
            return nomPresidentEtab != null ? nomPresidentEtab : "";
        }

        public void setNomPresidentEtab(String nomPresidentEtab) {
            this.nomPresidentEtab = nomPresidentEtab;
        }

        public String getQualitePresidentEtab() {
            return qualitePresidentEtab != null ? qualitePresidentEtab : "";
        }

        public void setQualitePresidentEtab(String qualitePresidentEtab) {
            this.qualitePresidentEtab = qualitePresidentEtab;
        }
    }

    public static class ContactContext {
        private String civiliteLibelle;
        private String fonction;
        private String mail;
        private String nom;
        private String prenom;
        private String tel;

        public ContactContext() { }

        public ContactContext(Contact contact) {
            this.civiliteLibelle = contact.getCivilite() != null ? contact.getCivilite().getLibelle() : null;
            this.fonction = contact.getFonction();
            this.mail = contact.getMail();
            this.nom = contact.getNom();
            this.prenom = contact.getPrenom();
            this.tel = contact.getTel();
        }

        public String getCiviliteLibelle() {
            return civiliteLibelle != null ? civiliteLibelle : "";
        }

        public void setCiviliteLibelle(String civiliteLibelle) {
            civiliteLibelle = civiliteLibelle;
        }

        public String getFonction() {
            return fonction != null ? fonction : "";
        }

        public void setFonction(String fonction) {
            this.fonction = fonction;
        }

        public String getMail() {
            return mail != null ? mail : "";
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getNom() {
            return nom != null ? nom : "";
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom != null ? prenom : "";
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getTel() {
            return tel != null ? tel : "";
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }

    public static class EnseignantContext {
        private String affectationLibelle;
        private String bureau;
        private String nom;
        private String prenom;
        private String tel;
        private String mail;

        public EnseignantContext() {
        }

        public EnseignantContext(Enseignant enseignant) {
            this.affectationLibelle = enseignant.getAffectation() != null ? enseignant.getAffectation().getLibelle() : null;
            this.bureau = enseignant.getBureau();
            this.nom = enseignant.getNom();
            this.prenom = enseignant.getPrenom();
            this.tel = enseignant.getTel();
            this.mail = enseignant.getMail();
        }

        public String getAffectationLibelle() {
            return affectationLibelle != null ? affectationLibelle : "";
        }

        public void setAffectationLibelle(String affectationLibelle) {
            this.affectationLibelle = affectationLibelle;
        }

        public String getBureau() {
            return bureau != null ? bureau : "";
        }

        public void setBureau(String bureau) {
            this.bureau = bureau;
        }

        public String getNom() {
            return nom != null ? nom : "";
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom != null ? prenom : "";
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getTel() {
            return tel != null ? tel : "";
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getMail() {
            return mail != null ? mail : "";
        }

        public void setMail(String mail) {
            this.mail = mail;
        }
    }

    public static class EtudiantContext {
        private String codeSexe;
        private String dateNais;
        private String identEtudiant;
        private String mail;
        private String nom;
        private String numEtudiant;
        private String prenom;

        public EtudiantContext() {
        }

        public EtudiantContext(Etudiant etudiant) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            this.codeSexe = etudiant.getCodeSexe();
            this.dateNais = etudiant.getDateNais() != null ? df.format(etudiant.getDateNais()) : null;
            this.identEtudiant = etudiant.getIdentEtudiant();
            this.mail = etudiant.getMail();
            this.nom = etudiant.getNom();
            this.numEtudiant = etudiant.getNumEtudiant();
            this.prenom = etudiant.getPrenom();
        }

        public String getCodeSexe() {
            return codeSexe != null ? codeSexe : "";
        }

        public void setCodeSexe(String codeSexe) {
            this.codeSexe = codeSexe;
        }

        public String getDateNais() {
            return dateNais != null ? dateNais : "";
        }

        public void setDateNais(String dateNais) {
            this.dateNais = dateNais;
        }

        public String getIdentEtudiant() {
            return identEtudiant != null ? identEtudiant : "";
        }

        public void setIdentEtudiant(String identEtudiant) {
            this.identEtudiant = identEtudiant;
        }

        public String getMail() {
            return mail != null ? mail : "";
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getNom() {
            return nom != null ? nom : "";
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getNumEtudiant() {
            return numEtudiant != null ? numEtudiant : "";
        }

        public void setNumEtudiant(String numEtudiant) {
            this.numEtudiant = numEtudiant;
        }

        public String getPrenom() {
            return prenom != null ? prenom : "";
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }
    }

    public static class ServiceContext {
        private String codePostal;
        private String commune;
        private String nom;
        private String paysLibelle;
        private String voie;

        public ServiceContext() {
        }

        public ServiceContext(Service service) {
            this.codePostal = service.getCodePostal();
            this.commune = service.getCommune();
            this.nom = service.getNom();
            this.paysLibelle = service.getPays() != null ? service.getPays().getLib() : null;
            this.voie = service.getVoie();
        }

        public String getCodePostal() {
            return codePostal != null ? codePostal : "";
        }

        public void setCodePostal(String codePostal) {
            this.codePostal = codePostal;
        }

        public String getCommune() {
            return commune != null ? commune : "";
        }

        public void setCommune(String commune) {
            this.commune = commune;
        }

        public String getNom() {
            return nom != null ? nom : "";
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPaysLibelle() {
            return paysLibelle != null ? paysLibelle : "";
        }

        public void setPaysLibelle(String paysLibelle) {
            this.paysLibelle = paysLibelle;
        }

        public String getVoie() {
            return voie != null ? voie : "";
        }

        public void setVoie(String voie) {
            this.voie = voie;
        }
    }

    public static class SignataireContext {
        private String civiliteLibelle;
        private String fonction;
        private String mail;
        private String nom;
        private String prenom;
        private String tel;

        public SignataireContext() {
        }

        public SignataireContext(Contact signataire) {
            this.civiliteLibelle = signataire.getCivilite() != null ? signataire.getCivilite().getLibelle() : null;
            this.fonction = signataire.getFonction();
            this.mail = signataire.getMail();
            this.nom = signataire.getNom();
            this.prenom = signataire.getPrenom();
            this.tel = signataire.getTel();
        }

        public String getCiviliteLibelle() {
            return civiliteLibelle != null ? civiliteLibelle : "";
        }

        public void setCiviliteLibelle(String civiliteLibelle) {
            this.civiliteLibelle = civiliteLibelle;
        }

        public String getFonction() {
            return fonction != null ? fonction : "";
        }

        public void setFonction(String fonction) {
            this.fonction = fonction;
        }

        public String getMail() {
            return mail != null ? mail : "";
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getNom() {
            return nom != null ? nom : "";
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom != null ? prenom : "";
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getTel() {
            return tel != null ? tel : "";
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }

    public static class StructureContext {
        private String activitePrincipale;
        private String codePostal;
        private String commune;
        private String effectifLibelle;
        private String mail;
        private String numeroSiret;
        private String paysLibelle;
        private String raisonSociale;
        private String statutJuridiqueLibelle;
        private String telephone;
        private String typeStructureLibelle;
        private String voie;

        public StructureContext() {
        }

        public StructureContext(Structure structure) {
            this.activitePrincipale = structure.getActivitePrincipale();
            this.codePostal = structure.getCodePostal();
            this.commune = structure.getCommune();
            this.effectifLibelle = structure.getEffectif() != null ? structure.getEffectif().getLibelle() : null;
            this.mail = structure.getMail();
            this.numeroSiret = structure.getNumeroSiret();
            this.paysLibelle = structure.getPays() != null ? structure.getPays().getLib() : null;
            this.raisonSociale = structure.getRaisonSociale();
            this.statutJuridiqueLibelle = structure.getStatutJuridique() != null ? structure.getStatutJuridique().getLibelle() : null;
            this.telephone = structure.getTelephone();
            this.typeStructureLibelle = structure.getTypeStructure() != null ? structure.getTypeStructure().getLibelle() : null;
            this.voie = structure.getVoie();
        }

        public String getActivitePrincipale() {
            return activitePrincipale != null ? activitePrincipale : "";
        }

        public void setActivitePrincipale(String activitePrincipale) {
            this.activitePrincipale = activitePrincipale;
        }

        public String getCodePostal() {
            return codePostal != null ? codePostal : "";
        }

        public void setCodePostal(String codePostal) {
            this.codePostal = codePostal;
        }

        public String getCommune() {
            return commune != null ? commune : "";
        }

        public void setCommune(String commune) {
            this.commune = commune;
        }

        public String getEffectifLibelle() {
            return effectifLibelle != null ? effectifLibelle : "";
        }

        public void setEffectifLibelle(String effectifLibelle) {
            this.effectifLibelle = effectifLibelle;
        }

        public String getMail() {
            return mail != null ? mail : "";
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getNumeroSiret() {
            return numeroSiret != null ? numeroSiret : "";
        }

        public void setNumeroSiret(String numeroSiret) {
            this.numeroSiret = numeroSiret;
        }

        public String getPaysLibelle() {
            return paysLibelle != null ? paysLibelle : "";
        }

        public void setPaysLibelle(String paysLibelle) {
            this.paysLibelle = paysLibelle;
        }

        public String getRaisonSociale() {
            return raisonSociale != null ? raisonSociale : "";
        }

        public void setRaisonSociale(String raisonSociale) {
            this.raisonSociale = raisonSociale;
        }

        public String getStatutJuridiqueLibelle() {
            return statutJuridiqueLibelle != null ? statutJuridiqueLibelle : "";
        }

        public void setStatutJuridiqueLibelle(String statutJuridiqueLibelle) {
            this.statutJuridiqueLibelle = statutJuridiqueLibelle;
        }

        public String getTelephone() {
            return telephone != null ? telephone : "";
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getTypeStructureLibelle() {
            return typeStructureLibelle != null ? typeStructureLibelle : "";
        }

        public void setTypeStructureLibelle(String typeStructureLibelle) {
            this.typeStructureLibelle = typeStructureLibelle;
        }

        public String getVoie() {
            return voie != null ? voie : "";
        }

        public void setVoie(String voie) {
            this.voie = voie;
        }
    }

    public static class AvenantContext {
        private String id;
        private String sujetStage;
        private String motifAvenant;
        private String dateDebutStage;
        private String dateFinStage;

        public AvenantContext() {
        }

        public AvenantContext(Avenant avenant) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            this.id = String.valueOf(avenant.getId());
            this.sujetStage = avenant.getSujetStage();
            this.motifAvenant = avenant.getMotifAvenant();
            this.dateDebutStage = avenant.getDateDebutStage() != null ? df.format(avenant.getDateDebutStage()) : "";
            this.dateFinStage = avenant.getDateFinStage() != null ? df.format(avenant.getDateFinStage()) : "";
        }

        public String getId() {
            return id != null ? id : "";
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSujetStage() {
            return sujetStage != null ? sujetStage : "";
        }

        public void setSujetStage(String sujetStage) {
            this.sujetStage = sujetStage;
        }

        public String getMotifAvenant() {
            return motifAvenant != null ? motifAvenant : "";
        }

        public void setMotifAvenant(String motifAvenant) {
            this.motifAvenant = motifAvenant;
        }

        public String getDateDebutStage() {
            return dateDebutStage != null ? dateDebutStage : "";
        }

        public void setDateDebutStage(String dateDebutStage) {
            this.dateDebutStage = dateDebutStage;
        }

        public String getDateFinStage() {
            return dateFinStage != null ? dateFinStage : "";
        }

        public void setDateFinStage(String dateFinStage) {
            this.dateFinStage = dateFinStage;
        }
    }
}
