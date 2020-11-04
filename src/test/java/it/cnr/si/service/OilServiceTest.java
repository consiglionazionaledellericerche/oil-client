package it.cnr.si.service;

import it.cnr.si.OilClientConfiguration;
import it.cnr.si.domain.ExternalProblem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OilClientConfiguration.class)
public class OilServiceTest {

    @Autowired
    private OilService oilService;

    @Test
    public void subitHelpDeskRequest() throws ExecutionException, InterruptedException {

        ExternalProblem hd = new ExternalProblem();
        hd.setEmail("foo@bar.it");
        hd.setFirstName("foo");
        hd.setLogin("foo.bar");
        hd.setNota("nota");
        hd.setTitolo("titolo");

        Long aLong = oilService.newProblem(hd);

    }


}
