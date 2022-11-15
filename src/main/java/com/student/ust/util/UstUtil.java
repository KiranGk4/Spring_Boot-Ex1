package com.student.ust.util;

import com.student.ust.entity.Student;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The type Ust util.
 */
public class UstUtil {
    /**
     * Check email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean validateEmail(String email)
    {
        String regex = "^([A-Za-z0-9.-_]+)@([a-z]+)\\.([a-z]+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches())
            return true;
        else
            return false;
    }

    /**
     * Validate password boolean.
     *
     * @param password the password
     * @return the boolean
     */
    public static boolean validatePassword(String password)
    {
        String regex = "^(?=.+[A-Za-z]{3,})(?=.+[0-9]{3,})(?=[!@#%&*.-_]).{7,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        //matcher.matches()?return true:return false;
        if(matcher.matches())
            return true;
        else
            return false;
    }

    /**
     * Hash password student.
     *
     * @param password the password
     * @return the student
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        String hexPassword = toHexString(getSHA(password));
        return hexPassword;
    }

    private static byte[] getSHA(String password)throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * To hex string string.
     *
     * @param hash the hash
     * @return the string
     */
    public static String toHexString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while(hexString.length() < 64)
        {
            hexString.insert(0,'0');
        }
        return hexString.toString();

    }

}
