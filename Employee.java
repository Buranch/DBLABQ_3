package buisness_company;

import java.util.Enumeration;
import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;


public class Employee implements Director{
    
    String name;
    float salary;
    Vector subordinates;
    public Employee(String name , float salary){
        this.name = name;
        this.salary = salary;
        subordinates = new Vector();
    }
    @Override
    public String getName() {
//        System.out.println(name);
      return name;
    }

    @Override
    public void show_childs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void add(Employee e) {
        subordinates.addElement(e);
    }
    
    public void remove(Employee e) {
        subordinates.removeElement(e);
     }
    
    public boolean isAssistance() {
        return false;
    }
    public Enumeration elements() {
        return subordinates.elements();
    }
 
    public Employee getChild(String name) {
        
        System.out.println("size: " + this.subordinates.size());
        
        for(int i =0; i< this.subordinates.size(); i++){
            //On this we're looping over presidents
            
            if(name.equals(((Employee) this.subordinates.get(i)).getName())){
                    System.out.println("Congra Found A President");
                    return ((Employee) this.subordinates.get(i));
            }
            Enumeration enu = ((Employee) this.subordinates.get(i)).elements();
            while(enu.hasMoreElements())
                {
                    //On this we're looping over Managers
                    Employee newEmp = (Employee) enu.nextElement();
                    if(name.equals(newEmp.getName())){
                        return newEmp;
                    }
                    Enumeration enu2 = newEmp.elements();
                    while(enu2.hasMoreElements()){
                        //On this we're looping over Assistances
                        Employee newEmp2 = (Employee) enu2.nextElement();
                        if(name.equals(newEmp2.getName())){
                            return newEmp2;
                        }
                    }
                    
                }
        }
        return null;
    }
    
    
    @Override
    public float getSalary() {
        float sum = salary; //this one’s salary
        //add in subordinates salaries
        for(int i = 0; i < subordinates.size(); i++) {
            sum += ((Employee)subordinates.elementAt(i)).getSalary();
        }
        return sum;
    }
    
    public boolean isManager(){
        return false;
    }
    
}
