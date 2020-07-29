# Athena Development

If you want to build **athena** from source , follow the steps mentioned here.

## System Requirements (for building and running Thingverse)

- Memory: 2 GB minimum, 8 GB recommended
- CPU Cores: 2 Cores, 4 Cores recommended
- Docker: Version 19+ (if you want to run tests locally)
- Java: JDK 11
- Gradle: Bundled via wrapper, but locally installed Gradle v1.65 helps.

> You would need credentials of the Amazon Redshift cluster. For running tests locally, you won't need Redshift since 
> local integration tests use a local PostgreSQL DB. You don't need to install PostgreSQL locally, just leave your
> Docker daemon running (the tests will automatically do the rest)

## Build and run tests
- The integration tests would require your Docker daemon to be running. (Install Docker Desktop locally and start it)
- Now issue the following commands in a terminal window to checkout code and build.

    ``` 
    # Checkout from GitHub
    git clone git@github.com:arunkpatra/athena.git
  
    # Build
    cd athena
    ./gradlew assemble
  
    # Run tests
    ./gradle check
    ```

## Running the Athena API App

- Export environment variables
    ``` 
    export DB_URL=<your_redhisht_jdbc_url>
    export DB_USER=<your_readshift_cluster_user>
    export DB_PASSWORD=<your_readshift_cluster_password>
    ``` 
- Starting the Athena Spring Boot app (the REST API app)
    * Ensure that your Redshift cluster is running and shows an `Available` status. Make sure to setup permissions based
    on your environment, and you have IAM roles setup correctly. The end result should be that, **you should be able to
    access Redshift via the Redshift JDBC driver from the machine and network you intend to start the Athena REST API app**.
    * Ensure that you have loaded data into the S3 bucket. See `subprojects/athena/redshift-data-load.sql` for bucket 
    and file names. You can upload all the files in the `subprojects/athena/data/` directory to your S3 bucket. 
    Provide permissions as necessary.
    * Ensure that you have run the `subprojects/athena/athena-schema.sql` script. If you run this in the Redshift Editor directly.
    * Now run the Athena REST APi app
        ``` 
        cd athena
        ./gradlew bootRun
        ```
    * Access **Swagger UI** at http://localhost:8080/swagger-ui.html. You should be able to invoke API calls.