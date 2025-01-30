Run test standalone without running application without saving in DB

    1> Make Sure H2 DB is added as dependency. If some other database is already present then add H2 as test dependency
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>test</scope>
        </dependency>
    2> 
    3> If the app is already running on H2 don't keep H2 again in test
    4> H2 DB details will be picked from application.properties under test/resources directory
    5> Schema will be loaded from test.schema.sql under test/resources directory
    6> It will run even if the application is running or stopped and test with H2

Run tests with actual Database making inserts

    1> Keep actual DB not H2
    2> Remove H2 from dependecny
    3> Remove below from test class
    @JdbcTest
    @Sql(scripts = "classpath:schema/test-schema.sql")
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    4> Add below annotations in test class
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    public class ProfileRepositoryIntegrationTest 


