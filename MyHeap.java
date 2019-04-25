public class MyHeap{
  //important notes
  /*
  parent: n
  left child: 2n+1
  right child: 2n+2
  */
  private static void pushDown(int[]data,int size,int index){

  }
  /*
    - size  is the number of elements in the data array.
    - push the element at index i downward into the correct position. This will swap with the larger of the child nodes provided thatchild is larger. This stops when a leaf is reached, or neither child is larger. [ should be O(logn) ]
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
      else index = 0; //stops the loop
    }
  }
  /*
    - push the element at index i up into the correct position. This will swap it with the parent node until the parent node is larger or the root is reached. [ should be O(logn) ]
    - precondition: index is between 0 and data.length-1 inclusive.
  */

  public static void heapify(int[] data){

  }
  //    - convert the array into a valid heap. [ should be O(n) ]

  public static void heapsort(int[] data){

  }
  //- sort the array by converting it into a heap then removing the largest value n-1 times. [ should be O(nlogn) ]
  public static void main(String[] args){
    int[] indices = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    int[] a1 = {24, 9, 4, 8, 17, 6, 1, 6, 3, 19, 0, 1, 7, -9};
    //          0   1  2  3  4   5  6  7  8  9  10 11 12  13
    //length 14
    System.out.println("Indices tree: ");
    System.out.println(HeapHelp.toString(indices));
    System.out.println("\na1:");
    System.out.println(HeapHelp.toString(a1));
    int[] pushUpTests = {5, 0, 10, 4, 9};
    for (int i = 0; i < pushUpTests.length; i++){
      System.out.println("Testing pushUp with index "+pushUpTests[i] +": ");
      pushUp(a1, pushUpTests[i]);
      System.out.println(HeapHelp.toString(a1));
    }
  }
}

//sample tree
/*
                   24
              /         \
            9            4
          /   \        /   \
        8     17      6     1
      /  \   /  \   /  \   /  \
     6   3  19  0   1  7 -9   --

*/
