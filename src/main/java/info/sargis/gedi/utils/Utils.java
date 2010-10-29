package info.sargis.gedi.utils;

/**
 * Created by IntelliJ IDEA.
 * User: Sargis Harutyunyan
 * Date: 29 oct. 2010
 * Time: 20:59:57
 */
public class Utils {
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}
