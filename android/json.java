/* Iterate over a JSONObject */
Iterator<String> iter = json.keys();
while (iter.hasNext()) {
    String key = iter.next();
    try {
        Object value = json.get(key);
    } catch (JSONException e) {
        // Something went wrong!
    }
}

/* Check data type in a JSONObject */
JSONObject obj = new JSONObject(content);
Object thing = stuff.get("key");
String class_name = thing.getClass().getName();
Systen.out.println("thing is a " + class_name);
if (thing instanceof Integer) {
    System.out.println("thing is an Integer");
}
