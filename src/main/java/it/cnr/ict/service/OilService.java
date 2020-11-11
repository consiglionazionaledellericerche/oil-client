package it.cnr.ict.service;

import it.cnr.ict.domain.Category;
import it.cnr.ict.domain.ExternalProblem;
import it.cnr.ict.domain.State;
import it.cnr.ict.domain.User;
import it.cnr.ict.repository.Oil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class OilService {

    private final Logger log = LoggerFactory.getLogger(OilService.class);

    @Value("${oil.instance}")
    private String instance;

    private Oil oil;

    public OilService(Oil oil) {
        this.oil = oil;
    }

    public Long newProblem(ExternalProblem externalProblem) {
        return oil.newProblem(externalProblem, instance);
    }

    // TODO: controllare se l'aggiunta nota si puo' fare solo per lo stato> NUOVA
    public void addNote(Long idProblem, String note, String userName) {
        changeState(idProblem, State.NUOVA, note, userName);
    }

    public void changeState(Long idProblem, State newState, String note, String userName) {

        ExternalProblem ep = new ExternalProblem();
        ep.setIdSegnalazione(idProblem);
        ep.setNota(note);
        ep.setStato(newState.getValue());
        ep.setLogin(userName);

        oil.addField(ep, instance);
    }

    public void addAttachments(Long idProblem,  File file) {
        oil.addAttachment(idProblem, file, instance);
    }

    public List<Category> getCategories() {
        return oil.getCategories(instance);
    }

    public void addCategory(Category category) {
        oil.addCategory(category, instance);
    }

    public void modifyCategory(Category category) {
        oil.modifyCategory(category, instance);
    }

    public void deleteCategory(String categoryName) {
        oil.deleteCategory(categoryName, instance);
    }

    public List<User> getUsers() {
        return oil.getUsers(instance);
    }

    public void addUser(User user) {
        oil.addUser(user, instance);
    }

    public void modifyUser(User user) {
        oil.modifyUser(user, instance);
    }

    public void deleteUser(String userId) {
        oil.deleteUser(userId, instance);
    }

    public List<User> getExperts(Long categoryId) {
        return oil.getExperts(categoryId, instance);
    }

    List<User> getExpertCategories(String uid) {
        return oil.getExpertCategories(uid, instance);
    }

    void assignCategory2User(String id, String uid) {
        oil.assignCategory2User(id, uid, instance);
    }

    void assignCategory2UserBIS(String id, String uid) {
        oil.assignCategory2UserBIS(id, uid, instance);
    }

    void removeCategory2User(String instance, String id, String uid) {
        oil.removeCategory2User(id, uid, instance);
    }

}
