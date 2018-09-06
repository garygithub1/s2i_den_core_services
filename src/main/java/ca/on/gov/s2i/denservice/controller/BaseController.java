package ca.on.gov.s2i.denservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public abstract class BaseController {
    protected static final String produces = "application/json; charset=UTF-8";
    protected static final String crossDomain = "*";

    //TODO verify override
    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @SuppressWarnings("rawtypes")
    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        logger.debug("cross domain");
        return new ResponseEntity(HttpStatus.OK);
    }
}
