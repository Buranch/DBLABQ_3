
package buisness_company;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;


public class Manager extends Employee{
    
    float salary;
    String name;
   Vector childAssistance;
    
    public Manager(String name, float salary) {
        super(name, salary);
        this.name = name;
        this.salary = salary;
        childAssistance = new Vector();
    }
    
    
    @Override
    public void add(Employee director){
        this.childAssistance.add(director);
    }
    
    @Override
    public void remove(Employee director){
        this.childAssistance.remove(director);
    }
    
    public List getArray(){
        return childAssistance;
    }

    @Override
    public String getName() {
       System.out.println(name);
       return this.name;
    }
    
    @Override
    public boolean isAssistance() {
        return false;
    }

    @Override
    public float getSalary() {
       // System.out.println(salary);
       float sum = this.salary;
       System.out.println("SIZE "+ childAssistance.size());
       for(int i=0; i < childAssistance.size();i++){
           System.out.println("Ass. "+ ((Assistance) childAssistance.elementAt(i)).getName());
           sum += ((Assistance) childAssistance.elementAt(i)).getSalary();
       }
       return sum;
    }
    
    public void setSalary(Integer salary){
        this.salary = salary;
    }

    @Override
    public void show_childs() {
//       childAssistance.forEach(Director :: getName);
    }
    
    @Override
    public boolean isManager() {
        return true;
    }
    
    
    @Override
    public Enumeration elements() {
        return this.childAssistance.elements();
    }
    
    @Override
    public Employee getChild(String name) {
        
        System.out.println("size: " + this.childAssistance.size());
        
        for(int i =0; i< this.childAssistance.size(); i++){
            //On this we're looping over presidents
            
            if(name.equals(((Employee) this.childAssistance.get(i)).getName())){
                    System.out.println("Congra Found A President");
                    return ((Employee) this.childAssistance.get(i));
            }
            
            Enumeration enu = ((Employee) this.childAssistance.get(i)).elements();
            while(enu.hasMoreElements())
                {
                    Employee newEmp = (Employee) enu.nextElement();
                    System.out.println("Iterating over "+newEmp.getName());
                    if(name.equals(newEmp.getName())){
                        System.out.println("COngrat found it");
                        return newEmp;
                    }
                }
        }
        return null;
    }
    
}
