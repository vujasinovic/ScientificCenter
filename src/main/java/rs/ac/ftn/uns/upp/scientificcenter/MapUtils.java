package rs.ac.ftn.uns.upp.scientificcenter;

import java.util.HashMap;
import java.util.Map;

public final class MapUtils {

    private static final String DATE = "date";
    private static final String SQL_DATE_SPLIT = "-";
    private static final String CAMUNDA_DATE_SPLIT = "/";
    private static final String ANOTHER_ONE = "anotherOne";
    private static final String ENUM = "Enum";
    private static final String WHITESPACE = " ";

    private MapUtils() {

    }

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
}
