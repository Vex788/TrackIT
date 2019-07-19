package trackit.demon.model;

public enum UserRole {
    ADMIN, USER, VIP, BANNED;

    @Override
    public String toString() {
        return name();
    }
}
