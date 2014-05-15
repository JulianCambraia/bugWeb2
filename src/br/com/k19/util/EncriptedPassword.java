package br.com.k19.util;

import org.jboss.security.auth.spi.Util;

public class EncriptedPassword {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Util.createPasswordHash("SHA-256", "BASE64", null, null, "senha"));

	}

}
