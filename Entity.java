package ex01;

public class Entity {
    int[] container;

    public Entity(){
        container = new int[2];
    }

    public void add(int index){
        container[index]++;
    }

    public int getCount(int index){
        return container[index];
    }
}
