package com.sam.environment;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class MessageController implements RequestHandler<Void, Message> {
    @Override
    public Message handleRequest(Void nothing, Context context) {
        Message message = new Message();
        message.setMessage(System.getenv("MESSAGE"));
        return message;
    }
}
