# Дипломный проект по курсу «Тестировщик ПО»

## Описание приложения
Приложение — это веб-сервис, который предлагает купить тур по определённой цене двумя способами:

1. Обычная оплата по дебетовой карте.
2. Уникальная технология: выдача кредита по данным банковской карты.

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:
- сервису платежей, далее Payment Gate;
- кредитному сервису, далее Credit Gate.

Приложение в собственной СУБД должно сохранять информацию о том, успешно ли был совершён платёж и каким способом. Данные карт при этом сохранять не допускается.

## Документация
- [План автоматизации тестирования](https://github.com/christinakru/DiplomaQA/blob/master/docs/Plan.md)
- [Отчет по итогам тестирования](https://github.com/christinakru/DiplomaQA/blob/master/docs/Report.md)
- [Отчет по итогам автоматизации](https://github.com/christinakru/DiplomaQA/blob/master/docs/Summary.md)

## Инструкция
*Предварительные требования:*
- IntelliJ IDEA
- Docker desktop / docker toolbox

### Запуск проекта:
1. Клонировать репозиторий, используя команду `git clone https://github.com/christinakru/DiplomaQA`
2. Запускаем docker-контейнер с СУБД MySQL и PostgreSQL, а также Node.js: `docker-compose up`
3. Запускаем SUT:
    - MySQL:
      `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar`
    - PostgreSQL:
      `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`
      
После исполнения команды в терминале появится сообщение: `2023-04-02 00:48:31.253  INFO 63900 --- [main] ru.netology.shop.ShopApplication: Started ShopApplication in 4.816 seconds (JVM running for 5.292)`

4. Запускаем авто-тесты
    - MySQL: `./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"`
    - PostgreSQL: `./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`
5. Генерируем отчет Allure
`./gradlew allureServe`
