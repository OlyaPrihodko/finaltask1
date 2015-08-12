package com.epam.prihodko.finaltask.logic;

import com.epam.prihodko.finaltask.exception.ProjectException;
import java.security.MessageDigest;
public class PasswordHash {
    private final static PasswordHash instance = new PasswordHash();
    public static PasswordHash getInstance() {
        return instance;
    }
    public String getHash(String password) throws ProjectException {
        try{
            return byteArrayToHexString(PasswordHash.computeHash(password));
        }
        catch (Exception e){
            throw new ProjectException("problem with password hash",e);
        }
    }
    public static byte[] computeHash(String pass)throws Exception
    {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.reset();

        messageDigest.update(pass.getBytes());
        return  messageDigest.digest();
    }

    public static String byteArrayToHexString(byte[] arrayByte){
        StringBuilder stringBuilder = new StringBuilder(arrayByte.length*2);
        for (byte element : arrayByte) {
            int value = element & 0xff;
            if (value < 16) {
                stringBuilder.append('0');
            }
            stringBuilder.append(Integer.toHexString(value));
        }
        return stringBuilder.toString().toUpperCase();
    }
}
