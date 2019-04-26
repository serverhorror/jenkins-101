package dev.assetkit.java101;

public class Greeter {

    private final String name;

    public Greeter(String name){
        this.name = name;
    }

    public String greet(){
        return this.toString();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
