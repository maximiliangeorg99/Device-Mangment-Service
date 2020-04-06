package c24.thriftshop.stripe.demo;

public class ApiError {
    String code;
    String doc_url;
    String message;
    String param;
    String type;

    @Override
    public String toString() {
        return "ApiException{" +
                "code='" + code + '\'' +
                ", doc_url='" + doc_url + '\'' +
                ", message='" + message + '\'' +
                ", param='" + param + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
