package edu.towson.cis.cosc442.project4.coffeemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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


    @Test
    void testSetChocolateSets0Neg() {
        inv.setChocolate(-4);
        assertEquals(0, inv.getChocolate());
    }

    @Test
    void testSetCoffeeSets0Neg() {
        inv.setCoffee(-5);
        assertEquals(0, inv.getCoffee());
    }

    @Test
    void testSetMilkSets0Neg() {
        inv.setMilk(-7);
        assertEquals(0, inv.getMilk());
    }

    @Test
    void testSetSugarSets0Neg() {
        inv.setSugar(-3);
        assertEquals(0, inv.getSugar());
    }

    
}
