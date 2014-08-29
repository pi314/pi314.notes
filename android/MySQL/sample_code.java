private Connection connect = null;
private Statement statement = null;
private ResultSet resultSet = null;

public static final String MYSQL_IP = "192.168.0.100";
public static final String MYSQL_DBNAME = "Book";
public static final String MYSQL_USERNAME = "isken";
public static final String MYSQL_PASSWORD = "isken";

public ArrayList bookList() throws Exception {
    ArrayList results = new ArrayList();
    try {
        String script = "SELECT id, name, URL FROM Book";
        Log.e("Isken", "script = "+script);

        // This will load the MySQL driver, each DB has its own driver
        Class.forName("com.mysql.jdbc.Driver");

        Log.e("Isken", "jdbc:mysql://"+MYSQL_IP+"/+MYSQL_DBNAME+?"+ "user="+MYSQL_USERNAME+"&password="+MYSQL_PASSWORD);

        // Setup the connection with the DB
        connect = (Connection) DriverManager.getConnection("jdbc:mysql://"+MYSQL_IP+"/+MYSQL_DBNAME+?"+ "user="+MYSQL_USERNAME+"&password="+MYSQL_PASSWORD);

        Log.e(PAGETAG, "connection is success");

        // Statements allow to issue SQL queries to the database
        statement = (Statement) connect.createStatement();

        // Result set get the result of the SQL query
        resultSet = statement.executeQuery(script);

        while (resultSet.next()) {
            MyObj obj = new MyObj();
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String url = resultSet.getString("URL");
            obj.setId(id);
            obj.setName(name);
            obj.setURL(url);
            results.add(obj);
        }
        Log.e(PAGETAG, "results size = "+results.size());
    } catch (Exception e) {
        throw e;
    } finally {
        close();
    }
    return results;
}
