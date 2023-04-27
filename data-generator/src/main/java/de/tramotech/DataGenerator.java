package de.tramotech;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import model.Customer;
import model.Transaction;
import org.apache.commons.lang3.LocaleUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Random;
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
        customer.setCustomerNr(faker.idNumber().valid());
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
    private String generateCustNr(String custPrefix, long num, int custNrWidth) {
        return String.format("%s%0" + custNrWidth + "d", custPrefix, num);
    }

    private String generateCustNr(long i) {
        return generateCustNr("CU_", i, 7);
    }
    /**
     * Creates a new Customer object with randomized data using the JavaFaker library.
     * @return a new Customer object with randomized data
     */
    Customer createOneCustomer(long num) {
        Customer customer = new Customer();
        customer.setCustomerNr(generateCustNr(num));
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
     * Creates a new Transaction with the given parameters.
     *
     * @param custNr    the customer number
     * @param trxPrefix the transaction prefix
     * @param num       the transaction number
     * @param maxAmount the maximum transaction amount
     * @return a new Transaction object
     */
    Transaction createOneTransaction(String custNr, String trxPrefix, int num, int maxAmount) {
        Transaction trx = new Transaction();
        trx.setTransactionNr(trxPrefix+custNr+"_"+num);
        trx.setCustomerNr(custNr);
        trx.setDate(LocalDate.now());
        trx.setAmount(BigDecimal.valueOf((Math.random() * maxAmount)));
        trx.setCredit(Math.random() > 0.5);
        return trx;
    }

    /**
     * Creates a list of transactions for a customer with the given customer number.
     * The number of transactions and maximum amount are given as parameters.
     *
     * @param custNr     the customer number
     * @param count      the max number of transactions to create (it will be 0 to count transaction(s) created)
     * @param maxAmount  the maximum amount of each transaction
     * @return a list of transactions
     */
    List<Transaction> createTransaction(String custNr, int count, int maxAmount) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> createOneTransaction(custNr, "TRX_", i, maxAmount))
                .collect(Collectors.toList());
    }

    /**
     * Generates a list of Customers with randomized data using the JavaFaker library.
     * @param size the number of Customers to generate
     * @return a list of Customers with randomized data
     */
    List<Customer> generateCustomers(int size) {
        return IntStream
                .rangeClosed(1, size)
                .mapToObj(this::createOneCustomer)
                .collect(Collectors.toList());
    }

    /**
     * Generates a list of random transactions based on the given parameters.
     *
     * @param sizeOfCustomer    The number of customers to generate transactions for.
     * @param maxTransactionsPerCustomer The maximum number of transactions per customer to generate.
     * @param maxAmount         The maximum amount of each transaction.
     * @return A list of randomly generated transactions.
     */
    public List<Transaction> generateTransactions(int sizeOfCustomer, int maxTransactionsPerCustomer, int maxAmount) {
        Random random = new Random();
        return IntStream.rangeClosed(1, sizeOfCustomer)
                .mapToObj(i-> this.createTransaction(generateCustNr(i), random.nextInt(maxTransactionsPerCustomer) + 1, maxAmount))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
