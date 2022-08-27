package noeasy.server.util;

public class KeyService {
    private static String baseURL = "http://54.180.115.160:8080";
    private static String RESTAPIKey = "e51b04ea236d57fbeb50cf812eee0ae1";

    private static String secretKey = "boggleboggle-jwt-authorization";

    public static  String addBaseURL(String imageURL){
        return baseURL + imageURL;
    }

    public static String getBaseURL(){
        return baseURL;
    }

    public static String getRESTAPIKey() {
        return RESTAPIKey;
    }
    public static String getSecretKey(){
        return secretKey;
    }
}