package org.esup_portail.esup_stage.service.signature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.esup_portail.esup_stage.bootstrap.ApplicationBootstrap;
import org.esup_portail.esup_stage.docaposte.DocaposteClient;
import org.esup_portail.esup_stage.dto.IdsListDto;
import org.esup_portail.esup_stage.dto.MetadataDto;
import org.esup_portail.esup_stage.dto.MetadataSignataireDto;
import org.esup_portail.esup_stage.dto.ResponseDto;
import org.esup_portail.esup_stage.enums.AppSignatureEnum;
import org.esup_portail.esup_stage.enums.FolderEnum;
import org.esup_portail.esup_stage.exception.AppException;
import org.esup_portail.esup_stage.model.*;
import org.esup_portail.esup_stage.repository.AvenantJpaRepository;
import org.esup_portail.esup_stage.repository.CentreGestionJpaRepository;
import org.esup_portail.esup_stage.repository.ConventionJpaRepository;
import org.esup_portail.esup_stage.service.ConventionService;
import org.esup_portail.esup_stage.service.impression.ImpressionService;
import org.esup_portail.esup_stage.service.signature.model.Historique;
import org.esup_portail.esup_stage.webhook.esupsignature.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SignatureService {

    private static final Logger logger	= LogManager.getLogger(SignatureService.class);

    @Autowired
    ApplicationBootstrap applicationBootstrap;

    @Autowired
    ConventionJpaRepository conventionJpaRepository;

    @Autowired
    AvenantJpaRepository avenantJpaRepository;

    @Autowired
    CentreGestionJpaRepository centreGestionJpaRepository;

    @Autowired
    ConventionService conventionService;

    @Autowired
    ImpressionService impressionService;

    @Autowired
    DocaposteClient docaposteClient;

    @Autowired
    WebhookService webhookService;

    private final WebClient webClient;

    public SignatureService(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public byte[] getPublicPdf(Convention convention, Avenant avenant) {
        if (convention.getNomEtabRef() == null || convention.getAdresseEtabRef() == null) {
            CentreGestion centreGestionEtab = centreGestionJpaRepository.getCentreEtablissement();
            if (centreGestionEtab == null) {
                throw new AppException(HttpStatus.NOT_FOUND, "Centre de gestion de type établissement non trouvé");
            }
            convention.setNomEtabRef(centreGestionEtab.getNomCentre());
            convention.setAdresseEtabRef(centreGestionEtab.getAdresseComplete());
            conventionJpaRepository.saveAndFlush(convention);
        }
        ByteArrayOutputStream ou = new ByteArrayOutputStream();
        impressionService.generateConventionAvenantPDF(convention, avenant, ou, false);

        return ou.toByteArray();
    }

    public MetadataDto getPublicMetadata(Convention convention) {
        return getPublicMetadata(convention, null);
    }

    public MetadataDto getPublicMetadata(Convention convention, Integer idAvenant) {
        MetadataDto metadata = new MetadataDto();
        metadata.setTitle("Convention_" + convention.getId() + "_" + convention.getEtudiant().getNom() + "_" + convention.getEtudiant().getPrenom());
        if (idAvenant != null) {
            metadata.setTitle("Avenant_" + idAvenant + "_" + convention.getEtudiant().getNom() + "_" + convention.getEtudiant().getPrenom());
        }
        metadata.setCompanyname(convention.getNomEtabRef());
        metadata.setSchool(convention.getEtape().getLibelle());
        metadata.setWorkflowId(convention.getCentreGestion().getCircuitSignature());
        List<MetadataSignataireDto> signataires = new ArrayList<>();

        convention.getCentreGestion().getSignataires().forEach(s -> {
            MetadataSignataireDto signataireDto = new MetadataSignataireDto();
            String phone = "";
            switch (s.getId().getSignataire()) {
                case etudiant:
                    Etudiant etudiant = convention.getEtudiant();
                    signataireDto.setName(etudiant.getNom());
                    signataireDto.setGivenname(etudiant.getPrenom());
                    signataireDto.setMail(impressionService.getOtpDataEmail(etudiant.getMail()));
                    phone = impressionService.getOtpDataPhoneNumber(convention.getTelPortableEtudiant());
                    signataireDto.setOrder(s.getOrdre());
                    break;
                case enseignant:
                    Enseignant enseignant = convention.getEnseignant();
                    signataireDto.setName(enseignant.getNom());
                    signataireDto.setGivenname(enseignant.getPrenom());
                    signataireDto.setMail(impressionService.getOtpDataEmail(enseignant.getMail()));
                    phone = impressionService.getOtpDataPhoneNumber(enseignant.getTel());
                    signataireDto.setOrder(s.getOrdre());
                    break;
                case tuteur:
                    Contact tuteur = convention.getContact();
                    signataireDto.setName(tuteur.getNom());
                    signataireDto.setGivenname(tuteur.getPrenom());
                    signataireDto.setMail(impressionService.getOtpDataEmail(tuteur.getMail()));
                    phone = impressionService.getOtpDataPhoneNumber(tuteur.getTel());
                    signataireDto.setOrder(s.getOrdre());
                    break;
                case signataire:
                    Contact signataire = convention.getSignataire();
                    signataireDto.setName(signataire.getNom());
                    signataireDto.setGivenname(signataire.getPrenom());
                    signataireDto.setMail(impressionService.getOtpDataEmail(signataire.getMail()));
                    phone = impressionService.getOtpDataPhoneNumber(signataire.getTel());
                    signataireDto.setOrder(s.getOrdre());
                    break;
                case viseur:
                    CentreGestion centreGestion = convention.getCentreGestion();
                    signataireDto.setName(centreGestion.getNomViseur());
                    signataireDto.setGivenname(centreGestion.getPrenomViseur());
                    signataireDto.setMail(impressionService.getOtpDataEmail(centreGestion.getMailViseur()));
                    phone = impressionService.getOtpDataPhoneNumber(centreGestion.getTelephone());
                    signataireDto.setOrder(s.getOrdre());
                    break;
            }
            if (signataireDto.getOrder() != 0) {
                signataireDto.setPhone(conventionService.parseNumTel(phone));
                signataires.add(signataireDto);
            }
        });

        metadata.setSignatory(signataires);
        return metadata;
    }

    public int upload(IdsListDto idsListDto, boolean isAvenant) {
        AppSignatureEnum appSignature = applicationBootstrap.getAppConfig().getAppSignatureEnabled();
        if (appSignature == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "La signature électronique n'est pas configurée");
        }
        if (idsListDto.getIds().size() == 0) {
            throw new AppException(HttpStatus.BAD_REQUEST, "La liste est vide");
        }
        int count = 0;
        for (int id : idsListDto.getIds()) {
            Convention convention = null;
            Avenant avenant = null;
            String queryParam = "conventionid";
            if (isAvenant) {
                queryParam = "avenantid";
                avenant = avenantJpaRepository.findById(id);
                if (avenant != null) convention = avenant.getConvention();
            } else {
                convention = conventionJpaRepository.findById(id);
            }
            if (convention == null || (appSignature == AppSignatureEnum.DOCAPOSTE && convention.getCentreGestion().getCircuitSignature() == null)) {
                continue;
            }
            ResponseDto controles = conventionService.controleEmailTelephone(convention);
            if (controles.getError().size() > 0) {
                continue;
            }
            switch (appSignature) {
                case DOCAPOSTE:
                    docaposteClient.upload(convention, avenant);
                    break;
                case ESUPSIGNATURE:
                case EXTERNE:
                    String documentId = webClient.post()
                            .uri(applicationBootstrap.getAppConfig().getWebhookSignatureUri() + "?" + queryParam + "=" + id)
                            .header("Authorization", "Bearer " + applicationBootstrap.getAppConfig().getWebhookSignatureToken())
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();
                    if (documentId != null) {
                        // Que fait-on si un précédent envoi a déjà été fait avant ?
                        String previousDocumentId = isAvenant ? avenant.getDocumentId() : convention.getDocumentId();
                        if (previousDocumentId != null) {
                            // Pour ESUP-Signature, on supprime l'ancien avant de renseigner le nouveau
                            if (appSignature == AppSignatureEnum.ESUPSIGNATURE) {
                                webClient.delete()
                                    .uri(applicationBootstrap.getAppConfig().getEsupSignatureUri() + "/signrequests/soft/" + previousDocumentId)
                                    .retrieve()
                                    .bodyToMono(String.class)
                                    .block();
                            }
                        }
                        if (isAvenant) {
                            avenant.setDateEnvoiSignature(new Date());
                            avenant.setDocumentId(documentId);
                            avenantJpaRepository.saveAndFlush(avenant);
                        } else {
                            convention.setDateEnvoiSignature(new Date());
                            convention.setDocumentId(documentId);
                            conventionJpaRepository.saveAndFlush(convention);
                        }
                    }
                    break;
            }
            count++;
        }
        return count;
    }

    public void updateHistorique(Convention convention) {
        if (convention == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Convention inexistante");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, -30);
        Date nowMinus30 = calendar.getTime();
        if (convention.getDateActualisationSignature() != null && nowMinus30.before(convention.getDateActualisationSignature())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Le rafraichissement des données n'est possible que toutes les 30 minutes");
        }
        AppSignatureEnum appSignature = applicationBootstrap.getAppConfig().getAppSignatureEnabled();
        if (appSignature == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "La signature électronique n'est pas configurée");
        }
        if (appSignature == AppSignatureEnum.EXTERNE) {
            throw new AppException(HttpStatus.NO_CONTENT, "La récupération de l'historique sera faite automatiquement");
        }

        try {
            List<Historique> historiques = new ArrayList<>();
            switch (appSignature) {
                case DOCAPOSTE:
                    historiques = docaposteClient.getHistorique(convention.getDocumentId(), convention.getCentreGestion().getSignataires());
                    break;
                case ESUPSIGNATURE:
                    historiques = webhookService.getHistorique(convention.getDocumentId(), convention);
                    break;
            }
            setSignatureHistorique(convention, historiques);
        } catch (Exception e) {
            logger.error(e);
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur de la récupération de l'historique");
        }
    }

    public void updateHistorique(Avenant avenant) {
        if (avenant == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Avenant inexistant");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, -30);
        Date nowMinus30 = calendar.getTime();
        if (avenant.getDateActualisationSignature() != null && nowMinus30.before(avenant.getDateActualisationSignature())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Le rafraichissement des données n'est possible que toutes les 30 minutes");
        }
        AppSignatureEnum appSignature = applicationBootstrap.getAppConfig().getAppSignatureEnabled();
        if (appSignature == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "La signature électronique n'est pas configurée");
        }
        if (appSignature == AppSignatureEnum.EXTERNE) {
            throw new AppException(HttpStatus.NO_CONTENT, "La récupération de l'historique sera faite automatiquement");
        }

        try {
            List<Historique> historiques = new ArrayList<>();
            switch (appSignature) {
                case DOCAPOSTE:
                    historiques = docaposteClient.getHistorique(avenant.getDocumentId(), avenant.getConvention().getCentreGestion().getSignataires());
                    break;
                case ESUPSIGNATURE:
                    historiques = webhookService.getHistorique(avenant.getDocumentId(), avenant.getConvention());
                    break;
            }
            setSignatureHistorique(avenant, historiques);
        } catch (Exception e) {
            logger.error(e);
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur de la récupération de l'historique");
        }
    }

    public void setSignatureHistorique(Convention convention, List<Historique> historiques) {
        convention.setDateActualisationSignature(new Date());
        for (Historique historique : historiques) {
            switch (historique.getTypeSignataire()) {
                case etudiant:
                    convention.setDateSignatureEtudiant(historique.getDateSignature());
                    convention.setDateDepotEtudiant(historique.getDateDepot());
                    if (convention.getDateDepotEtudiant() == null && historique.getDateDepot() == null) {
                        convention.setDateDepotEtudiant(historique.getDateSignature());
                    }
                    break;
                case enseignant:
                    convention.setDateSignatureEnseignant(historique.getDateSignature());
                    convention.setDateDepotEnseignant(historique.getDateDepot());
                    if (convention.getDateDepotEnseignant() == null && historique.getDateDepot() == null) {
                        convention.setDateDepotEnseignant(historique.getDateSignature());
                    }
                    break;
                case tuteur:
                    convention.setDateSignatureTuteur(historique.getDateSignature());
                    convention.setDateDepotTuteur(historique.getDateDepot());
                    if (convention.getDateDepotTuteur() == null && historique.getDateDepot() == null) {
                        convention.setDateDepotTuteur(historique.getDateSignature());
                    }
                    break;
                case signataire:
                    convention.setDateSignatureSignataire(historique.getDateSignature());
                    convention.setDateDepotSignataire(historique.getDateDepot());
                    if (convention.getDateDepotSignataire() == null && historique.getDateDepot() == null) {
                        convention.setDateDepotSignataire(historique.getDateSignature());
                    }
                    break;
                case viseur:
                    convention.setDateSignatureViseur(historique.getDateSignature());
                    convention.setDateDepotViseur(historique.getDateDepot());
                    if (convention.getDateDepotViseur() == null && historique.getDateDepot() == null) {
                        convention.setDateDepotViseur(historique.getDateSignature());
                    }
                    break;
                default:
                    break;
            }
        }
        conventionJpaRepository.save(convention);

        // Si toutes les dates sont renseignées, on récupère le PDF signé
        if (convention.isAllSignedDateSetted()) {
            MetadataDto metadataDto = getPublicMetadata(convention);
            downloadSignedPdf(convention.getDocumentId(), metadataDto);
        }
    }

    public void setSignatureHistorique(Avenant avenant, List<Historique> historiques) {
        avenant.setDateActualisationSignature(new Date());
        for (Historique historique : historiques) {
            switch (historique.getTypeSignataire()) {
                case etudiant:
                    avenant.setDateSignatureEtudiant(historique.getDateSignature());
                    avenant.setDateDepotEtudiant(historique.getDateDepot());
                    if (avenant.getDateDepotEtudiant() == null && historique.getDateDepot() == null) {
                        avenant.setDateDepotEtudiant(historique.getDateSignature());
                    }
                    break;
                case enseignant:
                    avenant.setDateSignatureEnseignant(historique.getDateSignature());
                    avenant.setDateDepotEnseignant(historique.getDateDepot());
                    if (avenant.getDateDepotEnseignant() == null && historique.getDateDepot() == null) {
                        avenant.setDateDepotEnseignant(historique.getDateSignature());
                    }
                    break;
                case tuteur:
                    avenant.setDateSignatureTuteur(historique.getDateSignature());
                    avenant.setDateDepotTuteur(historique.getDateDepot());
                    if (avenant.getDateDepotTuteur() == null && historique.getDateDepot() == null) {
                        avenant.setDateDepotTuteur(historique.getDateSignature());
                    }
                    break;
                case signataire:
                    avenant.setDateSignatureSignataire(historique.getDateSignature());
                    avenant.setDateDepotSignataire(historique.getDateDepot());
                    if (avenant.getDateDepotSignataire() == null && historique.getDateDepot() == null) {
                        avenant.setDateDepotSignataire(historique.getDateSignature());
                    }
                    break;
                case viseur:
                    avenant.setDateSignatureViseur(historique.getDateSignature());
                    avenant.setDateDepotViseur(historique.getDateDepot());
                    if (avenant.getDateDepotViseur() == null && historique.getDateDepot() == null) {
                        avenant.setDateDepotViseur(historique.getDateSignature());
                    }
                    break;
                default:
                    break;
            }
        }
        avenantJpaRepository.save(avenant);

        // Si toutes les dates sont renseignées, on récupère le PDF signé
        if (avenant.isAllSignedDateSetted()) {
            MetadataDto metadataDto = getPublicMetadata(avenant.getConvention(), avenant.getId());
            downloadSignedPdf(avenant.getDocumentId(), metadataDto);
        }
    }

    public String getSignatureFilePath(String filename) {
        return applicationBootstrap.getAppConfig().getDataDir() + FolderEnum.SIGNATURES + "/" + filename + "_signe.pdf";
    }

    public void downloadSignedPdf(String documentId, MetadataDto metadataDto) {
        InputStream inputStream = null;
        switch (applicationBootstrap.getAppConfig().getAppSignatureEnabled()) {
            case DOCAPOSTE:
                inputStream = docaposteClient.download(documentId);
                break;
            case ESUPSIGNATURE:
                inputStream = webhookService.download(documentId);
                break;
        }
        if (inputStream != null) {
            saveSignedFile(metadataDto, inputStream);
        }
    }

    public void saveSignedFile(MetadataDto metadataDto, InputStream inputStream) {
        Path uploadLocation = Paths.get(getSignatureFilePath(metadataDto.getTitle()));
        try {
            Files.copy(inputStream, uploadLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de l'enregistrement du PDF");
        }
    }
}
