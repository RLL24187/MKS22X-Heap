import java.util.Random;
public class MyHeap{
  //important notes
  /*
  parent: n
  left child: 2n+1
  right child: 2n+2
  */
  private static int height(int size){
    int height = 0;
    while (size>0){
      height++;
      size /= 2;
    }
    return height;
  }

  private static int exp(int base, int expt){
    int temp = 1;
    for (int i = expt; i > 0; i--){
      temp *= base;
    }
    return temp;
  }

  private static void pushDown(int[]data,int size,int index){
    //storing indices
    int left = (2*index) + 1;
    int right = (2*index) + 2;
    //System.out.println("Size: "+size);
    //System.out.println("data[left]: "+data[left]);
    //System.out.println("data[right]: "+data[right]);
    //System.out.println("Left: "+left);
    //System.out.println("Right: "+right);
    //System.out.println("\nIndex: "+index);
    //System.out.println("data[index]: "+data[index]);
    //check if parent is a leaf
    if (left >= size){
      //System.out.println("out of bounds: left\n");
      return;
    }
    //case 1: left greater than parent
    //System.out.println(left);
    //System.out.println(size);
    else if (data[left] > data[index]){
      //System.out.println("left greater than parent\n");
      //case 1a: RIGHT IS NONEXISTENT OR LEFT IS GREATER OR EQUAL TO RIGHT
      if (right >= size || data[left]>=data[right]){
        int temp = data[left];
        data[left] = data[index];
        data[index] = temp;
        //System.out.println(HeapHelp.toString(data)+"\n");
        pushDown(data, size, left);
      }
      //case 1b: RIGHT IS GREATER THAN ORTO LEFT
      else{
        //System.out.println("right and left greater than parent, right > left");
        int temp = data[right];
        data[right] = data[index];
        data[index] = temp;
        //System.out.println(HeapHelp.toString(data)+"\n");
        pushDown(data, size, right);
      }
    }
    //case 2: right is nonexistent
    else if (right >= size){
      //System.out.println("out of bounds: right\n");
      return;
    }
    //case 3: right is greater than parent
    else if (data[right]>data[index]){
      //System.out.println("right greater than parent\n");
      int temp = data[right];
      data[right] = data[index];
      data[index] = temp;
      //System.out.println(HeapHelp.toString(data)+"\n");
      pushDown(data, size, right);
    }
    //case 4: none are greater than parent
    else{
      //System.out.println("none are greater than parent\n");
    }
  }
  /*
    - size  is the number of elements in the data array.
    - push the element at index i downward into the correct position. This will swap with the larger of the child nodes provided that child is larger. This stops when a leaf is reached, or neither child is larger. [ should be O(logn) ]
    - precondition: index is between 0 and size-1 inclusive
    - precondition: size is between 0 and data.length-1 inclusive.
  */
  private static void pushUp(int[]data,int index){
    while(index!=0){
      int parent = data[(index-1)/2];
      int cur = data[index];
      //System.out.println(parent);
      //System.out.println(cur);
      if (parent < cur){
        data[(index-1)/2]=cur;
        data[index]=parent;
        index = (index-1)/2; //change the index to parent
        //System.out.println("Index: "+index);
      }
      else return; //stops the loop
    }
  }
  /*
    - push the element at index i up into the correct position.
      This will swap it with the parent node until the parent node is larger or the root is reached.
      [ should be O(logn) ]
    - precondition: index is between 0 and data.length-1 inclusive.
  */

  public static void heapify(int[] data){
    int bound = exp(2, height(data.length) - 1) - 2;
    //only need to pushDown the second to last, then third to last, and so on until you get to the top
    for (int i = bound; i >= 0; i--){
      pushDown(data, data.length, i);
    }
  }
  //    - convert the array into a valid heap. [ should be O(n) ]

  public static void heapsort(int[] data){
    heapify(data); //heapify first
    int counter = 1;
    for (int x = data.length; x > 0; x--) {
      int temp = data[0]; //this is the max
      data[0] = data[data.length-counter]; //replace the last element with max
      data[data.length-counter] = temp;
      pushDown(data,data.length-counter,0); //push down the former last element and make sure you decrease the "size"
      counter++;
    }
  }
  //- sort the array by converting it into a heap then removing the largest value n-1 times. [ should be O(nlogn) ]

  public static String printArray(int[]ary){
    String output = "{";
    for (int i = 0; i < ary.length; i++){
      output += ary[i];
      if (i != ary.length - 1){
        output += ", ";
      }
    }
    return output + "}";
  }

  public static void main(String[] args){
    int[] indices = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    int[] a1 = {24, 9, 4, 8, 17, 6, 11, 6, 3, 19, 0, 1, 7, 39};
    //          0   1  2  3  4   5  6  7  8  9  10 11 12  13
    //length 14
/*
    System.out.println("Indices tree: ");
    System.out.println(HeapHelp.toString(indices));
    System.out.println("\na1:");
    System.out.println(HeapHelp.toString(a1));
    int[] pushUpTests = {5, 0, 10, 4, 9, 13};
    System.out.println("\n\n-------Testing pushUp-------");
    for (int i = 0; i < pushUpTests.length; i++){
      System.out.println("Testing pushUp with index "+pushUpTests[i] +": ");
      pushUp(a1, pushUpTests[i]);
      System.out.println(HeapHelp.toString(a1));
    }
*/
    // edited a1
    //39. 19, 24, 8, 17, 4, 6, 6, 3, 9, 0, 1, 7, 11
    //0   1   2   3  4   5  6  7  8  9  10 11 12 13

/*
    System.out.println("\n\n-------Testing height(size)-------");
    System.out.println(height(a1.length));
*/
/*
    System.out.println("\n\n-------Testing pushDown-------");
    int[] pushDownTests = {5, 1, 3, 13, 6, 6};
    for (int i = 0; i < pushDownTests.length; i++){
      System.out.println("Testing pushDown with index "+pushDownTests[i] +": ");
      pushDown(a1, a1.length, pushDownTests[i]);
      System.out.println(HeapHelp.toString(a1)+"\n");
    }
*/
/*
    System.out.println("\n\n-------Testing exp-------");
    System.out.println(exp(2, 4));//16
    System.out.println(exp(3, 3));//27
    System.out.println(exp(9, 0));//1
*/

    System.out.println("\n\n-------Testing heapify-------");
    int seed, size;
    if(args.length > 0){
      seed = Integer.parseInt(args[0]);
    }
    else{
      Random r = new Random();
      seed = r.nextInt(1000);
    }
    Random rnd = new Random(seed);
    System.out.println("Seed: "+seed);
    if(args.length > 1){
      size = Integer.parseInt(args[1]);
    }
    else{
      size = Math.abs(rnd.nextInt(25) + 1);
      //note to self: size cannot be >= 128  b/c HeapHelp.toString() stops working then
    }
    System.out.println("Size: " + size);
    int[] a2 = new int[size];
    for (int i = 0; i < size; i++){
      a2[i] = rnd.nextInt(100);
    }
    System.out.println("a2:\n"+HeapHelp.toString(a2));
    //heapify(a2);
    //System.out.println("a2:\n"+HeapHelp.toString(a2));


    System.out.println("\n\n-------Testing heapSort-------");
    System.out.println(printArray(a2));
    heapsort(a2);
    System.out.println(HeapHelp.toString(a2));
    System.out.println(printArray(a2));
  }

  
}

//sample tree
/*
                   24
              /         \
            9            4
          /   \        /   \
        8     17      6     11
      /  \   /  \   /  \   /  \
     6   3  19  0   1  7 -3   --

*/
//index tree
/*
                   0
              /         \
            1            2          last index in a row: 2^height - 2
          /   \        /   \
        3      4      5     6
      /  \   /  \   /  \   /  \
     7   8  9   10 11  12 13  14


*/
