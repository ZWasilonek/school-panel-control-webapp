package pl.codeschool.model;

public enum Admin {

    ADMIN_USERNAME("ADMIN"),
    ADMIN_GROUP("ADMIN Group");

    private final String description;

    Admin(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static String getAdminUsername() {
        return String.valueOf(Admin.ADMIN_USERNAME.description);
    }

    public static String getAdminGroup() {
        return String.valueOf(Admin.ADMIN_GROUP.description);
    }

}