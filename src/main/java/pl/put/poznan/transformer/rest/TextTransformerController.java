package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.DecoratorBuilder;
import pl.put.poznan.transformer.logic.IdentityTransformer;
import pl.put.poznan.transformer.logic.StringTransformer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Map<String, String> get(@PathVariable String text,
                                   @RequestParam(value="transforms", defaultValue="UPPER") String[] transforms) {

        return transformText(text, transforms);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Map<String, String> post(@PathVariable String text,
                       @RequestParam(value="transforms", defaultValue="UPPER") String[] transforms) {

        return transformText(text, transforms);
    }

    private Map<String, String> transformText(String text, String[] transforms) {
        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        StringTransformer base = new IdentityTransformer(text);
        StringTransformer transformer = DecoratorBuilder.composeTransformer(base, transforms);
        String s = transformer.transform();
        HashMap<String, String> map = new HashMap<>();
        map.put("transformedText", s);
        return map;
    }

}


