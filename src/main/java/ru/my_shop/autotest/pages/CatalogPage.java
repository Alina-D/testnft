package ru.my_shop.autotest.pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;
import static java.lang.String.format;
import static org.junit.Assert.*;

public class CatalogPage extends CommonPage {

    public CatalogPage() {
        super();
    }



    private String TYPE_SORTING_LINK_XPATH;


    // Список с именами товаров
    private ElementsCollection nameProductsList = $$("a[href^='/shop/product'] > b");
    // Список с ценой товаров
    private static final ElementsCollection priceProductsList =
            $$x("//table[@data-o='listgeneral']//sup//../b[1]");
    // Список с кратким описанием товаров
    private ElementsCollection shortDescriptionProductsList =
            $$x("//table[@data-o='listgeneral']//td[2]");
    // Список с информацией о наличии и доставке товаров
    private final ElementsCollection infoAboutDeliveryProductsList =
            $$x("//table[@data-o='listgeneral']//sup/..");


    /**
     * Проверить результаты поиска
     *
     * @param productName - надименование товара
     */
    public CatalogPage checkSearchResults(String productName) {
        // todo привести к нижнему регистру
        nameProductsList.forEach(product -> {
            String nameProduct = product.getText().toLowerCase();
            assertTrue(format("Не корректное отображение результатьв поиска. Товар '%s'" +
                    "не содержит '%s'", nameProduct, productName), nameProduct.contains(productName));
        });
        logger.info("Результы поиска успешно проверены.");
        return this;
    }

    /**
     * Проверить результаты поиска
     * @param amountProducts - количество товаров
     */
    public CatalogPage printSearchResults(int amountProducts) {
        // todo сделать модели
        for (int index = 0; index < nameProductsList.size(); index++) {

            String productName = nameProductsList.get(index).text();
            logger.info("Имя " + productName);

            String priceProducts = priceProductsList.get(index).text();
            logger.info("Цена " + priceProducts);

            String shotDescriptionProducts = shortDescriptionProductsList.get(index).text();
            logger.info("Опис " + shotDescriptionProducts);

            String infoAboutProducts = infoAboutDeliveryProductsList.get(index).text();
            logger.info("Достав " + infoAboutProducts.substring(
                    infoAboutProducts.indexOf('\n') + 1, infoAboutProducts.indexOf(')') + 1));

        }
        logger.info("Результы поиска успешно проверены.");
        return this;
    }

    /**
     * Сортировать товары
     *
     * @param typeSorting - тип сортирвки
    */
    public void sortProduct(String typeSorting) {
        clickElement(format(TYPE_SORTING_LINK_XPATH, typeSorting));
        logger.info("Выполнена сортировка {}", typeSorting);
    }

    /**
     * Поиск товара по имени товара
     *
     * @param typeSorting - наименование товара
     */
    public CommonPage checkSorting(String typeSorting) {
        // todo сделать проверку сортиравки, перед эт м подумать, как лучше хранить информацию о товарах
        return this;
    }

    /**
     * Открыть товар по номеру в списке
     *
     * @param numberProduct - номер товара в списке
     */
    public CatalogPage openProductByNumberInList(int numberProduct) {
        clickElement(nameProductsList.get(numberProduct));
        logger.info("Открыть товар по номеру {} в списке", numberProduct);
        return this;
    }
}


























