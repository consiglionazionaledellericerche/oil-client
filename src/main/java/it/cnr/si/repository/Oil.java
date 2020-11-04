package it.cnr.si.repository;

import feign.Headers;
import feign.RequestLine;
import it.cnr.si.domain.ExternalProblem;

import javax.xml.ws.Response;

@Headers({"Content-Type: application/json"})
public interface Oil {

    @RequestLine("POST /pest/HDSiper")
    Response<Long> newProblem(ExternalProblem problem);

}
