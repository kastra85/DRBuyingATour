# Тестирование веб-сервиса "Путешествие дня"

Автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка

## Начало работы

### Prerequisites

Для работы с проектом требуется установка на персональный компьютер (далее - ПК) следующих программ: система контроля версий Git, профессиональный редактор кода IntelliJ IDEA Community, Docker

### Установка и запуск

Скачиваем и устанавливаем на свой ПК [Git](https://git-scm.com/downloads)

Скачиваем и устанавливаем на свой ПК профессиональный редактор кода [Intellij Idea Community Version](https://www.jetbrains.com/idea/download/)

Скачиваем и устанавливаем Docker в следующем порядке:

1. Регистрируемся (получаем Docker ID) на [Docker Hub](https://hub.docker.com/):

Выбираем `Sign Up`:

![](pic/signup.png)

Заполняем форму, регистрируемся.

2. Определяемся с вашей ОС и версией:
* Пользователи Windows 10 и Windows 11 начиная с версии 21H2 - вам нужен Docker Desktop. Установка описана [здесь](https://docs.docker.com/docker-for-windows/install/).
* Пользователи MacOS начиная с Big Sur - вам нужен Docker Desktop. Установка описана [здесь](https://docs.docker.com/docker-for-mac/install/)
* Пользователи Linux, в зависимости от дистрибутива: [Ubuntu](https://docs.docker.com/install/linux/docker-ce/ubuntu/), [Debian](https://docs.docker.com/install/linux/docker-ce/debian/). Не забудьте так же про [Post Installation](https://docs.docker.com/install/linux/linux-postinstall/)
* Пользователи Windows 7, Windows 8, неподдерживаемых версий Windows 10, Windows 11 и MacOS могут использовать виртуальную машину, предоставляемую Нетологией. [Инструкция по подключению к виртуальной машине Linux](project documentation/timeweb-instruction.md).

После установки программ клонируем командой `git clone` из удаленного репозитория CitHub проект на свой ПК и создаем локальный репозиторий (, далее запускаем его в следующем порядке:
* Запуск Docker Desktop
* Запуск контейнеров командой `docker compose up` в терминале IDEA
* Запуск приложения командой `java -jar ./artifacts/aqa-shop.jar` в терминале IDEA
* Запуск втотестов командой `./gradlew test` в терминале IDEA

## Лицензия

Для работы с вышеуказанными программами в рамках проекта достаточно бесплатных версий

## Документация по итогам тестирования

* [Plan.md](project%20documentation%2FPlan.md)
* [Report.md](project%20documentation%2FReport.md)
* [Summary.md](project%20documentation%2FSummary.md)
