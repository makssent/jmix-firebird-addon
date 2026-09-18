[![Apache 2.0 License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
# Аддон Firebird для Jmix

[English](README.md) | [Russian](README_ru.md)

Аддон позволяет приложениям Jmix использовать Firebird в качестве основного хранилища данных.

> [!IMPORTANT]
> Для проектов с Firebird недоступны Run Action в Jmix Studio и автоматическая генерация
> changelog-файлов Liquibase при изменении модели. Подробнее см. в разделе
> [ограничения и устранение проблем](docs/limitations-and-troubleshooting.md).

## Совместимость

| Jmix | Аддон | Firebird |
| --- | --- | --- |
| 3.0.x | `3.0.1` | 4.0+ |

Первые два компонента версии аддона определяют поддерживаемую линейку Jmix. Последний компонент
обозначает выпуск аддона в рамках этой линейки.

## Подключение аддона

Аддон можно установить из [Jmix Marketplace](https://www.jmix.ru/marketplace/firebird-db/)
или подключить вручную, как описано ниже.

> [!IMPORTANT]
> Установка аддона из Marketplace или ручное добавление зависимости и подключение changelog
> не заменяют полную настройку Firebird. Выполните все шаги из
> [руководства по установке и настройке](docs/configuration.md), включая подготовку базы данных,
> настройку драйвера JDBC, datasource, свойств Jmix и Liquibase.

Чтобы вручную подключить аддон к существующему проекту Jmix, добавьте стартер в блок `dependencies`
файла `build.gradle`:

```groovy
dependencies {
    implementation 'io.github.makssent:jmix-firebird-starter:3.0.1'
}
```

Подключите changelog аддона в корневом файле `changelog.xml` перед подключением changelog-файлов
Jmix и приложения:

```xml
<include file="/io/github/makssent/jmix/firebird/liquibase/changelog.xml"/>
```

Changelog аддона задаёт значения свойств Liquibase для Firebird, используемые последующими
changelog-файлами, поэтому его необходимо подключить перед ними.

## Примеры и дополнительные материалы

- [jmix-firebird-tutorial](https://github.com/makssent/jmix-firebird-tutorial) - подробное руководство
  и расширенная документация по использованию Jmix с Firebird.
- [jmix-crm-firebird](https://github.com/makssent/jmix-crm-firebird) - реальное приложение `jmix-crm`,
  адаптированное для работы с Firebird.
- [jmix-firebird-application](https://github.com/makssent/jmix-firebird-application) - минимальное,
  готовое к использованию приложение Jmix с настроенной поддержкой Firebird.
