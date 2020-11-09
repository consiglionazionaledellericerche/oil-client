package it.cnr.ict.repository;

import feign.Headers;
import feign.RequestLine;
import it.cnr.ict.domain.ExternalProblem;

@Headers({"Content-Type: application/json"})
public interface Oil {

    @RequestLine("PUT /pest/HDSiper")
    Long newProblem(ExternalProblem problem);

    @RequestLine("POST /pest/HDSiper")
    Long addField(ExternalProblem problem);
}
