//With start and end indices
//Time Complexity - O(n)

public class KadanesAlgorithm{
    public static void main(String[] args){
        int[] array = new int[]{1, -3, -4, 2, 3, 3, -1, 5, -1, -7};
        System.out.println(Kadanes(array));
    }

    public static int Kadanes(int[] array){
        int currentMax = 0;
        int actualMax = 0;
        int startIndex = 0, endIndex = 0, tempIndex = 0;

        for(int i = 0; i < array.length; i++){
            currentMax += array[i];
            
            if(currentMax < 0){
                currentMax = 0;
                tempIndex = i + 1;
            }
            else if(actualMax < currentMax){
                actualMax = currentMax;
                startIndex = tempIndex;
                endIndex = i;

            }
        }
        System.out.println("Start index: " + startIndex + " End index: " + endIndex);
        return actualMax;
    } 
}