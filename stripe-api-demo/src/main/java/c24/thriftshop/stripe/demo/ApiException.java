package c24.thriftshop.stripe.demo;

public class ApiException extends Exception {
    ApiError error;

    @Override
    public void printStackTrace() {
        System.out.println(error);
    }
}
