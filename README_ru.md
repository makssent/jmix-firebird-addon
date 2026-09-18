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

## Установка и настройка

Аддон можно установить из [Jmix Marketplace](https://www.jmix.ru/marketplace/firebird-db/)
или подключить вручную, как описано ниже.

<details>
<summary>Ручная установка</summary>

При ручной установке добавьте стартер в блок `dependencies` файла `build.gradle`
приложения:

```groovy
dependencies {
    implementation 'io.github.makssent:jmix-firebird-starter:3.0.1'
}
```

</details>

### 1. Подготовьте базу данных

Подготовьте работающий сервер Firebird и создайте пустую базу данных до первого запуска приложения.
Пользователь базы данных должен иметь права на подключение, создание и изменение объектов схемы.

### 2. Настройте зависимости Gradle

Добавьте Jaybird в блок `dependencies` файла `build.gradle` приложения.
Jaybird - драйвер JDBC для подключения к Firebird, его нужно добавить отдельно:

```groovy
runtimeOnly 'org.firebirdsql.jdbc:jaybird:6.0.5'
```

После изменения зависимостей перезагрузите проект Gradle.

### 3. Настройте datasource и Jmix

Замените настройки основного datasource в `src/main/resources/application.properties`
на подключение к подготовленной базе данных Firebird:

```properties
main.datasource.url=jdbc:firebird://localhost:3050//path/to/application.fdb?encoding=UTF8
main.datasource.username=your_username
main.datasource.password=your_password
main.datasource.driver-class-name=org.firebirdsql.jdbc.FBDriver

jmix.data.dbms-type=firebird
```

Замените адрес сервера, порт, путь к базе данных и учётные данные своими значениями. Путь к базе
данных определяется на сервере Firebird, а не на машине, где запущено приложение.

Если при установке Firebird вы не меняли стандартные учётные данные администратора, можно указать
имя пользователя `sysdba` и пароль `masterkey`.

Свойство `jmix.data.dbms-type=firebird` обязательно, поскольку Jmix не определяет Firebird
автоматически по метаданным JDBC.

### 4. Настройте Liquibase

Подключите changelog аддона в корневом файле `changelog.xml` перед всеми changelog-файлами модулей
Jmix и приложения. Если подключение уже добавлено, проверьте его расположение, не добавляя повторно:

```xml
<include file="/io/github/makssent/jmix/firebird/liquibase/changelog.xml"/>

<include file="/io/jmix/data/liquibase/changelog.xml"/>
<!-- Changelog-файлы других модулей Jmix -->
<!-- Changelog-файлы приложения -->
```

Changelog аддона задаёт значения свойств Liquibase для Firebird, используемые последующими
changelog-файлами, поэтому его необходимо подключить перед ними.

Перед первым запуском с Firebird адаптируйте changelog-файлы приложения:

1. Для столбцов UUID используйте `${uuid.type}` вместо явно указанного типа `UUID`:

   ```xml
   <column name="ID" type="${uuid.type}">
       <constraints primaryKey="true" nullable="false"/>
   </column>
   ```

2. Добавьте `firebird` в списки разрешённых СУБД в атрибутах `dbms` для изменений, которые должны
   выполняться на Firebird. В частности, проверьте вставки начального администратора и назначений
   ролей, созданные шаблоном проекта Jmix:

   ```xml
   <insert tableName="USER_" dbms="postgresql, mssql, hsqldb, firebird">
   ```

### 5. Запустите приложение

Запускайте приложение из командной строки, поскольку Run Action в Jmix Studio недоступен
для проектов с Firebird:

```bash
./gradlew bootRun
```

После изменения модели сущностей создавайте и подключайте changeset-файлы Liquibase вручную.
Studio не может генерировать их автоматически для Firebird. Известные проблемы совместимости
библиотек и ошибки запуска описаны в разделе
[ограничения и устранение проблем](docs/limitations-and-troubleshooting.md).

## Примеры и дополнительные материалы

- [jmix-firebird-tutorial](https://github.com/makssent/jmix-firebird-tutorial) - подробное руководство
  и расширенная документация по использованию Jmix с Firebird.
- [jmix-crm-firebird](https://github.com/makssent/jmix-crm-firebird) - реальное приложение `jmix-crm`,
  адаптированное для работы с Firebird.
- [jmix-firebird-application](https://github.com/makssent/jmix-firebird-application) - минимальное,
  готовое к использованию приложение Jmix с настроенной поддержкой Firebird.
