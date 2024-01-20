package at.tugraz.oop2;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class HandleURLErrors extends AbstractErrorController {
    public HandleURLErrors(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }
    @RequestMapping
    public ResponseEntity<?> error() {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", "400 - URL error message");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }
}
