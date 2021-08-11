package br.ufes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;


public class ValueSourceTeste {
    
    public ValueSourceTeste() {
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
        assertTrue(StringUtils.isPalindrome(string));
    }

}
