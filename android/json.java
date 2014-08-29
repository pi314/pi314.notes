JSONObject stuff = new JSONObject(whatever);
Object thing = stuff.get("key");
String classNameOfThing = thing.getClass().getName();
Systen.out.println("thing is a " + classNameOfThing);
if (thing instanceof Integer) {
    System.out.println("thing is an Integer");
} 
