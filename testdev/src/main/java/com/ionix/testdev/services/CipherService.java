package com.ionix.testdev.services;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.springframework.stereotype.Service;

import com.ionix.testdev.common.CommonConstants;

@Service
public class CipherService {
	
	public String CipherKey(String plainRut) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		DESKeySpec keySpec = new DESKeySpec(CommonConstants.CIPHER_KEY.getBytes("UTF8"));
        SecretKey ks = keyFactory.generateSecret(keySpec);
        Cipher cipher = Cipher.getInstance("DES");
 
        cipher.init(Cipher.ENCRYPT_MODE, ks);
		byte[] cleartext = plainRut.getBytes("UTF8");
		String encryptedRut = Base64.getEncoder().encodeToString(cipher.doFinal(cleartext));
		return encryptedRut;
		
	} 

}
