//solo

class CoffeeLady {
  
   private Coffee_maker coffeemaker;
   private Extra[] extras;
  
   
   public CoffeeLady(Coffee_maker coffeemaker ,Extra[] extras) {
	   
       this.coffeemaker = coffeemaker;   
       this.extras = extras;
   }
  
   
   public void brew_coffee() {
      
       if(coffeemaker.getBrewingStage() == 0) {
    	   
           System.out.println("Putting empty pot on warmer");
           System.out.println("Filling boiler with water");
           System.out.println("Putting filter and coffee grounds into filter holder");
           System.out.println("Loading it into the receptacle");
           System.out.println("selecting " + coffeemaker.getCoffee().getStrength() + " coffee strength");
          
          
           System.out.println("Pressing brew button");
          
           coffeemaker.brew();
       }
       
       else {
           
    	   coffeemaker.brew();  
       }
   }
  
  
   public double pour_coffee() {

	   //To calculate the total coffee cost
       double cost = 0;
       cost = cost + coffeemaker.getCoffee().getCost();
      
       for(Extra e:extras) {
    	   
           cost = cost + e.getCost();
       }
      
       return cost;
   }

   
   public Coffee_maker getCoffeemaker() {
       
	   return coffeemaker;
   }

   
   public void setCoffeemaker(Coffee_maker coffeemaker) {
       
	   this.coffeemaker = coffeemaker;
   }

   
   public Extra[] getExtras() {
       
	   return extras;
   }

   
   public void setExtras(Extra[] extras) {
       
	   this.extras = extras;
   }
  
  
}



class Coffee_maker {
  

	//To denote whether a pot is lifted or not
	private boolean lifted;

	//To denote whether the indicator is on or not
	private boolean indicator;

	//To set the spray force according to the type of coffee
	private String spray_force;

	//To indicate the stage of coffee brewing(0-3)
	private int brewingStage;


	private Coffee coffee;
  
	
   public Coffee_maker(Coffee coffee) {
	   this.coffee = coffee;
	   this.lifted= true;
	   this.indicator = false;
	   this.brewingStage = 0;
   }
  
   
   public Coffee getCoffee() {
       
	   return coffee;
   }
  
   
   public void setCoffee(Coffee coffee) {
       
	   this.coffee = coffee;
   }
  
   
   public boolean isIndicator() {
       
	   return indicator;
   }

   
   public void setIndicator(boolean indicator) {
       
	   this.indicator = indicator;
   }
  
   
   private void boiling_prep() {
       
	   System.out.println("Closing the (pressure) relief valve");
       System.out.println("Turning on the boiler");
   }
  
   
   private void done_boiling() {
       
	   System.out.println("Turning off boiler heater");
       System.out.println("Opening the (pressure) relief valve");
   }
  
   
   public void interrupt_brewing() {
      
	   remove_pot();
       done_boiling();
       
       if (indicator) {
    	   
    	   System.out.println("Turning indicator off");
       }
   }
  
   
   public void replace_pot() {
   
	   this.lifted = false;
      
       System.out.println("Turning on plate warmer");
       boiling_prep();
       this.setSpray_force(coffee.getStrength());
       this.setIndicator(true);
   }
  
  
   public void remove_pot() {
      
       System.out.println("Turning off plate warmer");
       this.lifted = true;
   }
  
   
   public int brew() {
      
       int i = brewingStage;
          
       //At the first stage of coffee brewing
       
       if (i == 0) {
    	   
    	   boiling_prep();
           this.setSpray_force(coffee.getStrength());
           System.out.println("Turning indicator on");
           this.setIndicator(true);
              
           System.out.println("Brewing");           
           brewingStage = brewingStage + 1;
           return brewingStage;    
       }

       // The other stages
       else if (i > 0 && i < 4) {
               
    	   System.out.println("Brewing");         
    	   brewingStage = brewingStage + 1;           
    	   return brewingStage;    
       }

       
       //If coffee is tried to brewed beyond the allowable stages
       else {
    	   done_boiling();
    	   System.out.println("Brewing is done.No more brewing");
    	   return brewingStage;
    	   
       }    
   }  
          


   public String getSpray_force() {

	   return spray_force;
   }

   
   public void setSpray_force(String spray_force) {
   
	   this.spray_force = spray_force;
   }

   
   public int getBrewingStage() {
   
	   return brewingStage;
   }

   
   public void setBrewingStage(int brewingStage) {
   
	   this.brewingStage = brewingStage;
   }
}




class Coffee {
  
   private double cost;
   private String strength;
  
   
   public Coffee(double cost) {
      
       this.cost = cost;
       this.strength = "medium";
   }
  
   
   public Coffee(double cost, String strength) {
      
       this.cost = cost;
       this.strength = strength;
   }

   
   public String getStrength() {
   
	   return strength;
   }

   
   public void setStrength(String strength) {
   
	   this.strength = strength;
   }

   
   public double getCost() {
   
	   return cost;
   }

   
   public void setCost(double cost) {
   
	   this.cost = cost;
   }
  
  
}


/*

To demonstrate the coffee extras like mocha, whip or other user-defined extras

*/

class Extra {
  
   private String name;
   private double cost;
  
   
   public Extra(String name, double cost) {
      
       this.name = name;
       this.cost = cost;
   }
   
   
   public String getName() {
   
	   return name;
   }
   
   
   public void setName(String name) {
   
	   this.name = name;
   }
   
   
   public double getCost() {
   
	   return cost;
   }
   
   
   public void setCost(double cost) {
   
	   this.cost = cost;
   }
  
  
}




public class MakeCoffee {

   public static void main(String[] args) {
      
       //In the morning
       Coffee c1 = new Coffee(1.10,"medium");
       Coffee_maker m = new Coffee_maker(c1);
      
       Extra[] e1 = new Extra[1];
      
       e1[0] = new Extra("whip",1.25);
      
       CoffeeLady l = new CoffeeLady(m,e1);
      
       //Brewing coffee 3 times out of 4 times
       l.brew_coffee();
       l.brew_coffee();
       l.brew_coffee();
      
       //Interrupted
       l.getCoffeemaker().interrupt_brewing();
       l.getCoffeemaker().replace_pot();
      
       System.out.println("The cost written down is $" + l.pour_coffee());
      
       //These statements are for formatting purposes
       System.out.println();
       System.out.println();
      
       //In the evening
              
       //Own coffee
       Coffee c2 = new Coffee(1.10,"light");
       m.setCoffee(c2);
       m.setBrewingStage(0);
      
       e1 = new Extra[1];
      
       //Suppose the cost of cinnamon is $2.00
       e1[0] = new Extra("cinnamon",2.00);
      
       l.setExtras(e1);
      
       //Brewing coffee 1 time out of 4 times
       l.brew_coffee();
      
       l.getCoffeemaker().interrupt_brewing();
      
       l.getCoffeemaker().replace_pot();
       l.brew_coffee();
       l.brew_coffee();
       l.brew_coffee();
      
       //At this stage there will be no more brewing
       l.brew_coffee();
      
       System.out.println("The cost written down is $" + l.pour_coffee());
      
       //These statements are for formatting purposes
       System.out.println();
       System.out.println();
      
       //Friend's coffee
      
       //As the type of coffee is not mentioned,hence medium will be selected
       Coffee c3 = new Coffee(1.10);
      
       m.setCoffee(c3);
       m.setBrewingStage(0);
      
       e1 = new Extra[1];
      
       e1[0] = new Extra("mocha",0.90);
      
       l.setExtras(e1);
      
       //Full brewing done
       l.brew_coffee();
       l.brew_coffee();
       l.brew_coffee();
       l.brew_coffee();
      
       System.out.println("The cost written down is $" + l.pour_coffee());
      
   }

}
