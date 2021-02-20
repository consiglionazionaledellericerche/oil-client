package it.cnr.ict.web.rest;

import feign.form.FormData;
import it.cnr.ict.config.OilConfigurationProperties;
import it.cnr.ict.domain.Category;
import it.cnr.ict.domain.ExternalProblem;
import it.cnr.ict.service.OilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing OIL.
 */
@RestController
@ConditionalOnProperty(name = "oil.url")
@RequestMapping("/api/oil")
public class OILResource {
    private final Logger log = LoggerFactory.getLogger(OILResource.class);

    private final OilService oilService;
    private final OilConfigurationProperties oilConfigurationProperties;

    public OILResource(OilService oilService, OilConfigurationProperties oilConfigurationProperties) {
        this.oilService = oilService;
        this.oilConfigurationProperties = oilConfigurationProperties;
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
                    Base64.getDecoder().decode(allegato.get().getBytes()));
            oilService.addAttachments(result, formData);
        }
        return ResponseEntity.created(new URI("/"))
                .body(result);
    }

    @GetMapping("/acceptcategories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok().body(oilConfigurationProperties.getAcceptcategories());
    }
}
