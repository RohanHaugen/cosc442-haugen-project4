package edu.towson.cis.cosc442.project4.coffeemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
public class CoffeeMakerTest {
    CoffeeMaker machine;
    Recipe r1;
    Recipe r2;
    Recipe r3;
    Recipe r4;

    @BeforeEach 
    public void setUp(){
        machine = new CoffeeMaker();
        r1 = new Recipe();
        r1.setAmtCoffee(1);
        r1.setName("Recipe1");
        r1.setAmtMilk(2);
        r1.setAmtSugar(3);
        r1.setAmtChocolate(4);
        r1.setPrice(3);
        r2 = new Recipe();
        r2.setAmtCoffee(5);
        r2.setName("Recipe2");
        r2.setAmtMilk(6);
        r2.setAmtSugar(7);
        r2.setAmtChocolate(8);
        r2.setPrice(4);
        r3 = new Recipe();
        r3.setAmtCoffee(16);
        r3.setName("Recipe3");
        r3.setAmtMilk(16);
        r3.setAmtSugar(16);
        r3.setAmtChocolate(16);
        r3.setPrice(16);
        r4 = new Recipe();
        r4.setAmtCoffee(15);
        r4.setName("Recipe4");
        r4.setAmtMilk(15);
        r4.setAmtSugar(15);
        r4.setAmtChocolate(15);
        r4.setPrice(3);
    }
    @AfterEach 
    public void tearDown(){
        machine = null;
    }
    //@Test
    void testAddInventoryWorksReturnsTrue() {
        boolean actualResult = machine.addInventory(1,1,1,1);
        assertTrue(actualResult);
    }
    //@Test 
    void testAddInventoryWorksAddsInventory() {
        machine.addInventory(1,2,3,4);
        Inventory inv = machine.checkInventory();
        int cof = inv.getCoffee();
        int mil = inv.getMilk();
        int sug = inv.getSugar();
        int cho = inv.getChocolate();
        assertEquals(16, cof);
        assertEquals(17, mil);
        assertEquals(18, sug);
        assertEquals(19, cho);
    }
    //@ParameterizedTest 
    @CsvSource ({
        "0,1,1,1",
        "1,0,1,1",
        "1,1,0,1",
        "1,1,1,0"
    })
    void testAddInventoryWorks0(int cof, int mil, int sug, int cho) {
        boolean actualResult = machine.addInventory(cof,mil,sug,cho);
        assertTrue(actualResult);
        Inventory inv = machine.checkInventory();
        int expCof = inv.getCoffee();
        int expMil = inv.getMilk();
        int expSug = inv.getSugar();
        int expCho = inv.getChocolate();
        assertEquals(15+cof, expCof);
        assertEquals(15+mil,expMil);
        assertEquals(15+sug, expSug);
        assertEquals(15+cho,expCho);
    }
    //@ParameterizedTest 
    @CsvSource ({
        "-1,1,1,1",
        "1,-1,1,1",
        "1,1,-1,1",
        "1,1,1,-1"
    })
    void testAddInventoryWorksFailsNegative(int cof, int mil, int sug, int cho) {
        boolean actualResult = machine.addInventory(cof,mil,sug,cho);
        assertFalse(actualResult);
        Inventory inv = machine.checkInventory();
        int expCof = inv.getCoffee();
        int expMil = inv.getMilk();
        int expSug = inv.getSugar();
        int expCho = inv.getChocolate();
        assertEquals(15, expCof);
        assertEquals(15,expMil);
        assertEquals(15, expSug);
        assertEquals(15,expCho);
    }

    @Test
    void testAddRecipeWorks() {
        boolean actualResult = machine.addRecipe(r1);
        assertTrue(actualResult);
    }
    @Test 
    void testAddRecipeWorksCanFind(){
        machine.addRecipe(r1);
        Recipe actualResult = machine.getRecipeForName("Recipe1");
        assertEquals(r1, actualResult);
    }
    @Test
    void testAdd3RecipesWorks(){
        machine.addRecipe(r1);
        machine.addRecipe(r2);
        boolean actualResult= machine.addRecipe(r3);
        assertTrue(actualResult);
    }
    @Test 
    void testAdd4RecipesFails(){
        machine.addRecipe(r1);
        machine.addRecipe(r2);
        machine.addRecipe(r3);
        boolean actualResult= machine.addRecipe(r4);
        assertFalse(actualResult);
    }
    @Test
    void testAddDuplicateRecipeFails(){
        machine.addRecipe(r1);
        boolean actualResult=machine.addRecipe(r1);
        assertFalse(actualResult);
    }
    @Test 
    void testAddRecipeFailsNull(){
        boolean actualResult = machine.addRecipe(null);
        assertFalse(actualResult);
    }
    //@Test
    void testCheckInventoryWorks() {
        Inventory inv = machine.checkInventory();
        int expCof = inv.getCoffee();
        int expMil = inv.getMilk();
        int expSug = inv.getSugar();
        int expCho = inv.getChocolate();
        assertEquals(15, expCof);
        assertEquals(15,expMil);
        assertEquals(15, expSug);
        assertEquals(15,expCho);
    }

    @Test
    void testDeleteRecipeWorks() {
        machine.addRecipe(r1);
        boolean actualResult = machine.deleteRecipe(r1);
        assertTrue(actualResult);
    }
    @Test 
    void testDeleteRecipeWorksDeletesRecipe(){
        machine.addRecipe(r1);
        machine.deleteRecipe(r1);
        Recipe actualResult = machine.getRecipeForName("Recipe1");
        assertNotEquals(actualResult, r1);
    }
    @Test 
    void testDeleteRecipeFails(){
        machine.addRecipe(r1);
        boolean actualResult = machine.deleteRecipe(r2);
        assertFalse(actualResult);
    }
    @Test 
    void testDeleteRecipeFailsNull(){
        machine.addRecipe(r1);
        boolean actualResult = machine.deleteRecipe(null);
        assertFalse(actualResult);
    }
    @Test
    void testEditRecipeWorks() {
        machine.addRecipe(r1);
        r2.setName("Recipe1");
        boolean actualResult = machine.editRecipe(r1,r2);
        assertTrue(actualResult);
    }
    @Test
    void testEditRecipeWorksRemovesRecipe(){
        machine.addRecipe(r1);
        r2.setName("Recipe1");
        machine.editRecipe(r1,r2);
        Recipe actualResult = machine.getRecipeForName("Recipe1");
        assertNotEquals(r1,actualResult);
        assertEquals(5,actualResult.getAmtCoffee());
        assertEquals(6,actualResult.getAmtMilk());
        assertEquals(7, actualResult.getAmtSugar());
        assertEquals(8, actualResult.getAmtChocolate());
        assertEquals(4,actualResult.getPrice());
    }
    @Test 
    void testEditRecipeWorksAddsRecipe(){
        machine.addRecipe(r1);
        r2.setName("Recipe1");
        machine.editRecipe(r1,r2);
        Recipe actualResult = machine.getRecipeForName("Recipe1");
        assertEquals(r2,actualResult);
        assertEquals(5,actualResult.getAmtCoffee());
        assertEquals(6,actualResult.getAmtMilk());
        assertEquals(7, actualResult.getAmtSugar());
        assertEquals(8, actualResult.getAmtChocolate());
        assertEquals(4,actualResult.getPrice());
    }
    @Test 
    void testEditRecipeFailsDifferentName(){
        machine.addRecipe(r1);
        boolean actualResult = machine.editRecipe(r1, r2);
        assertFalse(actualResult);
    }
    @Test 
    void testEditRecipeFailsNull(){
        machine.addRecipe(r1);
        boolean actualResult = machine.editRecipe(r1, null);
        assertFalse(actualResult);
    }

    //@Test
    void testGetRecipeForNameFailsReturnsEmptyRecipe() {
        Recipe actualResult = machine.getRecipeForName("Seven");
        assertNull(actualResult.getName());
        assertEquals(0, actualResult.getPrice());
        assertEquals(0,actualResult.getAmtCoffee());
        assertEquals(0,actualResult.getAmtMilk());
        assertEquals(0,actualResult.getAmtSugar());
        assertEquals(0,actualResult.getAmtChocolate());
    }

    //@Test
    void testGetRecipes() {
        Recipe[] actualResult = machine.getRecipes();
        for(int i = 0;i<actualResult.length;i++){
            assertNull(actualResult[i].getName());
            assertEquals(0, actualResult[i].getPrice());
            assertEquals(0,actualResult[i].getAmtCoffee());
            assertEquals(0,actualResult[i].getAmtMilk());
            assertEquals(0,actualResult[i].getAmtSugar());
            assertEquals(0,actualResult[i].getAmtChocolate());
        }
    }

    //@Test
    void testMakeCoffeeWorksReturnsAmountPaid() {
        int actualResult = machine.makeCoffee(r1, 10);
        assertEquals(7,actualResult);
    }

    @Test 
    void testMakeCoffeeWorksSubtractsIngredients() {
        machine.makeCoffee(r1,10);
        Inventory inv = machine.checkInventory();
        int expCof = inv.getCoffee();
        int expMil = inv.getMilk();
        int expSug = inv.getSugar();
        int expCho = inv.getChocolate();
        assertEquals(14, expCof);
        assertEquals(13, expMil);
        assertEquals(12, expSug);
        assertEquals(11,expCho);
    }
    //@Test 
    void testMakeCoffeeWorksExactPrice() {
        int actualResult = machine.makeCoffee(r1,3);
        assertEquals(0,actualResult);
    }
    //@Test 
    void testMakeCoffeeWorksExactIngredients() {
        int actualResult = machine.makeCoffee(r4,10);
        assertEquals(7,actualResult);
    }
    //@Test 
    void testMakeCoffeeFailsWhenNoIngredients(){
        int actualResult = machine.makeCoffee(r3,10);
        assertEquals(10, actualResult);
        Inventory inv = machine.checkInventory();
        int expCof = inv.getCoffee();
        int expMil = inv.getMilk();
        int expSug = inv.getSugar();
        int expCho = inv.getChocolate();
        assertEquals(15, expCof);
        assertEquals(15, expMil);
        assertEquals(15, expSug);
        assertEquals(15,expCho);
    }
    //@Test 
    void testMakeCoffeeFailsWhenNoMoney() {
        int actualResult = machine.makeCoffee(r2,3);
        assertEquals(3, actualResult);
        Inventory inv = machine.checkInventory();
        int expCof = inv.getCoffee();
        int expMil = inv.getMilk();
        int expSug = inv.getSugar();
        int expCho = inv.getChocolate();
        assertEquals(15, expCof);
        assertEquals(15, expMil);
        assertEquals(15, expSug);
        assertEquals(15,expCho);
    }

    
    
}
