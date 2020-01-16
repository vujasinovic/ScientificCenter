package rs.ac.ftn.uns.upp.scientificcenter.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public final class VariableHelper {
    private VariableHelper() {

    }

    public static <T> T writeVariablesToObject(Map<String, Object> variables, Class<T> responseType) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return objectMapper.convertValue(variables, responseType);
    }

}