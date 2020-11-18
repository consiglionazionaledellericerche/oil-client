package it.cnr.ict.service;

import feign.form.FormData;
import it.cnr.ict.OilClientConfiguration;
import it.cnr.ict.domain.Category;
import it.cnr.ict.domain.ExternalProblem;
import it.cnr.ict.domain.User;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OilClientConfiguration.class)
public class OilServiceTest {

    @Autowired
    private OilService oilService;

    @Test
    public void submitHelpDeskRequest() {

        ExternalProblem hd = new ExternalProblem();
        hd.setEmail("foo@bar.it");
        hd.setFirstName("First Name");
        hd.setFamilyName("Family Name");
        hd.setDescrizione("Description ");
        hd.setLogin("foo.bar");
        hd.setTitolo("Title");
        hd.setCategoria(1);
        hd.setCategoriaDescrizione("Cat Desc");
        hd.setConfirmRequested("n");

        Long idProblem = oilService.newProblem(hd);
        Assert.assertNotNull(idProblem);

        oilService.addNote(idProblem, "note", "missioni1");

    }

    @Test
    public void addAttachment() throws IOException {

        ExternalProblem hd = new ExternalProblem();
        hd.setEmail("foo@bar.it");
        hd.setFirstName("First Name");
        hd.setFamilyName("Family Name");
        hd.setDescrizione("Description ");
        hd.setLogin("foo.bar");
        hd.setTitolo("Title");
        hd.setCategoria(1);
        hd.setCategoriaDescrizione("Cat Desc");
        hd.setConfirmRequested("n");
        Long idProblem = oilService.newProblem(hd);

        Resource resource = new ClassPathResource("attachment.jpg");
        byte[] bytesResource = Files.readAllBytes(Paths.get(resource.getURI()));
        FormData formData = new FormData("image/jpg", resource.getFilename(), bytesResource);

        oilService.addAttachments(idProblem, formData);

    }

    @Test
    @Ignore
    public void getCategories() {
        List<Category> categories = oilService.getCategories();
        Assert.assertNotNull(categories);
    }

    @Test
    @Ignore
    public void addCategory() {
        Category cat = new Category();
        cat.setId(88L);
        cat.setNome("test");
        cat.setDescrizione("desc");
        oilService.addCategory(cat);
    }

    @Test
    @Ignore
    public void getUsers() {
        List<User> users = oilService.getUsers();
        Assert.assertNotNull(users);
    }

    @Test
    @Ignore
    public void getExperts() {
        List<User> users = oilService.getExperts(1L);
        Assert.assertNotNull(users);
    }
}
