package c24.thriftshop.stripe.demo.persistence;

public class DeletedMessage {
    String id;
    String object;
    boolean deleted;

    public DeletedMessage(String id, String object, boolean deleted) {
        this.id = id;
        this.object = object;
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
