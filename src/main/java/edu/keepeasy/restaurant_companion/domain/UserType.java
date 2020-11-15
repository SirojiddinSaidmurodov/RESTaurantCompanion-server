package edu.keepeasy.restaurant_companion.domain;

public enum UserType {
    UNDEFINED(0),
    ADMIN(1),
    COOK(2),
    WAITER(3);
    private final int value;

    UserType(int value) {
        this.value = value;
    }

    public static UserType fromInteger(int value) {
        return switch (value) {
            case 1 -> ADMIN;
            case 2 -> COOK;
            case 3 -> WAITER;
            default -> UNDEFINED;
        };
    }

    public int getValue() {
        return value;
    }
}
