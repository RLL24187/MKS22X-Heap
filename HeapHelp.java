public class HeapHelp{
	private static final String SPACES = "                                                                                                                                                    ";
	public static String toString(int [] arr){ //very large arrays will not work due to SPACES running out of space :o
	//as the visual representation of the tree is generated from the bottom to the top, if one of you number's suddenly has many digits you run the risk of an error being thrown
		if (arr.length == 0){return "";}
		int height = (int)(Math.log(arr.length) / Math.log(2)) + 1;
		String lastRow = ""; String edge = ""; String out = "";
		for (int i = (int)Math.pow(2,height-1) - 1; i < arr.length; i++){ //make the last row of numbers
			String space = " ";
			if (i % 2 == 1){space = "   ";}
			lastRow += arr[i] + space;
		}
		out+=lastRow;
		for (int level = height-2; level >= 0; level--){
			edge = ""; boolean first = false; // first - alternate placement
			for (int o = 0; o < lastRow.length();o++){ // make the edges
				if (o > 0  && !first&& !lastRow.substring(o-1,o).equals(" ") && lastRow.substring(o,o+1).equals(" ")){
					edge+="/"; first = true;
				}
				else if (o < lastRow.length()-1 &&first&&lastRow.substring(o,o+1).equals(" ") && !lastRow.substring(o+1,o+2).equals(" ")){
					edge += "\\ "; o++; //o is one behind the spaces
					while (o < lastRow.length() && !lastRow.substring(o,o+1).equals(" ")){edge+=" ";o++;} //o move ahead by one
					first = false; //for no odd placement
				}   //works!!!
				else{edge+=" ";}
			}
			out = edge+"\n"+out;
			lastRow = ""; //clear last row to make room for next one
			int originalLength = edge.length();
			for (int e = 0; e < (int)Math.pow(2,level); e++){ //place the numbers
				int num = arr[(int)Math.pow(2,level) - 1 + e];
				String beforeSpace = ""; String afterSpace = "";
				int firstIndex = edge.indexOf("/");
				int secondIndex = firstIndex;
				if (edge.indexOf("\\") != -1){secondIndex = edge.indexOf("\\");}
				int avg = (int) Math.round((firstIndex + secondIndex)/2.0);
				if (firstIndex != -1) {avg-= (num+"").length()/2;}
				if (firstIndex == -1){
					beforeSpace = "   ";
					afterSpace = " ";
				}
				else {
					if (avg > 0){beforeSpace = SPACES.substring(0,avg);}else {avg = 0;}
					if (secondIndex < avg){throw new IllegalArgumentException("sorry one of your numbers is too big and too far down the tree. Beats me why you would try that anyway");}
					afterSpace = SPACES.substring(0,secondIndex - avg);
				}
				String newPart = beforeSpace + num + afterSpace;
				int secondSlash = edge.indexOf("/") + edge.substring(edge.indexOf("/")+1).indexOf("/") + 1;
				if (newPart.length() > secondSlash){newPart = beforeSpace + num + " ";}
				lastRow += newPart;
				if (newPart.length() < edge.length()) {edge = edge.substring(newPart.length());}
				else {edge = "";}
			}
			out= lastRow + "\n" + out;
		}
		//System.out.println("**********************************");
		return out;
	}
	public static boolean isValidHeap(int[] data) { //checks if it is a valid heap
		for (int i = data.length - 1; i >= 0; i--){
			if (data[i] > data[(i-1)/2]){return false;}
		}
		return true;
	}
}
/* height = 4
0|           100
 |      /          \
1|     19          36
 |   /    \       /   \
2|  5      6     7     8
 | / \    / \   / \   / \
3|1   10 1   1 1   1 1   1
 /
1
*/
