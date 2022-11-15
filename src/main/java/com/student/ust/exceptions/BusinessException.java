package com.student.ust.exceptions;

/**
 * The type Business exception.
 */
public class BusinessException extends RuntimeException{
    /**
     * Instantiates a new Business exception.
     *
     * @param exception the exception
     */
    BusinessException(String exception)
    {
        super(exception);
    }

}
