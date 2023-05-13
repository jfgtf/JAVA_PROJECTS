import java.util.ArrayList;

public class CodingBat{

    public boolean firstLast6(int[] nums) {
        if(nums[0] == 6 || nums[nums.length - 1] == 6)
            return true;

        return false;
    }

    public boolean sameFirstLast(int[] nums) {
        if(nums.length >= 1 && nums[0] == nums[nums.length-1])
            return true;

        return false;
    }

    public int[] makePi() {
        return new int[]{3, 1, 4};
    }

    public boolean commonEnd(int[] a, int[] b) {
        if(a[0] == b[0] || a[a.length-1] == b[b.length-1])
            return true;

        return false;
    }

    public int countEvens(int[] nums) {
        int i=0;
        int count = 0;
        while(i < nums.length){
            if (nums[i]%2 == 0){
                count ++;
            }
            i++;
        }
        return count;
    }

    public int bigDiff(int[] nums) {
        int max = 0;
        int min = 2147483647;
        for (int num:nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return max - min;
    }
}
