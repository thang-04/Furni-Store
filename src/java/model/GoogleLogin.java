
package model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

public class GoogleLogin {

    public static String getToken(String code) throws IOException {
    try {
        System.out.println("Attempting to exchange code for token...");
        System.out.println("Code: " + code);
        System.out.println("Redirect URI: " + Iconstant.GOOGLE_REDIRECT_URI);
        
        String response = Request.Post(Iconstant.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(
                        Form.form()
                                .add("client_id", Iconstant.GOOGLE_CLIENT_ID)
                                .add("client_secret", Iconstant.GOOGLE_CLIENT_SECRET)
                                .add("redirect_uri", Iconstant.GOOGLE_REDIRECT_URI)
                                .add("code", code)
                                .add("grant_type", Iconstant.GOOGLE_GRANT_TYPE)
                                .build()
                )
                .execute().returnContent().asString();
        
        System.out.println("Response: " + response);
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    } catch (Exception e) {
        System.out.println("Error exchanging code for token: " + e.getMessage());
        e.printStackTrace();
        throw e;
    }
}

    public static GoogleAccount getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Iconstant.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        GoogleAccount googlePojo = new Gson().fromJson(response, GoogleAccount.class);
        return googlePojo;
    }

}
