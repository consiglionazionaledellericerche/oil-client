package it.cnr.si.service;

import it.cnr.si.domain.ExternalProblem;
import it.cnr.si.repository.Oil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.ExecutionException;

@Service
public class OilService {

    private final Logger log = LoggerFactory.getLogger(OilService.class);

    private Oil oil;

    public OilService(Oil oil) {
        this.oil = oil;
    }

    public Long newProblem(ExternalProblem hd) throws ExecutionException, InterruptedException {
        return oil.newProblem(hd).get();
    }

    public void addAttachments(long id, MultipartFile uploadedMultipartFile) {


    }

}
