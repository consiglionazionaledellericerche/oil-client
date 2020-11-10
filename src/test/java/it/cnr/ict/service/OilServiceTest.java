package it.cnr.ict.service;

import it.cnr.ict.OilClientConfiguration;
import it.cnr.ict.domain.ExternalProblem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OilClientConfiguration.class)
public class OilServiceTest {

    @Autowired
    private OilService oilService;

    @Test
    public void subitHelpDeskRequest() {

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

        Long idProblem2 = oilService.addNote(idProblem, "note", "missioni1");
        Assert.assertNull(idProblem2);

    }


}
