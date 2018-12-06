package com.serverless.bookstore.security.builders;

import com.serverless.bookstore.security.modules.Policy;
import com.serverless.bookstore.security.modules.PolicyDocument;
import com.serverless.bookstore.security.modules.Statement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * IAM Policies use capitalized field names, but Lambda by default will serialize object members using camel case
 *
 * This method implements a custom serializer to return the IAM Policy as a well-formed JSON document, with the correct field names
 * */
public class PolicyDocumentSerializer {
    // IAM Policy Constants
    public static final String VERSION = "Version";
    public static final String STATEMENT = "Statement";
    public static final String EFFECT = "Effect";
    public static final String ACTION = "Action";
    public static final String NOT_ACTION = "NotAction";
    public static final String RESOURCE = "Resource";
    public static final String NOT_RESOURCE = "NotResource";
    public static final String CONDITION = "Condition";
    public static Map<String, Object> serialize(PolicyDocument policyDocumentObject) {
        Map<String, Object> serializablePolicy = new HashMap<>();
        serializablePolicy.put(VERSION, policyDocumentObject.getVersion());
        List<Statement> statements = policyDocumentObject.getStatement();
        Map<String, Object>[] serializableStatementArray = new Map[statements.size()];
        int i = 0;
        for (Statement statement : statements) {
            Map<String, Object> serializableStatement = new HashMap<>();
            serializableStatement.put(EFFECT, statement.getEffect());
            serializableStatement.put(ACTION, statement.getAction());
            serializableStatement.put(RESOURCE, statement.getResource());
            //serializableStatement.put(CONDITION, statement.getCondition());
            serializableStatementArray[i] = serializableStatement;
            i++;
        }
        serializablePolicy.put(STATEMENT, serializableStatementArray);
        return serializablePolicy;
    }
}
