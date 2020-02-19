import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution
{
    public static void main(String[] args)
    {
        System.out.println(new Solution().subsets(new ArrayList<>()
        {{
            add(1);
            add(2);
            add(3);
            add(4);
        }}));
    }
    
    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A)
    {
        Collections.sort(A);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        bt(0, result, A, new ArrayList<>());
        return result;
    }
    
    public void bt(int s, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> A, ArrayList<Integer> list)
    {
        if(list.size() == A.size())
        {
            result.add((ArrayList<Integer>)list.clone());
            return;
        }
        
        for(int i = s; i < A.size(); i++)
        {
            list.add(A.get(i));
            swap(A,s,i);
            bt(s + 1,result,A,list);
            swap(A,s,i);
            list.remove(list.size() - 1);
            
        }
    }
    
    public void swap(List<Integer> A, int s, int i){
        Integer ii = A.remove(i);
        A.add(s,ii);
    }
}