package gosk.szymon.gateway;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class ViewController {

    @GetMapping("/view-orders")
    public String viewOrders() throws IOException {
        return "";
    }

}
