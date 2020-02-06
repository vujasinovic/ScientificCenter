package rs.ac.ftn.uns.upp.scientificcenter.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MapperUtils {
    private static final String DATE = "date";
    private static final String SQL_DATE_SPLIT = "-";
    private static final String CAMUNDA_DATE_SPLIT = "/";
    private static final String ENUM = "Enum";
    private static final String WHITESPACE = " ";

    public static Map<String, Object> cast(Map<String, String[]> params) {
        Map<String, Object> res = new HashMap<>();

        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(DATE)) {

                String[] date = entry.getValue()[0].split(SQL_DATE_SPLIT);

                entry.getValue()[0] = date[2] + CAMUNDA_DATE_SPLIT + date[1] + CAMUNDA_DATE_SPLIT + date[0];
            }

            if (entry.getKey().contains(ENUM)) {
                entry.getValue()[0] = entry.getValue()[0].toLowerCase().replaceAll(WHITESPACE, "");
            }

            res.put(entry.getKey(), entry.getValue()[0].toLowerCase());
        }

        return res;
    }

    public static <T> T map(Object source, Class<T> destination) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return objectMapper.convertValue(source, destination);
    }

    public static <T> T writeVariablesToObject(Map<String, Object> variables, Class<T> responseType) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return objectMapper.convertValue(variables, responseType);
    }
}
