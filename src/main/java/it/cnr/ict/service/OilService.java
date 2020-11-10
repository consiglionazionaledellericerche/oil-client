package it.cnr.ict.service;

import it.cnr.ict.config.OilConfigurationProperties;
import it.cnr.ict.domain.ExternalProblem;
import it.cnr.ict.domain.State;
import it.cnr.ict.repository.Oil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class OilService {

    private final Logger log = LoggerFactory.getLogger(OilService.class);

    @Autowired
    private OilConfigurationProperties ocp;

    private Oil oil;

    public OilService(Oil oil) {
        this.oil = oil;
    }

    public Long newProblem(ExternalProblem externalProblem) {
        return oil.newProblem(externalProblem, ocp.getInstance());
    }

    public void addNote(Long idProblem, String note, String userName) {
        changeState(idProblem, State.NUOVA, note, userName);
    }

    public void changeState(Long idProblem, State newState, String note, String userName) {

        ExternalProblem ep = new ExternalProblem();
        ep.setIdSegnalazione(idProblem);
        ep.setNota(note);
        ep.setStato(newState.getValue());
        ep.setLogin(userName);

        oil.addField(ep, ocp.getInstance());
    }

    public void addAttachments(Long idProblem,  File file) {
        oil.addAttachment(idProblem, file, ocp.getInstance());
    }

}
