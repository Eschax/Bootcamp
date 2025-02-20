package example;

import org.testng.annotations.DataProvider; 

public class StaticProvider {

    @DataProvider(name ="dataproviderPositive")
    public Object[][] dataTestPositive() {
        return new Object[][]{
            {"Rudy",10}, 
            {"Sari",20}, 
            {"Budi",25}
        };
    }

    @DataProvider(name ="dataprovider")
    public Object[][] dataTestNegative() {
        return new Object[][]{
            {"Rudy",10}, 
            {"Sari",20},  
            {"Budi",25}
        };

    }    
}
