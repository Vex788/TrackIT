package trackit.demon.model;

public enum UserRole {
    ADMIN, USER, VIP, ANONYMOUS, BANNED;

    @Override
    public String toString() {
        return name();
    }
}
