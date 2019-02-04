/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package buisness_company;

/**
 *
 * @author Biruk
 */
public class Assistance extends Employee {

    String name;
    float salary;
    
    public Assistance(String name, float salary) {
        super(name, salary);
        this.name = name;
        this.salary = salary;
    }
    
    
     @Override
    public float getSalary() { 
        return this.salary;
    }
    
     
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public boolean isAssistance() {
        return true;
    }
    
    @Override
    public boolean isManager() {
        return false;
    }
    
}
