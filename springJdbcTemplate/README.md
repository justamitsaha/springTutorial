Run test standalone without running application without saving in DB

    1> Make Sure H2 DB is added as dependency. If some other database is already present then add H2 as test dependency
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>test</scope>
        </dependency>
    2> If the app is already running on H2 don't keep H2 again in test
    3> H2 DB details will be picked from application.properties under test/resources directory
    4> Schema will be loaded from test.schema.sql under test/resources directory
    5> It will run even if the application is running or stopped and test with H2

Run tests with actual Database making inserts

    1> 


