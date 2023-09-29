package org.esup_portail.esup_stage.controller.apipublic;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.esup_portail.esup_stage.dto.MetadataDto;
import org.esup_portail.esup_stage.dto.PdfMetadataDto;
import org.esup_portail.esup_stage.exception.AppException;
import org.esup_portail.esup_stage.model.Convention;
import org.esup_portail.esup_stage.repository.ConventionJpaRepository;
import org.esup_portail.esup_stage.service.impression.ImpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;

@ApiPublicController
@RequestMapping("/conventions")
public class ConventionPublicController {

    @Autowired
    ConventionJpaRepository conventionJpaRepository;

    @Autowired
    ImpressionService impressionService;

    @GetMapping(value = "/{id}/metadata", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Récupération des metadata de la convention")
    @ApiResponses({
            @ApiResponse(),
            @ApiResponse(
                    responseCode = "404",
                    description = "Convention non trouvée",
                    content = { @Content(mediaType = MediaType.TEXT_PLAIN_VALUE) }
            )
    })
    public MetadataDto getConventionMetadata(
            @PathVariable("id") @Parameter(description = "Identifiant de la convention") int id
    ) {
        Convention convention = conventionJpaRepository.findById(id);
        if (convention == null) {
            throw new AppException(HttpStatus.NOT_FOUND, "Convention inexistante");
        }
        return impressionService.getPublicMetadata(convention);
    }

    @GetMapping(value = "/{id}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    @Operation(description = "Récupération du PDF de la convention")
    @ApiResponses({
            @ApiResponse(),
            @ApiResponse(
                    responseCode = "404",
                    description = "Convention non trouvée",
                    content = { @Content(mediaType = MediaType.TEXT_PLAIN_VALUE) }
            )
    })
    public ResponseEntity<byte[]> getConventionPdf(
            @PathVariable("id") @Parameter(description = "Identifiant de la convention") int id
    ) {
        Convention convention = conventionJpaRepository.findById(id);
        if (convention == null) {
            throw new AppException(HttpStatus.NOT_FOUND, "Convention inexistante");
        }
        return ResponseEntity
                .ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.builder("attachment")
                                .filename("Convention_" + convention.getId() + ".pdf")
                                .build()
                                .toString())
                .body(impressionService.getPublicPdf(convention, null));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Récupération du PDF de la convention en base64 et des metadata")
    @ApiResponses({
            @ApiResponse(),
            @ApiResponse(
                    responseCode = "404",
                    description = "Convention non trouvée",
                    content = { @Content(mediaType = MediaType.TEXT_PLAIN_VALUE) }
            )
    })
    public PdfMetadataDto getConvention(
            @PathVariable("id") @Parameter(description = "Identifiant de la convention") int id
    ) {
        Convention convention = conventionJpaRepository.findById(id);
        if (convention == null) {
            throw new AppException(HttpStatus.NOT_FOUND, "Convention inexistante");
        }

        PdfMetadataDto pdfMetadataDto = new PdfMetadataDto();
        pdfMetadataDto.setPdf64(Base64.getEncoder().encodeToString(impressionService.getPublicPdf(convention, null)));
        pdfMetadataDto.setMetadata(impressionService.getPublicMetadata(convention));
        return pdfMetadataDto;
    }
}
