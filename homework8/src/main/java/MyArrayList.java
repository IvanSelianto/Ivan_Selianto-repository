import java.util.*;

public class MyArrayList<T>   {
    private  int count = 0;
    private Object[] array = new Object[16];
    private Object[] array1 = new Object[16];
     List s = new ArrayList();

  //  private Object[] array1 = new Object[count+1];


    public  void add(T object){
        if(count> array.length){
            array1 = new Object[array.length*2];
            System.arraycopy(array, 0 ,array1,0,array1.length);
            array = array1;

        }
        array[count] = object;
        count++;
    }
    public void delete(int index){
        array[index] = null;
        System.arraycopy(array, index, array, index, array.length-index-1);


        Map map =new HashMap();
    }
    public  int size(){
        return  count;
    }
    public T get(int index){
        if(index>count){
            throw new ArrayIndexOutOfBoundsException();
        }
        Object object = array[index];

        return (T)object;
    }
    public void print(){
        for (int i = 0; i < array.length ; i++) {
            if(array[i]!=null){
                System.out.print(array[i]+" ");
            }

        }
        System.out.println("\n");
    }
}
