package info.sargis.gedi;

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
public class EDIBuilderException extends RuntimeException {

    private static final long serialVersionUID = -2058409450413388131L;

    public EDIBuilderException() {
    }

    public EDIBuilderException(String message) {
        super(message);
    }

    public EDIBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public EDIBuilderException(Throwable cause) {
        super(cause);
    }

}
