package it.cnr.ict.web.rest;

import feign.form.FormData;
import it.cnr.ict.domain.ExternalProblem;
import it.cnr.ict.service.OilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * REST controller for managing OIL.
 */
@RestController
@RequestMapping("/api/oil")
public class OILResource {
    private final Logger log = LoggerFactory.getLogger(OILResource.class);

    private final OilService oilService;

    public OILResource(OilService oilService) {
        this.oilService = oilService;
    }

    @PostMapping("/problem")
    public ResponseEntity<Long> createProblem(@RequestBody ExternalProblem externalProblem,
                                              @RequestParam(required = false) String allegatoContentType,
                                              @RequestParam(required = false) String allegatoFileName) throws URISyntaxException {
        log.debug("REST request to create problem : {}", externalProblem);
        if (externalProblem.getIdSegnalazione() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "A new problem cannot already have an ID");
        }
        Long result = oilService.newProblem(externalProblem);
        Optional<String> allegato = Optional.ofNullable(externalProblem.getAllegato());
        if (allegato.isPresent()) {
            FormData formData = new FormData(
                    allegatoContentType,
                    allegatoFileName,
                    allegato.get().getBytes());
            oilService.addAttachments(result, formData);
        }
        return ResponseEntity.created(new URI("/"))
                .body(result);
    }

}
