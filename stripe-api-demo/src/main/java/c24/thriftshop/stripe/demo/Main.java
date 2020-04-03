package c24.thriftshop.stripe.demo;

public class Main {
    public static void main(String[] args) {
        //System.out.println(new Service().getAllCustomer().list.size());
        //System.out.println(new Service().deleteCustomer("cus_H1sCtmxg6jW6dM"));
        Customer newCustomer = new Customer("cus_H1tKflRCev4YuA", "max@web.de", "Max Georg", "012345", 100, "usd");
        String oldCustomerId = "cus_H1tKflRCev4YuA";
        System.out.println(new Service().updateCustomer(newCustomer, oldCustomerId));
    }
}
