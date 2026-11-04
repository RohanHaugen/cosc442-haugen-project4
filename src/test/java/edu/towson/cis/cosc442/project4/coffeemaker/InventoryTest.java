package edu.towson.cis.cosc442.project4.coffeemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class InventoryTest {
    Inventory inv;
    @BeforeEach 
    public void setUp() {
        inv = new Inventory();
    }
    @AfterEach 
    public void tearDown() {
        inv = null;
    }
    @Test
    void testEnoughIngredientsWorks() {
        Recipe r = new Recipe();
        r.setAmtCoffee(1);
        r.setName("Recipe1");
        r.setAmtMilk(2);
        r.setAmtSugar(3);
        r.setAmtChocolate(4);
        r.setPrice(3);
        assertTrue(inv.enoughIngredients(r));

    }
    @Test 
    void testEnoughIngredientsFailsNotEnough(){
        Recipe r = new Recipe();
        r.setAmtCoffee(16);
        r.setName("Recipe1");
        r.setAmtMilk(16);
        r.setAmtSugar(16);
        r.setAmtChocolate(4);
        r.setPrice(3);
        assertFalse(inv.enoughIngredients(r));

    }
    @Test 
    void testEnoughIngredientsWorksExact() {
        Recipe r = new Recipe();
        r.setAmtCoffee(15);
        r.setName("Recipe1");
        r.setAmtMilk(15);
        r.setAmtSugar(15);
        r.setAmtChocolate(15);
        r.setPrice(3);
        assertTrue(inv.enoughIngredients(r));
    }

    @ParameterizedTest 
    @CsvSource ({
        "-1,0",
        "0,0",
        "1,1"
    })
    void testSetIngredients(int input, int output){
        inv.setChocolate(input);
        inv.setCoffee(input);
        inv.setMilk(input);
        inv.setSugar(input);
        assertEquals(output, inv.getChocolate());
        assertEquals(output, inv.getCoffee());
        assertEquals(output, inv.getMilk());
        assertEquals(output, inv.getSugar());
    }
    @Test 
    void testToString(){
        String expectedOutput = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n";
        assertEquals(expectedOutput,inv.toString());
    }
    
}
