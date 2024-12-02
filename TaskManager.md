# Менеджер задач
https://github.com/ElvinTM/my-project/tree/master/TaskManager/src/main
___
### Описание проекта
Веб приложение для составление списка задач про принципу __CRUD__. Архитектура разделена на слои. 
Приложение может:
+ создавать задачу
+ редактировать задачу
+ удалять задачу
### Запуск приложения
Для запуска нужно скачать репозиторий с помощью команды
```
$ git clone {https://github.com/ElvinTM/my-project.git}
```
Либо скачать тут файл [ZIP](https://github.com/ElvinTM/my-project/archive/refs/heads/master.zip)

Приложение работает на http://localhost:8080/, однако порт можно поменять в __application.properties__.
Данный файл находиться по пути ___TaskManager/src/main/resources/application.properties___
```yaml
server.port = 8080
```
для этого нужно порт 8080 изменить на любой другой свободный порт для корректного запуска.

### Библиотеки
В работе использовались следующие библиотеки:
* SpringBoot
* Lombok
* Thymeleaf

Для тестирования использовался:
* SpringBootTest
