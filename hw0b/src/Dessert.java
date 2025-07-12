public class Dessert {
    private int flavor;
    private int price;
    public static int numDesserts=0;
    public Dessert(int flavor,int price){
        this.flavor=flavor;
        this.price=price;
        numDesserts+=1;
    }
    public void printDessert(){
        System.out.printf("%d %d %d\n",flavor,price,numDesserts);
    }
    public static void main(String[] args){
        System.out.println("I love dessert!");
    }
}
