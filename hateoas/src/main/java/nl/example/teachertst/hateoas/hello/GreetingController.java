package nl.example.teachertst.hateoas.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@Configuration
@RestController
public class GreetingController {

    private static final String template = "Helloo, this is planet earth again, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Value("${switch.feature.enabled}")
    private boolean switchEnabled;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        if (switchEnabled){
            name = "switch is enabled in properties file";
        }
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}
