package org.wso2.carbon.identity.conditional.auth.functions.geovelocity.exception;

/**
 * This is the exception class for handling errors.
 */
public class StoreLoginDetailsResolver extends Exception {

    public StoreLoginDetailsResolverException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreLoginDetailsResolverException(String message) {
        super(message);
    }
}
