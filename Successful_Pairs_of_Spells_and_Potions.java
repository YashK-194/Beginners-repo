import java.util.Arrays;

public class Successful_Pairs_of_Spells_and_Potions {
    public int[] successfulPairs(int[] spells, int[] potions, long target) {
        int n = spells.length;
        int[] ans = new int[n];
        Arrays.sort(potions);

        for(int i=0; i<n; i++){
            int count = 0;
            int x = 0, y = potions.length-1;
            int index = -1;

            inner: while(x <= y){
                int mid = x + (y - x) / 2;
                long val = (long) spells[i] * (long) potions[mid];

                if(val >= target) {
                    index = mid;
                    y = mid - 1;
                }
                else x = mid + 1;
            }

            if(index != -1) ans[i] = potions.length - index;
        }

        return ans;
    }
}
