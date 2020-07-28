package com.emailclassification.service;

import java.util.Base64;
import java.util.StringTokenizer;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ObscureService
{
	private static final String key = "aesEncryptionKey";
	private static final String initVector = "encryptionIntVec";

	public static String obscure(String value)
	{
		try
		{
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			String result = "";
			StringTokenizer tokens = new StringTokenizer(value);
			while (tokens.hasMoreTokens())
			{
				String token = tokens.nextToken();
				String obscured_token = Base64.getEncoder().encodeToString(cipher.doFinal(token.getBytes()));
				if (result.equals(""))
				{
					result += obscured_token;
				} else
				{
					result += " " + obscured_token;
				}
			}
			return result;

		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	public static String reverse_obscure(String encrypted)
	{
		try
		{
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			StringTokenizer tokens = new StringTokenizer(encrypted);
			String result = "";
			while (tokens.hasMoreTokens())
			{
				String token = tokens.nextToken();
				String dec = new String(cipher.doFinal(Base64.getDecoder().decode(token)));
				if (result.equals(""))
				{
					result += dec;
				} else
				{
					result += " " + dec;
				}

			}
			return result;

		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return null;
	}

	public static void main(String arg[])
	{
		String str = "THIS IS DIFFERENT THEN ANYTHING ELSE YOU'VE SEEN";
		String enc = obscure(str);
		System.out.println(enc);
		String dec = reverse_obscure(enc);
		System.out.println(dec);
	}

}
