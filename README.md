[![Apache 2.0 License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
# Firebird add-on for Jmix

The add-on enables Jmix applications to use Firebird as the primary data store.

> [!IMPORTANT]
> For Firebird projects, Jmix Studio Run Action and automatic Liquibase changelog generation for
> model changes are unavailable. See
> [limitations and troubleshooting](docs/limitations-and-troubleshooting.md) for details.

## Compatibility

| Jmix | Add-on | Firebird |
| --- | --- | --- |
| 3.0.x | `3.0.1` | 4.0+ |

The first two add-on version components identify the supported Jmix line. The last component
identifies the add-on release within that line.

## Connecting the add-on

To manually add the add-on to an existing Jmix project, add the starter to the `dependencies` block
in `build.gradle`:

```groovy
dependencies {
    implementation 'io.github.makssent:jmix-firebird-starter:3.0.1'
}
```

Include the add-on changelog in the root `changelog.xml` before the Jmix and application changelog
includes:

```xml
<include file="/io/github/makssent/jmix/firebird/liquibase/changelog.xml"/>
```

The add-on changelog declares Firebird values for Liquibase properties used by the following
changelogs, so this include must precede them.

The complete process for configuring a Jmix project for Firebird, including database preparation,
the JDBC driver, datasource, Jmix properties, Liquibase, and the remaining steps required to start
the application, is described in the
[installation and configuration guide](docs/configuration.md).

## Examples and additional resources

- [jmix-firebird-tutorial](https://github.com/makssent/jmix-firebird-tutorial) - a detailed tutorial
  and extended documentation for using Jmix with Firebird.
- [jmix-crm-firebird](https://github.com/makssent/jmix-crm-firebird) - the real `jmix-crm`
  application adapted to work with Firebird.
- [jmix-firebird-application](https://github.com/makssent/jmix-firebird-application) - a minimal,
  ready-to-use Jmix application with Firebird support configured.
