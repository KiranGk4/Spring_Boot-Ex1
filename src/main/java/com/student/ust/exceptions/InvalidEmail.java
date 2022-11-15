package com.student.ust.exceptions;

/**
 * The type Invalid email.
 */
public class InvalidEmail extends BusinessException{
    /**
     * Instantiates a new Invalid email.
     */
    public InvalidEmail()
    {
        super("Invalid email format");
        //System.out.println("Invalid Email");
    }

}
