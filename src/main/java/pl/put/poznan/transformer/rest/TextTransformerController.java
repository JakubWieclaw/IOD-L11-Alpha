package pl.put.poznan.transformer.rest;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.DecoratorBuilder;
import pl.put.poznan.transformer.logic.IdentityTransformer;
import pl.put.poznan.transformer.logic.StringTransformer;

import java.util.Arrays;
import java.util.HashMap;


@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                      @RequestParam(value="transforms", defaultValue="UPPER") String[] transforms) throws JsonProcessingException {

        return transformText(text, transforms);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text,
                       @RequestParam(value="transforms", defaultValue="UPPER") String[] transforms) throws JsonProcessingException {

        return transformText(text, transforms);
    }

    private String transformText(String text, String[] transforms) throws JsonProcessingException {
        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        StringTransformer base = new IdentityTransformer(text);
        StringTransformer transformer = DecoratorBuilder.composeTransformer(base, transforms);
        String s = transformer.transform();
//        HashMap<String, String> map = new HashMap<>();
//        map.put("transformedText", s);
//        String payload = new ObjectMapper().writeValueAsString(map);
        Payload payload = new Payload("\""+s+"\"");
        String jsonPayload = new ObjectMapper().writeValueAsString(payload);
        logger.debug(jsonPayload);
        return jsonPayload;
    }

}


