import java.util.*;

/**
 * This class creates all permutations of the given string
 *
 * @author Travis Chamness
 * @version 6/21/2019
 */
public class UniqueAnagrams
{
    private static final int MAX_WORD = 8;
    private ArrayList<String> anagrams;

    public UniqueAnagrams()
    {
        this.anagrams = new ArrayList<>();
    }

    public void permutations(String word)
    {
        permutations("", word);
        System.out.println("Possible anagrams = " + this.anagrams);

        TreeSet<String> toTest = new TreeSet(this.anagrams);
        System.out.println("Expected unique and sorted anagrams = " + toTest);
        System.out.println();

        sort(); // duplicates will be removed during the sorting process
    }

    //Whole word is in suffix
    private void permutations(String prefix, String suffix) //Recursive
    {
        if(suffix.length() >= MAX_WORD){ throw new IllegalArgumentException("Word too large, please choose smaller.");}

        int numOfChars = suffix.length();
        if (numOfChars == 1)
        {
//            System.out.println(prefix + suffix);
            this.anagrams.add(prefix + suffix);
        }
        else
        {
            //TODO Project2 - Permutations
           for(int i = 0; i < numOfChars; i++)
           {
              permutations( prefix + suffix.charAt(i), suffix.substring(0,i) + suffix.substring(i+1,numOfChars)); //Discretely identifies where suffix is based on i position
           }
        }
    }

    private void sort()
    {
        //TODO Project2
        for(int index = 0; index < this.anagrams.size() - 1; index++)
        {
            int indexOfSmallest = getIndexOfSmallestAndRemoveDuplicates(index,this.anagrams.size()-1);
            swap(indexOfSmallest, index);
        }

        // calls getIndexOfSmallestAndRemoveDuplicates(index, this.anagrams.size());
        // calls swap(index, indexOfNextSmallest);
    }

    private int getIndexOfSmallestAndRemoveDuplicates(int first, int last)
    {
        //TODO Project2

            int indexOfMin = first; //index of smallest
            String min = this.anagrams.get(first); //hold for comparisons for smallest

            for(int index = first+1; index <= last; index++)
            {
                if(this.anagrams.get(index).compareTo(min) == 0)
                {

                    swap(index,this.anagrams.size()-1);
                    this.anagrams.remove(this.anagrams.size()-1);
                    last--;
                    this.anagrams.trimToSize();
                    index--; //backstep to check updated index value
                }else if(this.anagrams.get(index).compareTo(min) < 0)
                {

                    min = this.anagrams.get(index);
                    indexOfMin = index;

                }
            }
        // as the smallest is being found removes duplicates

        return indexOfMin; // THIS IS A STUB
    }

    private void swap(int i, int j)
    {
        String temp = this.anagrams.get(i);
        this.anagrams.set(i, this.anagrams.get(j));
        this.anagrams.set(j, temp);
        //TODO Project2 - Swap - Done
    }

    private void display()
    {
        System.out.println("Computed unique and sorted anagrams = " + this.anagrams);
    }

    public static void main(String[] args)
    {
        UniqueAnagrams uniqueAnagrams = new UniqueAnagrams();
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please enter a word");
        String word = keyboard.next();

        uniqueAnagrams.permutations(word);
        uniqueAnagrams.display();
    }
}
