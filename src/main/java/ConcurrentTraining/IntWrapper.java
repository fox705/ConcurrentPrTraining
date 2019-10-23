package ConcurrentTraining;

public class IntWrapper {
    private Object key = new Object();
    private int i;

    public IntWrapper(int i){

        this.i = i;
    }

    public void setI(int i){

        this.i = i;
    }

    public int getI(){

        return this.i;
    }

    public void incrementI(){
        synchronized (key){
            this.i = i + 1 ;}

    }



}
