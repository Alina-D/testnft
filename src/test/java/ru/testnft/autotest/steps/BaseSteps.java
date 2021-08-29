package ru.testnft.autotest.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import ru.testnft.autotest.pages.CommonPage;
import ru.testnft.autotest.pages.ProductPage;

import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

/**
 * Класс описывающий общие шаги Интернет-магазина my-shop.ru
 */
public class BaseSteps extends AbstractSteps {

    private final CommonPage commonPage = new CommonPage();
    private final ProductPage productPage = new ProductPage();

    @When("ожидаем авторизацию {int} минут")
    public void waitAuthorization(int min) {
        printStepName(format("Ожидаем авторизацию %s минут", min));
        commonPage.waitAuthorization("Панель инструментов", min);
        logger.info("Авторизация успешно выполнена");
    }

    @And("открываем страницу покупки боксов по Url {string}")
    public void openPage(String url) {
        printStepName(format("Открываем страницу покупки боксов по Url %s", url));
        open(url);
        logger.info(format("Открыта страница с Url [%s]", url));
    }

    @And("обновляем страницу за 10 мин до начала продаж обновляем страницу")
    public void refreshPage() {
        printStepName("Обновляем страницу за %s мин до начала продаж обновляем страницу");
        productPage.refreshBoxPage();
    }

    @And("ожидаем кнопку {string}")
    public void waitBtn(String btnName) {
        printStepName(format("Ожидаем кнопку %s", btnName));

    }

    @And("нажимаем кнопку {string}")
    public void clickBtn(String btnName) {
        printStepName(format("Нажимаем кнопку %s", btnName));

    }

    @And("устанавливаем кол-во боксов - {string}")
    public void setCountBox(String count) {
        printStepName(format("Устанавливаем кол-во боксов %s", count));

    }

    @And("проверяем покупку бокса в кол-ве {string} шт")
    public void checkCountOfPurchasedBoxes(String count) {
        printStepName(format("Проверяем покупку бокса в кол-ве %s", count));

    }
}