package com.domaintoolsapi;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DTSigner {
  private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

  private final String apiUsername;
  private final String apiKey;
  private SimpleDateFormat timeFormatter;

  protected DTSigner(String api_username, String api_key) {
    this.apiUsername = api_username;
    this.apiKey = api_key;
    this.timeFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    this.timeFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
  }

  protected String timestamp() {
    Date now = new Date();
    return this.timeFormatter.format(now);
  }

  protected String getHexString(byte[] b) {
    String result = "";
    for (int i=0; i < b.length; i++) {
	    result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring(1);
    }
    return result;
  }

  protected String sign(String timestamp, String uri)
    throws java.security.SignatureException {
    String Result;
    try {
	    String data = new String(this.apiUsername + timestamp + uri);
	    SecretKeySpec signingKey = new SecretKeySpec(this.apiKey.getBytes(),
			HMAC_SHA1_ALGORITHM);
	    Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
	    mac.init(signingKey);
	    byte[] rawSignature = mac.doFinal(data.getBytes());
	    Result = this.getHexString(rawSignature);
    } catch(Exception e) {
	    throw new java.security.SignatureException("Failed to generate HMAC : "
			+ e.getMessage());
    }
    return Result;
  }
}