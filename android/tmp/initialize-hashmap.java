import java.util.HashMap

class Demo {
    static final HashMap<String, String[]> demo_map = new HashMap<String, String[]() {{
        put("key1", new String[]{"value1-1", "value1-2"});
        put("key2", new String[]{"value2-1"});
        put("key3", new String[]{"value3-1", "value3-2", "value3-3"});
    }};
}

// http://www.softwaregeek.net/2012/12/double-brace-initialization-in-java.html
// Keyword: double brace initialization

// {{}} 實際上會繼承產生一個 anounymous class，內容是 Constructor
// 這個方法可能會造成 Memory Leak，以及 .equals() 和 .hashcode() 會失效
