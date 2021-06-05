package kr.fiveminutesmarket.user.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SHA256PasswordEncoder implements JavaPasswordEncoder {

    @Override
    public String encode(String source, String salt) {
        String result = "";

        byte[] sourceBytes = source.getBytes();
        byte[] saltBytes = salt.getBytes();

        byte[] bytes = new byte[sourceBytes.length + saltBytes.length];

        // source(원본) 전체 -> bytes로 복사
        System.arraycopy(sourceBytes, 0, bytes, 0, sourceBytes.length);
        // salt 전체 -> bytes(복사된 source 뒤부터)로 복사
        System.arraycopy(saltBytes, 0, bytes, sourceBytes.length, saltBytes.length);

        try {
            // SHA-256 암호화 방식으로 Digest
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);
            byte[] byteData = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
            }
            result = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }

    // login시 password 일치 여부
    @Override
    public Boolean matches(String password, String encodedPassword, String salt) {
        return encode(password, salt).equals(encodedPassword);
    }

    @Override
    public String generateSalt() {
        Random random = new Random();

        byte[] salt = new byte[8];
        random.nextBytes(salt);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < salt.length; i++) {
            sb.append(String.format("%02x", salt[i]));
        }

        return sb.toString();
    }


}
