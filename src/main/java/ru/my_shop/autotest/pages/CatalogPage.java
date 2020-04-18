package ru.my_shop.autotest.pages;

import com.codeborne.selenide.ElementsCollection;
import ru.my_shop.autotest.models.ProductModel;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;
import static java.lang.String.format;
import static org.junit.Assert.*;

public class CatalogPage extends CommonPage {


    // todo Поля класса.
    private ArrayList<ProductModel> listProduct = new ArrayList<>();

    // todo конструктор
    public CatalogPage() {
        super();
    }

    // todo локаторы
    // Ссылка с типом сортировки товаров
    private static final String TYPE_SORTING_LINK_XPATH = "";
    // Список с именами товаров
    private final ElementsCollection nameProductsList = $$("a[href^='/shop/product'] > b");
    // Список с ценой товаров
    private final ElementsCollection priceProductsList =
            $$x("//table[@data-o='listgeneral']//sup//../b[1]");
    // Список с кратким описанием товаров
    private final ElementsCollection shortDescriptionProductsList =
            $$x("//table[@data-o='listgeneral']//td[2]");
    // Список с информацией о наличии и доставке товаров
    private final ElementsCollection infoOnAvailabilityAndDeliveryProductsList =
            $$x("//table[@data-o='listgeneral']//sup/..");


    // todo методы

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
     * Сохраняет информацию о найденных товарах
     */
    public CatalogPage saveInfoAboutProductsFound() {
        for (int index = 0; index < nameProductsList.size(); index++) {
            
            // Получить имя и цену товара
            String productName = nameProductsList.get(index).text();

            // Создать объект с именем товара
            ProductModel product = new ProductModel(productName);

            // Установить цену товара
            String priceProducts = priceProductsList.get(index).text();
            product.setPriceProduct(priceProducts);

            // Установить краткое описание товара
            String shotDescriptionProducts = shortDescriptionProductsList.get(index).text();
            product.setShotDescriptionProduct(shotDescriptionProducts);
            
            // Установить информацию о наличии товара
            String infoOnAvailabilityAndDeliveryProducts = infoOnAvailabilityAndDeliveryProductsList.get(index).text();
            String productAvailabilityInfo = infoOnAvailabilityAndDeliveryProducts.substring(
                    infoOnAvailabilityAndDeliveryProducts.indexOf('\n') + 1, 
                    infoOnAvailabilityAndDeliveryProducts.indexOf(';'));
            product.setProductAvailability(productAvailabilityInfo);

            // Установить дату доставки товара
            String deliveryDate = infoOnAvailabilityAndDeliveryProducts.substring(
                    infoOnAvailabilityAndDeliveryProducts.indexOf(';') + 2, 
                    infoOnAvailabilityAndDeliveryProducts.indexOf(')') + 1);
            product.setDeliveryDate(deliveryDate);

            listProduct.add(product);
        }
        logger.info("Информация о найденных товарах успешно сохранена");
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


























