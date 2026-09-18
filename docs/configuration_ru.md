# Установка и настройка

[English](configuration.md) | [Russian](configuration_ru.md)

Это руководство описывает настройку приложения Jmix для использования Firebird в качестве основного
хранилища данных. Поддерживаемые версии Jmix, аддона и Firebird приведены в
[таблице совместимости](../README_ru.md#совместимость).

## Предварительные требования

Перед началом подготовьте:

- работающий сервер Firebird;
- пустую базу данных, созданную до первого запуска приложения;
- пользователя базы данных с правами на создание и изменение объектов схемы (по умолчанию можно
  использовать `SYSDBA` с паролем `masterkey`).

## Подключение Firebird к приложению

### Зависимости Gradle

Добавьте стартер и Jaybird в блок `dependencies` файла `build.gradle` приложения:

```groovy
dependencies {
    implementation 'io.github.makssent:jmix-firebird-starter:3.0.1'
    runtimeOnly 'org.firebirdsql.jdbc:jaybird:6.0.5'
}
```

Jaybird - драйвер JDBC для подключения к базе данных.

После изменения зависимостей перезагрузите проект Gradle.

### Настройка datasource

Замените настройки основного datasource в `src/main/resources/application.properties`
на подключение к подготовленной базе данных Firebird:

```properties
main.datasource.url=jdbc:firebird://localhost:3050//path/to/application.fdb?encoding=UTF8
main.datasource.username=your_username
main.datasource.password=your_password
main.datasource.driver-class-name=org.firebirdsql.jdbc.FBDriver

jmix.data.dbms-type=firebird
```

Если при установке Firebird вы не меняли стандартные учётные данные администратора, можно указать
имя пользователя `sysdba` и пароль `masterkey`.

Замените `/path/to/application.fdb` на путь к вашей базе данных.

Свойство `jmix.data.dbms-type=firebird` обязательно, поскольку Jmix не определяет Firebird
автоматически по метаданным JDBC.

### Настройка Liquibase

Добавьте changelog аддона в корневой файл `changelog.xml` перед существующими подключениями
changelog-файлов модулей Jmix и приложения:

```xml
<include file="/io/github/makssent/jmix/firebird/liquibase/changelog.xml"/>

<include file="/io/jmix/data/liquibase/changelog.xml"/>
<!-- Changelog-файлы других модулей Jmix -->
<!-- Changelog-файлы приложения -->
```

Порядок подключения обязателен. Changelog аддона задаёт значения свойств Liquibase для Firebird,
используемые последующими скриптами.

Перед первым запуском с Firebird проверьте changelog-файлы приложения:

1. Для столбцов UUID используйте `${uuid.type}` вместо явно указанного типа `UUID`:

   ```xml
   <column name="ID" type="${uuid.type}">
       <constraints primaryKey="true" nullable="false"/>
   </column>
   ```

2. Если в существующем changeset задан список разрешённых СУБД в атрибуте `dbms`, добавьте в него
   `firebird`:

   ```xml
   <insert tableName="USER_" dbms="postgresql, mssql, hsqldb, firebird">
   ```

   Обычно это относится к вставкам начального администратора и назначений ролей, созданным
   шаблоном проекта Jmix.

## Заключение

После выполнения этих шагов можно запускать приложение с Firebird в качестве основной базы данных.
Если при запуске или дальнейшей работе возникают ошибки, ограничения или вопросы совместимости,
см. раздел [ограничения и устранение проблем](limitations-and-troubleshooting_ru.md).
