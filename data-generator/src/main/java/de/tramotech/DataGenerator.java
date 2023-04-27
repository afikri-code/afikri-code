package de.tramotech;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import model.Customer;
import org.apache.commons.lang3.LocaleUtils;

import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A utility class for generating fake data using the JavaFaker library.
 * Author: Ahmed Fikri
 */
public class DataGenerator {

    private final Faker faker;
    private final FakeValuesService fakeValuesService;

    /**
     * Creates a new DataGenerator instance with the specified language.
     * @param language a two-letter ISO 639-1 language code (e.g. "en" for English)
     */
    public DataGenerator(String language) {
        // Convert the input language code to a Locale object using LocaleUtils
        Locale locale = LocaleUtils.toLocale(language);

        // Create a new Faker instance with the specified locale
        faker = new Faker(locale);

        // Create a new FakeValuesService instance with the specified locale and a RandomService
        fakeValuesService = new FakeValuesService(locale, new RandomService());
    }

    /**
     * Returns a new Faker instance with the configured locale.
     * @return a Faker instance
     */
    public Faker getFaker() {
        return faker;
    }

    /**
     * Returns a new FakeValuesService instance with the configured locale and a RandomService.
     * @return a FakeValuesService instance
     */
    public FakeValuesService getFakeValuesService() {
        return fakeValuesService;
    }

    /**
     * Creates a new Customer object with randomized data using the JavaFaker library.
     * @return a new Customer object with randomized data
     */
    Customer createOneCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId(faker.idNumber().valid());
        customer.setFirstName(faker.name().firstName());
        customer.setLastName(faker.name().lastName());
        customer.setBirthday(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        customer.setEmail(faker.internet().emailAddress());
        customer.setPhoneNumber(faker.phoneNumber().cellPhone());
        customer.setAddress(faker.address().streetAddress());
        customer.setCity(faker.address().city());
        customer.setPostalCode(faker.address().zipCode());
        customer.setAddressCountry("Germany");
        customer.setPlaceOfBirthCountry(fakeValuesService.resolve("address.country", null, faker));
        return customer;
    }

    /**
     * Creates a new Customer object with randomized data using the JavaFaker library.
     * @return a new Customer object with randomized data
     */
    Customer createOneCustomer(String custPrefix, long num, int custNrWidth) {
        Customer customer = new Customer();
        customer.setCustomerId(String.format("%s%0" + custNrWidth + "d", custPrefix, num));
        customer.setFirstName(faker.name().firstName());
        customer.setLastName(faker.name().lastName());
        customer.setBirthday(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        customer.setEmail(faker.internet().emailAddress());
        customer.setPhoneNumber(faker.phoneNumber().cellPhone());
        customer.setAddress(faker.address().streetAddress());
        customer.setCity(faker.address().city());
        customer.setPostalCode(faker.address().zipCode());
        customer.setAddressCountry("Germany");
        customer.setPlaceOfBirthCountry(fakeValuesService.resolve("address.country", null, faker));
        return customer;
    }

    /**
     * Generates a list of Customers with randomized data using the JavaFaker library.
     * @param size the number of Customers to generate
     * @return a list of Customers with randomized data
     */
    List<Customer> generateCustomers(int size) {
        return IntStream
                .rangeClosed(1, size)
                .mapToObj(i -> createOneCustomer("CU_", i, 7))
                .collect(Collectors.toList());
    }
}
