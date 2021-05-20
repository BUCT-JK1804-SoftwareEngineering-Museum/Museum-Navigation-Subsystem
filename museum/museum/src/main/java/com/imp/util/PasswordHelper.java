package com.imp.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import java.util.Random;


/**
 * @author Dengyn
 */
@Service
public class PasswordHelper {

    /**
     * PWD_SALT_LENGTH: 密码加密盐值长度
     */
    public static final int PWD_SALT_LENGTH = 6;
    /**
     * PWD_ALGORITHM_NAME: 密码加密算法
     */
    public static final String PWD_ALGORITHM_NAME = "MD5";

    /**
     * PWD_ALGORITHM_NAME: 密码加密次数
     */
    public static final int PWD_HASH_ITERATIONS = 2;

    public static final String SALT = "88880000";

    /**
     * 生成密码<br/>
     * @param pwd
     * @return
     */
    public static String generatePwdEncrypt(String pwd) {
        return generatePwdEncrypt(pwd, SALT);
    }

    /**
     * 生成密码<br/>
     *
     * @param pwd
     * @param salt
     * @return
     */
    public static String generatePwdEncrypt(String pwd, String salt) {
        SimpleHash hash = new SimpleHash(PWD_ALGORITHM_NAME, pwd, salt, PWD_HASH_ITERATIONS);
        return hash.toString();
    }

    /**
     * 生成密码<br/>
     * @return
     */
    public static String getDefaultPassword() {
        SimpleHash hash = new SimpleHash(PWD_ALGORITHM_NAME, "123456", SALT, PWD_HASH_ITERATIONS);
        return hash.toString();
    }

    /**
     * 生成盐值<br/>
     *
     * @return
     */
    public static String generateSalt() {
        return Long.toHexString(new Random().nextLong());
    }


    public static void main(String[] args) {
//        String generateSalt = generateSalt();
        String generateSalt = SALT;

        String generatePwdEncrypt = generatePwdEncrypt("123456", generateSalt);
        System.out.println(generateSalt);
        System.out.println(generatePwdEncrypt);

    }



}

