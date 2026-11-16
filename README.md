# Структурные паттерны

Установите JDK 22 и настройте `JAVA_HOME` и `Path` в Windows. Откройте PowerShell в папке проекта.

Установка зависимостей и сборка:

```powershell
.\mvnw.cmd package
```

Запуск всех примеров:

```powershell
.\mvnw.cmd exec:java
```

Запуск одного примера:

```powershell
.\mvnw.cmd exec:java "-Dexec.args=Adapter"
```

Список примеров:

```powershell
.\mvnw.cmd exec:java "-Dexec.args=--list"
```
