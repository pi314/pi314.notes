HashSet<String> s = new HashSet<String>();

s.add("test1");
s.add("test2");

s.contains("test1");    // true
s.contains("XD");       // false

====

private class FeatureIndex {
    String feature_name;
    int index;
    
    @Override
    public int hashCode() {
        return feature_name.hashCode();
    }
}

HashSet<FeatureIndex> a;

a.contains("a");    // it will always FAIL even if you override hashCode() and equals()
