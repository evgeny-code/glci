package evg299.learn.glci;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public ResponseEntity<String> hey(@Value("${app.msg}") final String msg) {
        return ResponseEntity.ok(msg);
    }
}
