package edu.towson.cis.cosc442.project4.coffeemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RecipeTest {
    Recipe r;
    @BeforeEach 
    public void setUp(){
        r = new Recipe();
    }
    @AfterEach 
    public void tearDown(){
        r = null;
    }
    @Test
    void testEquals() {
        Recipe r1= new Recipe();
        r1.setName("recipea");
        r.setName("recipea");
        r.setAmtChocolate(300);
        assertTrue(r.equals(r1));
    }
    @Test 
    void testEqualsNullOther(){
        Recipe r1= new Recipe();
        r1.setName("recipea");
        r.setAmtChocolate(300);
        assertFalse(r.equals(r1));

    }


    @ParameterizedTest 
    @CsvSource ({
        "-1,0",
        "0,0",
        "1,1"
    })
    void testSetIngredients(int input, int output){
        r.setAmtChocolate(input);
        r.setAmtCoffee(input);
        r.setAmtMilk(input);
        r.setAmtSugar(input);
        r.setPrice(input);
        assertEquals(output, r.getAmtChocolate());
        assertEquals(output, r.getAmtCoffee());
        assertEquals(output, r.getAmtMilk());
        assertEquals(output, r.getAmtSugar());
        assertEquals(output, r.getPrice());
    }
    
    void testToString(){
        r.setName("Seven");
        assertEquals("Seven",r.toString());
        
    }



    
}
