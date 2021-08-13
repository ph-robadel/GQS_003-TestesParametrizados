package br.ufes;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;


public class TestesParametrizados {
    
    public TestesParametrizados() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @ParameterizedTest
    @ValueSource(strings = { "level", "madam", "saippuakivikauppias" })
    void palindromeReadsSameBackward(String string) {
        assertTrue(isPalindrome(string));
    }
    
    @ParameterizedTest
    @ValueSource(ints = { 3, 6, 9})
    void divisibleByThree(int number) {
        assertEquals(0, number % 3);
    }
    
    @ParameterizedTest
    @NullSource
    @EmptySource
    @NullAndEmptySource
    @ValueSource(strings = { " " })
    void nullEmptyAndBlankStrings(String text) {
        assertTrue(text == null || text.trim().isEmpty());
    }
    
    @ParameterizedTest
    @MethodSource("numberToMonth")
    void monthNames(int month, String name) {
        assertEquals(name, getMonthForInt(month));
    }

    private static Stream<Arguments> numberToMonth() {
        return Stream.of(
                Arguments.arguments(0, "janeiro"),
                Arguments.arguments(1, "fevereiro"),
                Arguments.arguments(11, "dezembro")
        );
    }
    
    @CsvSource({
        "Write a blog post, IN_PROGRESS, 2020-12-20",
        "Wash the car, OPENED, 2020-12-15"
    })
    void readTasks(String title, String status, LocalDate date) {
        System.out.printf("%s, %s, %s", title, status, date);
    }
    
//    @ParameterizedTest
//    @CsvFileSource(resources = "/tasks.csv")
//    void readTasksFile(String title, String status, LocalDate date) {
//        System.out.printf("%s, %s, %s", title, status, date);
//    }
    
    @ParameterizedTest
    @CsvSource(", IN_PROGRESS, 2020-12-20")
    void nullArgumentCsv(String title, String status, LocalDate date) {
        assertNull(title);
    }
    
    @ParameterizedTest
    @CsvSource("'', IN_PROGRESS, 2020-12-20")
    void emptyArgument(String title, String status, LocalDate date) {
        assertTrue(title.isEmpty());
    }
    
    @ParameterizedTest
    @CsvSource(value = "NULL, IN_PROGRESS, 2020-12-20", nullValues = "NULL")
    void customNullArgument(String title, String status, LocalDate date) {
        assertNull(title);
    }
    
    @ParameterizedTest
    @ValueSource(strings = { "2018-01-01", "2018-01-31" })
    void convertStringToLocalDate(LocalDate localDate) {
        assertEquals(Month.JANUARY, localDate.getMonth());
    }
    
    @ParameterizedTest
    @CsvSource("John Doe")
    void fallbackStringConversion(Person person) {
        assertEquals("John Doe", person.getNome());
    }
    
    @ParameterizedTest
    @CsvSource({
            "15, F",
            "16, 10",
            "233, E9"
    })
    void convertWithCustomHexConverter(int decimal, @ConvertWith(HexConverter.class) int hex) {
        assertEquals(decimal, hex);
    }
    
    @ParameterizedTest
    @CsvSource({
            "Write a blog post, IN_PROGRESS, 2020-12-20",
            "Wash the car, OPENED, 2020-12-15"
    })
    void aggregateArgumentsWithAggregator(@AggregateWith(TaskAggregator.class) Task task) {
        System.out.println(task);
    }
    
    private boolean isPalindrome(String palavra){
        String invertida = new StringBuffer(palavra).reverse().toString(); 
        return palavra.equals(invertida);
    }
    
    private String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }

}
