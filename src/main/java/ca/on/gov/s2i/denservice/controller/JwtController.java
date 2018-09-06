package ca.on.gov.s2i.denservice.controller;

import java.util.Date;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.on.gov.common.exception.model.AppExceptionResponse;
import ca.on.gov.common.jwt.JwtComp;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/jwt")
@Validated
public class JwtController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(JwtController.class);

    @Autowired
    private JwtComp jwtComp;

    // @Autowired(required=false)

    @SuppressWarnings("rawtypes")
    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        logger.debug("cross domain 1");
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "check jwt is valid or not.", notes = "Do not return jwt content. Errors will be returned as 500 exception.")
    @ApiResponses({
        @ApiResponse(code = 500, response = AppExceptionResponse.class, message = "1.expired. 2.bad signiture. ") })
    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = produces)
    public boolean verify(@NotNull @RequestParam() String jwt) {
        jwtComp.decode(jwt, true);

        return true;
    }

    @ApiOperation(value = "create jwt by given content map", notes = "")
    @ApiResponses({
        @ApiResponse(code = 500, response = AppExceptionResponse.class, message = "") })
    @CrossOrigin(origins = crossDomain)
    @RequestMapping(value = "/content", method = RequestMethod.POST, produces = produces)
    public String[] create(@NotNull @RequestBody Map<String, Object> content, @RequestParam(defaultValue="1533912275803") long expiredDate) {
        //logger.debug((new Date()).getTime()+"---");
        
        String jwt = jwtComp.encode(content, new Date(expiredDate));

        return new String[] {jwt};
    }
}
