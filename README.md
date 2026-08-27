# Jmix Firebird Add-on

`jmix-firebird-addon` adds Firebird database support to Jmix applications.

The add-on integrates Firebird with the Jmix data layer and EclipseLink. It provides Firebird-specific DBMS features, type mappings, sequence support, and Liquibase properties required by Jmix applications.

## Compatibility

| Jmix | Add-on | Branch |
|------|--------|--------|
| 3.0.x | `3.0.x` | `main` |

The first two components of the add-on version identify the supported Jmix release line. The patch component identifies the add-on release within that line, starting with `0`. For example, add-on version `3.0.0` is the first release for Jmix 3.0.x.

## Add the Dependencies

Make sure that `mavenCentral()` and the Jmix repository are available:

```groovy
repositories {
    mavenCentral()
    maven { url = 'https://global.repo.jmix.io/repository/public' }
}
```

Add the Firebird starter and the Jaybird JDBC driver to the `dependencies` block in your Jmix project's `build.gradle`:

```groovy
dependencies {
    implementation 'io.github.makssent:jmix-firebird-starter:3.0.0'
    runtimeOnly 'org.firebirdsql.jdbc:jaybird:6.0.5'
}
```

The starter automatically includes the core `jmix-firebird` module. [Jaybird](https://github.com/FirebirdSQL/jaybird) is the JDBC driver used to connect the application to Firebird.

If the project uses the embedded HSQLDB database, remove this dependency:

```groovy
runtimeOnly 'org.hsqldb:hsqldb'
```

Both add-on modules are published to Maven Central:

- [jmix-firebird-starter](https://central.sonatype.com/artifact/io.github.makssent/jmix-firebird-starter)
- [jmix-firebird](https://central.sonatype.com/artifact/io.github.makssent/jmix-firebird)

## Configure the Data Source

Create an empty Firebird database before starting the application.

Configure the connection in `src/main/resources/application.properties`:

```properties
main.datasource.url=jdbc:firebird://localhost:3050//path/to/application.fdb?encoding=UTF8
main.datasource.username=YOUR_DB_USER
main.datasource.password=YOUR_DB_PASSWORD
main.datasource.driver-class-name=org.firebirdsql.jdbc.FBDriver

jmix.data.dbms-type=firebird
```

Replace the database path and credentials with the values for your environment.

The `jmix.data.dbms-type=firebird` property enables the Firebird-specific implementations provided by the add-on.

## Configure Liquibase

Include the add-on changelog before the Jmix module and application changelogs in the root `changelog.xml`:

```xml
<include file="/io/github/makssent/jmix/firebird/liquibase/changelog.xml"/>
```

The included changelog defines Firebird values for properties used by Jmix Liquibase scripts. Existing application changelogs may require additional adaptation for Firebird.

## Documentation

See the [Jmix Firebird documentation](https://github.com/makssent/jmix-firebird-docs/tree/release_firebird_3.0) for detailed instructions covering:

- connecting Firebird to an existing Jmix project;
- configuring the datasource;
- adapting and creating Liquibase changelogs;
- known limitations and Firebird-specific behavior;
- migrating an existing application to Firebird;
- a complete Jmix tutorial tested with Firebird.

The documentation is maintained in English and Russian.

For a ready-to-run project, see [jmix-firebird-application](https://github.com/makssent/jmix-firebird-application).

## License

The add-on is available under the [Apache License 2.0](LICENSE).
