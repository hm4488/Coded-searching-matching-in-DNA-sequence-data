import java.util.Arrays;
import java.util.Random;
import java.util.*;

public class CodedSearching {
   static int ops = 0;
   static int linear_Ops = 0;
   static int binary_Ops = 0;
   static int interpolation_Ops = 0;
   static int unsuccessful_Linear_Ops = 0;
   static int unsuccessful_Binary_Ops = 0;
   static int unsuccessful_Interpolation_Ops = 0;
   static int average_Ops = 0;
   static int linear_Ops_Per_Element = 0;
   static int binary_Ops_Per_Element = 0;
   static int interpolation_Ops_Per_Element = 0;
   static int[] listNumbers;
   static double average_total_Ops_For_Successful_Linear_Search = 0;
   static double average_total_Ops_For_Successful_Binary_Search = 0;
   static double average_total_Ops_For_Successful_Interpolation_Search = 0;
   static double average_total_Ops_For_Unsuccessful_Linear_Search = 0;
   static double average_total_Ops_For_Unsuccessful_Binary_Search = 0;
   static double average_total_Ops_For_Unsuccessful_Interpolation_Search = 0;
   static double totalAverage = 0;
   static double totalAverageLinearSearch = 0;
   static double totalAverageBinarySearch = 0;
   static double totalAverageInterpolationSearch = 0;
   
   static int linearSearchOrdered(int[] arr, int key) {
      int n = arr.length;
      ops = 0;
   
      for(int i = 0; i < n; i++) {
         ops++;
        
         if(arr[i] == key)
            return i;
         else if(arr[i] > key) {
            return -1;
         }
      }
      
      
            
      return -1;
   }

   static int binarySearch(int arr[], int key) {
      int start = 0;
      int end = arr.length - 1;
      ops = 0;
      binary_Ops_Per_Element = 0;
      
      while(start <= end) {
         ops++;
         
         int mid = (start + end) / 2;
         if(arr[mid] == key)
            return mid;
         else if(arr[mid] < key)
            start = mid + 1;
         else
            end = mid - 1;
      }
      
      
      return -1;
   }
   
  // key >= arr[low] && key <= arr[high]

   static int interpolationSearch(int[] arr, int key) {
      int low = 0;
      int high = arr.length - 1;
      ops = 0;
      
      // while(low <= high) {
      while(key >= arr[low] && key <= arr[high]){
         ops++;
         
         int index = low + (((key - arr[low]) * (high - low)) / 
                     (arr[high] - arr[low]));
         
         if(key == arr[index])
            return index;
         
         if(key < arr[index])
            high = index - 1;
         else
            low = index + 1;
      }
      
      
      return -1;
   }

   static void searchResult(String type, int key, int index) {
      if(index != -1)
      {
         if(type == "Linear")
         {
            //System.out.println("Linear Found: " + linear_Ops_Per_Element);
         }
         else if(type == "Binary")
         {
            //System.out.println("Binary Found: " + binary_Ops_Per_Element);
         }
         else if(type == "Interpolation")
         {
            //System.out.println("Interpolation Found: " + interpolation_Ops_Per_Element);
         }
         // // System.out.println(type + ": Found " + key + " at index " + index + 
                            //" in " + ops + " operations");
      }
      else
      {
         if(type == "Linear")
         {
           //System.out.println("Linear Not found: " + linear_Ops_Per_Element);
         }
         else if(type == "Binary")
         {
            //System.out.println("Binary Not found: " + binary_Ops_Per_Element);
         }
         else if(type == "Interpolation")
         {
            //System.out.println("Interpolation Not found: " + interpolation_Ops_Per_Element);
         }
      
        // System.out.println(type + ": Did not find " + key + " in " + ops + 
                           // " operations");
      }
   }
   
   
   
   
   static void printArray(int arr[]) { 
      int n = arr.length; 
      
      for(int i = 0; i < n; i++) 
         System.out.print(arr[i] + " "); 
      
      System.out.println(); 
   }

   public static void main(String[] args)
   {
     
      int index;
      
      int key;
      int unsuccessful_Key;
         
      //Random randomNo;
      LinkedList<int[]> listOfLists = new LinkedList<int[]>();
      //int j = 1;
      boolean flag = true;
      
      for(int p = 0; p < 100; p++)
      {
      listNumbers = randomNumberGenerator();
      //printArray(listNumbers);
         listOfLists.add(listNumbers);
         }
      
      
      for(int j = 0; j < 100; j++)
      {
        linear_Ops = 0;
        binary_Ops = 0;
        interpolation_Ops = 0;
        unsuccessful_Linear_Ops = 0;
        unsuccessful_Binary_Ops = 0;
        unsuccessful_Interpolation_Ops = 0;
      
         
      
      
         
         for(int i = 0; i < 1000; i++)
         {
                           
         
            key = listNumbers[i];
           
            searchResult("Linear", key, linearSearchOrdered(listOfLists.get(j), key));
            linear_Ops += ops;
            searchResult("Binary", key, binarySearch(listOfLists.get(j), key));
            binary_Ops += ops;
            searchResult("Interpolation", key, interpolationSearch(listOfLists.get(j), key));
            interpolation_Ops += ops;
         }
         
         average_total_Ops_For_Successful_Linear_Search += linear_Ops / 1000;
         //System.out.println("Total Average Successful Operations for Linear Search is " + average_total_Ops_For_Successful_Linear_Search);
         average_total_Ops_For_Successful_Binary_Search += binary_Ops / 1000;
        // System.out.println("Total Average Successful Operations for Binary Search is " + average_total_Ops_For_Successful_Binary_Search);
         average_total_Ops_For_Successful_Interpolation_Search += interpolation_Ops / 1000;
        // System.out.println("Total Average Successful Operations for Interpolation Search is " + average_total_Ops_For_Successful_Interpolation_Search);
                  
         //System.out.println();
         //System.out.println("Unsuccessful Searches");
         int value = 0;
      
         for(int i = 0; i < 1000; i++)
         {
            do
            {
               Random randomNo = new Random();
               unsuccessful_Key = randomNo.nextInt(10000);
            
            }while(interpolationSearch(listNumbers, unsuccessful_Key) != -1);
         
         
            searchResult("Linear", unsuccessful_Key, linearSearchOrdered(listOfLists.get(j), unsuccessful_Key));
            unsuccessful_Linear_Ops += ops;
            searchResult("Binary", unsuccessful_Key, binarySearch(listOfLists.get(j), unsuccessful_Key));
            unsuccessful_Binary_Ops += ops;
            searchResult("Interpolation", unsuccessful_Key, interpolationSearch(listOfLists.get(j), unsuccessful_Key));
            unsuccessful_Interpolation_Ops += ops;
         
         }
         
         average_total_Ops_For_Unsuccessful_Linear_Search += unsuccessful_Linear_Ops / 1000;
         //System.out.println("Total Average Unsuccessful Operations for Linear Search is " + average_total_Ops_For_Unsuccessful_Linear_Search);
         average_total_Ops_For_Unsuccessful_Binary_Search += unsuccessful_Binary_Ops / 1000;
         //System.out.println("Total Average Unsuccessful Operations for Binary Search is " + average_total_Ops_For_Unsuccessful_Binary_Search);
         average_total_Ops_For_Unsuccessful_Interpolation_Search += unsuccessful_Interpolation_Ops / 1000;
        // System.out.println("Total Average Unsuccessful Operations for Interpolation Search is " + average_total_Ops_For_Unsuccessful_Interpolation_Search);
      
         
      }   
      
      average_total_Ops_For_Successful_Linear_Search = average_total_Ops_For_Successful_Linear_Search/100;
      average_total_Ops_For_Successful_Binary_Search = average_total_Ops_For_Successful_Binary_Search/100;
      average_total_Ops_For_Successful_Interpolation_Search = average_total_Ops_For_Successful_Interpolation_Search/100;
      System.out.println();
      System.out.println("Successful Searches");
      System.out.println();
         //average_total_Ops_For_Successful_Linear_Search = linear_Ops / 1000;
         System.out.println("Total Average Successful Operations for Linear Search is " + average_total_Ops_For_Successful_Linear_Search);
         //average_total_Ops_For_Successful_Binary_Search = binary_Ops / 1000;
         System.out.println("Total Average Successful Operations for Binary Search is " + average_total_Ops_For_Successful_Binary_Search);
         //average_total_Ops_For_Successful_Interpolation_Search = interpolation_Ops / 1000;
         System.out.println("Total Average Successful Operations for Interpolation Search is " + average_total_Ops_For_Successful_Interpolation_Search);
        
       

       average_total_Ops_For_Unsuccessful_Linear_Search = average_total_Ops_For_Unsuccessful_Linear_Search/100;
      average_total_Ops_For_Unsuccessful_Binary_Search = average_total_Ops_For_Unsuccessful_Binary_Search/100;
      average_total_Ops_For_Unsuccessful_Interpolation_Search = average_total_Ops_For_Unsuccessful_Interpolation_Search/100;
      System.out.println();
      System.out.println("Unsuccessful Searches");
      System.out.println();
         //average_total_Ops_For_Unsuccessful_Linear_Search = unsuccessful_Linear_Ops / 1000;
         System.out.println("Total Average Unsuccessful Operations for Linear Search is " + average_total_Ops_For_Unsuccessful_Linear_Search);
         //average_total_Ops_For_Unsuccessful_Binary_Search = unsuccessful_Binary_Ops / 1000;
         System.out.println("Total Average Unsuccessful Operations for Binary Search is " + average_total_Ops_For_Unsuccessful_Binary_Search);
         //average_total_Ops_For_Unsuccessful_Interpolation_Search = unsuccessful_Interpolation_Ops / 1000;
         System.out.println("Total Average Unsuccessful Operations for Interpolation Search is " + average_total_Ops_For_Unsuccessful_Interpolation_Search);
         
         
         System.out.println();
      System.out.println("Total Average for linear searches");
      System.out.println();
      totalAverageLinearSearch = average_total_Ops_For_Successful_Linear_Search + average_total_Ops_For_Unsuccessful_Linear_Search/2;
      System.out.println(totalAverageLinearSearch);
       System.out.println();
      System.out.println("Total Average for binary searches");
      System.out.println();
      totalAverageBinarySearch = average_total_Ops_For_Successful_Binary_Search + average_total_Ops_For_Unsuccessful_Binary_Search/2;
      System.out.println(totalAverageBinarySearch);
       System.out.println();
      System.out.println("Total Average for interpolation searches");
      System.out.println();
      totalAverageInterpolationSearch = average_total_Ops_For_Successful_Interpolation_Search + average_total_Ops_For_Unsuccessful_Interpolation_Search/2;
      System.out.println(totalAverageInterpolationSearch);
      
      System.out.println();
      System.out.println("Total Average for all searches");
      System.out.println();
      totalAverage = totalAverageLinearSearch + totalAverageBinarySearch + totalAverageInterpolationSearch/3;
      System.out.println(totalAverage);
          
   }
      
         
        
       
       
 
   
   static int[] randomNumberGenerator()
   {
      int[] numArray = new int[1000];
     
      Random rand = new Random();
     
      for(int i = 0; i < numArray.length; i++)
      {
         numArray[i] = rand.nextInt(10000);
        //System.out.println(i + " : " +  numArray[i]);
      }
     
      Arrays.sort(numArray);
       //printArray(numArray);
      return numArray;
   }
   
   // static int[] eachElementNumber(int[] arr)
//    {
//       int n = arr.length; 
//       int value = 0;
//       for(int i = 0; i < n; i++) 
//       {
//          value = arr[i];
//       }
//    }
}