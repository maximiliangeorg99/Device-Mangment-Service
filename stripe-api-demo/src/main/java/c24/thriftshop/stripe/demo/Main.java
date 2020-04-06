package c24.thriftshop.stripe.demo;

public class Main {
    public static void main(String[] args) {
        //System.out.println(new Service().getAllCustomer());
        //System.out.println(new Service().deleteCustomer("cus_H2uGnf5GvR9EgH"));
        try {
            System.out.println(new Service().getCustomer("cus_H2umk0n3g222UDUCM"));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
