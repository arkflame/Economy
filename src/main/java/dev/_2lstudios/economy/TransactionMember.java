package dev._2lstudios.economy;

public class TransactionMember {
    private String id;
    private String uuid;
    private String name;

    public TransactionMember(String id, String uuid, String name) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}
