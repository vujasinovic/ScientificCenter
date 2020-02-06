package rs.ac.ftn.uns.upp.scientificcenter.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

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
}
