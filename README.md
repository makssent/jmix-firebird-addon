# Jmix Firebird Add-on

`jmix-firebird-addon` enables Jmix projects to use Firebird as their primary application database.

## Compatibility

| Jmix  | Available add-on version |
| ----- | ------------------------ |
| 3.0.x | `3.0.1`                  |

Future add-on versions will follow this pattern:

| Jmix line | Add-on version pattern        |
| --------- | ----------------------------- |
| 3.0.x     | `3.0.0`, `3.0.1`, `3.0.2`... |
| 3.1.x     | `3.1.0`, `3.1.1`, `3.1.2`... |
| 3.2.x     | `3.2.0`, `3.2.1`, `3.2.2`... |

The first two version components match the supported Jmix line. The last component is the add-on release number within that line. `3.0.0` is the first release for Jmix 3.0.x, and `3.0.1` is the current release for the same line. The 3.1.x and 3.2.x rows show the future versioning rule; these versions are not currently available.

## Usage

To use the add-on, add `jmix-firebird-starter` and the Jaybird JDBC driver to your project's `build.gradle`:

```groovy
dependencies {
    implementation 'io.github.makssent:jmix-firebird-starter:3.0.1'
    runtimeOnly 'org.firebirdsql.jdbc:jaybird:6.0.5'
}
```

[Jaybird](https://github.com/FirebirdSQL/jaybird) must be added separately. It is the JDBC driver used to connect the application to Firebird.

### Maven Central

`jmix-firebird-starter` is published to Maven Central:

- [jmix-firebird-starter](https://central.sonatype.com/artifact/io.github.makssent/jmix-firebird-starter)

## Documentation

See the [Jmix Firebird documentation](https://github.com/makssent/jmix-firebird-docs/tree/release_firebird_3.0) for complete setup and usage instructions.
