package com.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash256 {

	public static String hashPassword(String password)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-256"); // creation d'une intance de messageDigest avec l'algorithme SHA-256
			
			byte[] hashBytes = md.digest(password.getBytes("UTF-8")); // recuperation du tableau de byte hasché
			
			StringBuilder sb = new StringBuilder();
			
			for(byte b : hashBytes)
			{
				String hex = Integer.toHexString(0xff & b);
				if(hex.length()==1)sb.append('0');
				sb.append(hex);
			}
			return sb.toString();
		}catch(NoSuchAlgorithmException |UnsupportedEncodingException e)
		{
			throw new RuntimeException("Erreur lors du haschage");
		}
	}
}
