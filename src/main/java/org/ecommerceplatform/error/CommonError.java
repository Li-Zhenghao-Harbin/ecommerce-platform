package org.ecommerceplatform.error;

public interface CommonError {
    public int getErrorCode();
    public String getErrorMessage();
    public CommonError setErrorMessage(String errorMessage);
}
