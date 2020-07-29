# Athena Development

If you want to build **athena** from source , follow the steps mentioned here.

## System Requirements (for building and running Thingverse)

- Memory: 2 GB minimum, 8 GB recommended
- CPU Cores: 2 Cores, 4 Cores recommended
- Docker: Version 19+ (if you want to run tests locally)
- Java: JDK 11
- Gradle: Bundled via wrapper, but locally installed Gradle v1.65 helps.

> You would need credentials of the Redshift cluster. For running tests locally, you won't need redshift since 
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
    export DB_URL=jdbc:redshift://examplecluster2.coibnwwmkdkh.ap-south-1.redshift.amazonaws.com:5439/dev
    export DB_USER=<your_readshift_cluster_user>
    export DB_PASSWORD=<your_readshift_cluster_password>
    ``` 
- Start the athena Spring Boot app (the REST API app)
    ``` 
    cd athena
    ./gradlew bootRun
    ```
