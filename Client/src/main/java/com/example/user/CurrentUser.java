package com.example.user;

public class CurrentUser {
    public static String login;
    public static String password;
    public static String isAdmin;

    public static class SelectedUser {
        public static String login;
        public static String password;
        public static String isAdmin;

        public static void setSelectedUser (String newLogin, String newPassword, String newAdmin) {
            login = newLogin;
            password = newPassword;
            isAdmin = newAdmin;
        }
    }

    public static void setCurrentUser (String newLogin, String newPassword, String newAdmin) {
        login = newLogin;
        password = newPassword;
        isAdmin = newAdmin;
    }
}
