package pl.com.bottega.docflowjee.docflow.rest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RequestErrors {

    public Map<String, List<String>> errors = new HashMap<>();

    public RequestErrors addError(String field, String msg) {
        List<String> errorsList = errors.getOrDefault(field, new LinkedList<>());
        errorsList.add(msg);
        errors.put(field, errorsList);
        return this;
    }

}
