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

    public Long addNote(Long idProblem, String note, String userName) {
        return changeState(idProblem, State.NUOVA, note, userName);
    }

    public Long changeState(Long idProblem, State newState, String note, String userName) {

        ExternalProblem ep = new ExternalProblem();
        ep.setIdSegnalazione(idProblem);
        ep.setNota(note);
        ep.setStato(newState);
        ep.setLogin(userName);

        return oil.addField(ep, ocp.getInstance());
    }

    public void addAttachments(long id, MultipartFile uploadedMultipartFile) {

    }

}
