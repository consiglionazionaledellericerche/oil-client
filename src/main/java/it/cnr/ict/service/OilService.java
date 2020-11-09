package it.cnr.ict.service;

import it.cnr.ict.domain.ExternalProblem;
import it.cnr.ict.repository.Oil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OilService {

    private final Logger log = LoggerFactory.getLogger(OilService.class);

    private Oil oil;

    public OilService(Oil oil) {
        this.oil = oil;
    }

    public Long newProblem(ExternalProblem externalProblem) {
        return oil.newProblem(externalProblem);
    }

    public Long addField(ExternalProblem externalProblem) {
        return oil.addField(externalProblem);
    }

    public void addAttachments(long id, MultipartFile uploadedMultipartFile) {

    }

}
