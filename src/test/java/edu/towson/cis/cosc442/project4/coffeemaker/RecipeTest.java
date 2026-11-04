package edu.towson.cis.cosc442.project4.coffeemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testSetAmtChocolateSets0Neg() {
        r.setAmtChocolate(-3);
        assertEquals(0,r.getAmtChocolate());
    }

    @Test
    void testSetAmtCoffeeSets0Neg() {
        r.setAmtCoffee(-3);
        assertEquals(0,r.getAmtCoffee());
        
    }

    @Test
    void testSetAmtMilkSets0Neg() {
        r.setAmtMilk(-3);
        assertEquals(0,r.getAmtMilk());
        
    }

    @Test
    void testSetAmtSugarSets0Neg() {
        r.setAmtSugar(-3);
        assertEquals(0,r.getAmtSugar());
        
    }

  

    @Test
    void testSetPriceSets0Neg() {
        r.setPrice(-3);
        assertEquals(0, r.getPrice());
    }


    
}
