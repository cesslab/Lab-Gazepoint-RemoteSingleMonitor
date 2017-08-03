package edu.nyu.cess.gazepoint;

import java.util.HashMap;
import java.util.Map;

// TODO: Add message validation
public class Message {
    static String messageToTag(String message) {
        String msg = message.trim();
        return msg.substring(1,4);
    }

    public static boolean isResponse(String message, String id) {
        String tag = Message.messageToTag(message);
        if (!tag.equals(Tag.ACKNOWLEDGE)) {
            return false;
        }

        Map<String, String> pairs = Message.messageToMap(message);
        return pairs.containsKey(Attribute.ID) && pairs.get(Attribute.ID).equals(id);
    }

    public static boolean hasIdValue(String message, String idValue) {
        Map<String, String> kvp = messageToMap(message);
        return kvp.containsKey(Attribute.ID) && kvp.get(Attribute.ID).equals(idValue);
    }

    public static boolean hasTag(String message, String tag) {
        return Message.messageToTag(message).equals(tag);
    }

    private static Map<String, String> messageToMap(String message) {
        String[] split = message.trim().substring(5, message.length() - 3).replace("\"", "").split(" ");

        HashMap<String, String> mutablePairs = new HashMap<>();

        for (String aSplit : split) {
            String[] keyValue = aSplit.split("=");
            mutablePairs.put(keyValue[0], keyValue[1]);
        }
        mutablePairs.put("TYPE", messageToTag(message));

        return mutablePairs;
    }
}
