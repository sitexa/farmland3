package com.sitexa.user.service;

/**
 * User: xnpeng
 * Date: 2010-2-8
 * Time: 11:34:33
 */
public class MemberException extends RuntimeException{
    public static final String OBJECT_ERROR="10";
    public static final String DAO_ERROR="11";
    
    public MemberException(String message) {
        super(message);
    }
}
