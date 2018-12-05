package com.serverless.bookstore.datalyer.modules;

import java.util.UUID;

public class Id {
    public static String getId() {
        return  UUID.randomUUID().toString();
    }
}
