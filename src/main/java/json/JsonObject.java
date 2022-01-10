package json;

import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private HashMap<String, Json> json_pairs = new HashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair pair: jsonPairs) {
            json_pairs.put(pair.key, pair.value);
        }
    }

    @Override
    public String toJson() {
        return "{" + getJsonObjBody() + "}";
    }

    private String getJsonObjBody() {
        StringBuilder jsonStr = new StringBuilder();
        Iterator<Map.Entry<String, Json>> jsonIterator = json_pairs.entrySet().iterator();
        while (jsonIterator.hasNext()) {
            Map.Entry<String, Json> jsonPair = jsonIterator.next();
            jsonStr.append(jsonPair.getKey());
            jsonStr.append(": ");
            jsonStr.append(jsonPair.getValue().toJson());
            if (jsonIterator.hasNext()) {
                jsonStr.append(", ");
            }
        }
        return jsonStr.toString();
    }

    public void add(JsonPair jsonPair) {
        json_pairs.put(jsonPair.key, jsonPair.value);
    }

    public boolean contains(String name) {
        return  json_pairs.containsKey(name);
    }

    public Json find(String name) {
        return json_pairs.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject json = new JsonObject();
        for (String key : names) {
            if (json_pairs.containsKey(key)) {
                json.add(new JsonPair(key, json_pairs.get(key)));
            }
        }
        return json;
    }
}
