package it.cnr.ict.service;

import feign.form.FormData;
import it.cnr.ict.domain.Category;
import it.cnr.ict.domain.ExternalProblem;
import it.cnr.ict.domain.State;
import it.cnr.ict.domain.User;
import it.cnr.ict.repository.Oil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OilService {

    private final Logger LOGGER = LoggerFactory.getLogger(OilService.class);

    @Value("${oil.instance}")
    private String instance;

    @Value("${oil.default.categoria.id:1}")
    private Integer defaultCategoria;

    @Value("${oil.default.categoria.descrizione:default}")
    private String defaultCategoriaDescrizione;

    private Oil oil;

    public OilService(Oil oil) {
        this.oil = oil;
    }

    public Long newProblem(ExternalProblem externalProblem) {
        externalProblem.setCategoria(Optional.ofNullable(externalProblem)
                .flatMap(externalProblem1 -> Optional.ofNullable(externalProblem1.getCategoria()))
                .orElseGet(() -> defaultCategoria));
        externalProblem.setCategoriaDescrizione(Optional.ofNullable(externalProblem)
                .flatMap(externalProblem1 -> Optional.ofNullable(externalProblem1.getCategoriaDescrizione()))
                .orElseGet(() -> defaultCategoriaDescrizione));
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
        ep.setStato(newState);
        ep.setLogin(userName);

        oil.addField(ep, instance);
    }

    public void addAttachments(Long idProblem, FormData formData) {
        oil.addAttachment(idProblem, formData, instance);
    }

    public List<Category> getCategories() {
        return oil.getCategories(instance);
    }

    public Long addCategory(Category category) {
        return oil.addCategory(category, instance);
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

    public List<User> getExpertCategories(String uid) {
        return oil.getExpertCategories(uid, instance);
    }

    public void assignCategory2User(String id, String uid) {
        oil.assignCategory2User(id, uid, instance);
    }

    public void assignCategory2UserBIS(String id, String uid) {
        oil.assignCategory2UserBIS(id, uid, instance);
    }

    public void removeCategory2User(String id, String uid) {
        oil.removeCategory2User(id, uid, instance);
    }

}
