# VK Bot

Это проект на Spring Boot для создания VK бота, который повторяет отправленные ему сообщения.

## Содержание
- [Описание проекта](#VkBot)
- [Установка](#Установка)
- [Требования](#требования)
- [Запуск](#запуск)

## Требования

- Java 11 или новее
- Maven
- Ngrok (для проксирования локального сервера)

## Установка

1. Клонируйте репозиторий:

```bash
git clone https://github.com/MrKirya/vkbot.git
cd vkbot
 ```

2 . Создайте файл .env в корне проекта и добавьте туда ваш токен доступа VK:
```bash
VK_API_TOKEN=ваш_токен_доступа
CONFIRMATION_CODE=ваш_код_подтверждения
SECRET_KEY=секретный_ключ
 ```

3. Настройте application.yml для использования токена из .env:
```bash

spring:
  application:
    name: "Your project name"
  config:
    import: optional:file:.env[.properties]


vk:
  token: ${VK_TOKEN}
  confirmation: ${CONFIRMATION_CODE}
  secret: ${SECRET_KEY}

server:
  port: 8080
 ```

## Запуск
1. Соберите и запустите проект с помощью Maven:
```bash
mvn clean install
mvn spring-boot:run
```
2. Установите и запустите Ngrok для проксирования вашего локального сервера:
```bash
ngrok http 8080
```
Ngrok предоставит вам публичный URL, который вы можете использовать для настройки Callback API VK.

3. Настройте Callback API в вашем сообществе VK:
    - Перейдите в Управление сообществом -> Callback API.
    - Укажите URL, предоставленный Ngrok, и добавьте `/webhook` в конец (например, `https://your-ngrok-url.ngrok.io/webhook`).
    - Установите версию API на 5.236.
    - Укажите секретный ключ и код подтверждения.
