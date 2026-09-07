# Limitations and troubleshooting

## Main limitations when using Firebird

When using Firebird, two Jmix Studio features are unavailable:

- **Application startup.** Run Action is unavailable for a Firebird project because of the database
  schema check performed before startup, so the application must be started from the command line.
- **Automatic Liquibase changelog generation.** Studio cannot compare the entity model with the
  Firebird schema and generate changesets for model changes, so the changelog must be created
  manually.

These are the main practical limitations of the Studio integration. They do not affect the runtime
operation of the add-on or the application with Firebird.

## Other known limitations

The remaining limitations affect individual operations or depend on the versions of the libraries
in use, and usually do not prevent the application from starting.

### Liquibase and EclipseLink support

Both libraries support Firebird, but some checks and SQL generation mechanisms were designed for
older Firebird releases. An error from a specific Liquibase change type or EclipseLink operation may
be an upstream library compatibility issue.

## Support boundaries

Firebird support is provided by this community add-on and is maintained separately from Jmix.
Firebird is not one of the databases officially supported by the Jmix team, so new Jmix, Liquibase,
or EclipseLink releases can introduce compatibility issues that require add-on updates.

The current add-on line targets Firebird 4.0+. Jaybird 6 can connect to Firebird 3.0, but the
JDBC driver's support range alone does not make the add-on compatible with Firebird 3.0. The add-on
uses Firebird capabilities introduced in Firebird 4.0, including time-zone data types and modern
sequence syntax.

## Troubleshooting

### Starting the application with Firebird

To start the application without Studio Run Action, use the regular Gradle task from the command
line:

```bash
./gradlew bootRun
```

### Entity model changes and Liquibase changelogs

After changing the entity model, create, include, and verify the corresponding Liquibase changesets
manually. The changelog include procedure is described in the
[configuration guide](configuration.md#liquibase-configuration).

### Jmix does not select the Firebird implementation

**Symptoms:** startup reports an unsupported database type, or database-specific services are not
selected.

**Check:** `application.properties` must contain:

```properties
jmix.data.dbms-type=firebird
```

Also verify that `jmix-firebird-starter` is present in the runtime classpath.

### The Firebird JDBC driver is missing

**Symptoms:** `org.firebirdsql.jdbc.FBDriver` cannot be loaded, or no suitable JDBC driver is found.

**Check:** add Jaybird as a runtime dependency and reload Gradle:

```groovy
runtimeOnly 'org.firebirdsql.jdbc:jaybird:6.0.5'
```

### The application cannot open the database

**Symptoms:** Firebird reports that the database is unavailable or the file cannot be opened.

**Check:** create the database before starting Jmix, verify host and port, and remember that the path
in the JDBC URL is resolved on the Firebird server. Verify the configured user can connect and can
create schema objects.

### Liquibase uses the wrong type for UUID or another Jmix property

**Symptoms:** schema creation fails on `UUID`, or a Jmix module changelog cannot resolve a type
property.

**Check:** include the add-on changelog before every Jmix module and application changelog:

```xml
<include file="/io/github/makssent/jmix/firebird/liquibase/changelog.xml"/>
```

Use `${uuid.type}` for application UUID columns.

### The initial administrator is missing

**Symptoms:** the schema is created, but the initial Jmix user cannot sign in.

**Check:** template changesets that insert the administrator and role assignment often have a
`dbms` allowlist. Add `firebird` to that list when those inserts must run on Firebird.

### Liquibase rejects `createSequence` with `startValue`

**Symptom:** Liquibase reports `startValue is not allowed on firebird` even though Firebird 4.0 and
newer support sequence start values.

This restriction comes from the Liquibase version, not from Jmix or modern Firebird. If upgrading to
a Liquibase version containing the fix is not possible, isolate a temporary Firebird-specific
workaround:

```xml
<changeSet id="create-sequence" author="app" dbms="!firebird">
    <createSequence sequenceName="APP_NUMBER" startValue="1000"/>
</changeSet>

<changeSet id="create-sequence-firebird" author="app" dbms="firebird">
    <createSequence sequenceName="APP_NUMBER"/>
    <sql>ALTER SEQUENCE APP_NUMBER RESTART WITH 1000</sql>
</changeSet>
```

Remove the workaround after moving to a Liquibase version that handles the target Firebird version
correctly. The upstream fix is tracked in
[liquibase/liquibase#7922](https://github.com/liquibase/liquibase/pull/7922).

## Further help

For extended examples, including manual changelog authoring and a complete application migration,
see the separate
[Jmix Firebird tutorial](https://github.com/makssent/jmix-firebird-tutorial/tree/release_firebird_3.0).
