package com.dianke.employee.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.text.RandomStringGenerator;

import java.util.ArrayList;
import java.util.List;

public class Md5Utils {
    public static String encode(String password) {
        RandomStringGenerator rsg = new RandomStringGenerator.Builder()
                .withinRange(new char[] {'0', '9'}, new char[] {'a', 'z'}, new char[] {'A', 'Z'}).build();

        String randomString = rsg.generate(16);  //生成16位的随机字符串

        String md5Pwd = DigestUtils.md5Hex(password);  //密码作MD5加密，得到32位字符串

        String newMd5 = DigestUtils.md5Hex(randomString + md5Pwd); //将盐值与密码组合再



        char[] randomStringCharArray = randomString.toCharArray();
        char[] md5PwdCharArray = newMd5.toCharArray();

        char[] c = new char[48];

        int temp =  0;
        int temp1 = 0;
        for(int i = 0; i < c.length; i++) {
            c[i] = i % 3 == 1 ? randomStringCharArray[temp++] : md5PwdCharArray[temp1++];
        }
        return new String(c);
    }

    public static boolean validatePassword(String password, String saltString) {
        char[] saltCharArray = saltString.toCharArray();

        List<Character> salt = new ArrayList<>();
        List<Character> string = new ArrayList<>();

        //提取出盐值和md5字符串
        for(int i = 0; i < saltCharArray.length; i++) {
            if(i % 3 == 1) {
                salt.add(saltCharArray[i]);
            }else {
                string.add(saltCharArray[i]);
            }
        }

        String md5Password = DigestUtils.md5Hex(password); //秘密作md5加密
        StringBuffer stringBuffer = new StringBuffer();

        for(Character c : salt) {
            stringBuffer.append(c);
        }

        //将加密后的密码和盐值拼接后再作md5加密
        String md5String = DigestUtils.md5Hex(stringBuffer.toString() + md5Password);

        StringBuffer sb = new StringBuffer();
        for(Character c : string) {
            sb.append(c);
        }

        return sb.toString().equals(md5String);
    }
}
