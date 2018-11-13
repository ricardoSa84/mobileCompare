package com.example.rsa.androidnativobench.general;

public class AppConfig {
    public enum Enviroment {prod, dev};

    private static final Enviroment Env = Enviroment.dev;
    private static final String serverAddress = "https://vitasenior-test.eu-gb.mybluemix.net/";
    private static int serviceTimeout = 30000;
    private static String token = "";

    public static Enviroment getEnv() {
        return Env;
    }

    public static String getServerAddress() {
        return serverAddress;
    }

    public static int getServiceTimeout() {
        return serviceTimeout;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        AppConfig.token = token;
    }
}
