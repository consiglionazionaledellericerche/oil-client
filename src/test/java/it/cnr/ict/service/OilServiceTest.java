package it.cnr.ict.service;

import it.cnr.ict.OilClientConfiguration;
import it.cnr.ict.domain.ExternalProblem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public void addAttachment() {

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

//        Long idProblem = oilService.newProblem(hd);

        File file = Paths.get("/home/zyzz/workspace/oil-client/src/test/resources/attachment.jpg").toFile();

        System.out.println("test");

    }


}
