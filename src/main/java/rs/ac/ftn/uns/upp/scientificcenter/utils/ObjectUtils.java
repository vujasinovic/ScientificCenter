package rs.ac.ftn.uns.upp.scientificcenter.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ObjectUtils {

    public static boolean equalsAny(Object value, Object... targets) {
        boolean retVal = false;

        if (ArrayUtils.isNotEmpty(targets)) {
            for (Object target : targets) {
                if (value.equals(target)) {
                    retVal = true;
                    break;
                }
            }
        }

        return retVal;
    }

    public static boolean equalsAnyIgnoreCase(String string, String... targets) {
        boolean retVal = false;

        if (ArrayUtils.isNotEmpty(targets)) {
            for (String target : targets) {
                if (string.equalsIgnoreCase(target)) {
                    retVal = true;
                    break;
                }
            }
        }

        return retVal;
    }

    public static <K, V> boolean nullOrEmpty(Map<K, V> map) {
        return map == null || map.size() == 0;
    }

    public static <K, V> boolean notNullOrEmpty(Map<K, V> map) {
        return (map != null ? map.size() : 0) != 0;
    }

    public static <T> boolean nullOrEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

}
