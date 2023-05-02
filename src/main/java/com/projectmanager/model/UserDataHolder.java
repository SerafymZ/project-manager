package com.projectmanager.model;

public class UserDataHolder {

    private static final ThreadLocal<UserData> DATA_HOLDER = new InheritableThreadLocal<>();

    private UserDataHolder() {}

    public static UserData init(Long userId) {
        DATA_HOLDER.remove();

        var userData = new UserData(userId);
        DATA_HOLDER.set(userData);

        return userData;
    }


    public static UserData getUserData() {
        UserData userData = DATA_HOLDER.get();

        if (userData == null) {
            userData = new UserData();
            DATA_HOLDER.set(userData);
        }

        return userData;
    }

    public static void clearUserData() {
        DATA_HOLDER.remove();
    }

    public static void setUserData(UserData userData) {
        if(userData != null) {
            DATA_HOLDER.set(userData);
        }
    }
}