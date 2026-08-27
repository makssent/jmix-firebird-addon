# Jmix Firebird Add-on

`jmix-firebird-addon` enables Jmix projects to use Firebird as their primary application database.

## Compatibility

Add-on version `3.0.0` is compatible with Jmix 3.0.x.

The first two version components identify the supported Jmix line: add-on versions `3.0.x` support Jmix 3.0.x, `3.1.x` support Jmix 3.1.x, and `3.2.x` support Jmix 3.2.x. The last component is the add-on release number within that line. For example, `3.0.0` is the first release for Jmix 3.0.x, `3.0.1` is the next release, and `3.1.0` is the first release for Jmix 3.1.x.

## Installation

Add the Firebird starter and the Jaybird JDBC driver to your project's `build.gradle`:

```groovy
dependencies {
    implementation 'io.github.makssent:jmix-firebird-starter:3.0.0'
    runtimeOnly 'org.firebirdsql.jdbc:jaybird:6.0.5'
}
```

[Jaybird](https://github.com/FirebirdSQL/jaybird) must be added separately. It is the JDBC driver used to connect the application to Firebird.

The add-on is published to Maven Central:

- [jmix-firebird-starter](https://central.sonatype.com/artifact/io.github.makssent/jmix-firebird-starter)
- [jmix-firebird](https://central.sonatype.com/artifact/io.github.makssent/jmix-firebird)

## Documentation

See the [Jmix Firebird documentation](https://github.com/makssent/jmix-firebird-docs/tree/release_firebird_3.0) for complete setup and usage instructions.
