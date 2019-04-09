package pl.com.bottega.docflowjee.docflow.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestErrors {

    public Map<String, List<String>> errors = new HashMap<>();

    public RequestErrors addError(String field, String msg) {
        errors.put(field, Arrays.asList(msg));
        return this;
    }

}
