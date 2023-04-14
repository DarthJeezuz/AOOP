class Solution {
    public static int trap(int[] height) {
        int left = 0;
        int right = 0;
        int minus = 0;
        int len = 0;
        int water = 0;
        boolean isTrap = false;
        boolean close = false;

        for(int item : height){

            if(isTrap){
                // reason to close
                if(item >= left){

                    if(item > left){
                        right = left;
                    }
                    else{
                        right = item;
                    }
                    close = true;
                }

                // Checking for isthmus
                if(item > 0 && item < left){
                    minus++;
                }
                if(!close){
                    //we are not closing, therefore we add to length
                    len++;
                }
            }
            if(close){
                //Add trapped water
                water += (len * right) - minus;
                isTrap = false;
                close = false;

            }
            else if(item > 0 && !isTrap){
                //item is > 0, & no trap is set; assign left flag
                left = item;
                len = 0;
                minus = 0;
                isTrap = true;
            }
        }
        System.out.println("Output: " + water);
        return water;
    }
    public static void main(String[] args){
        int[] arr = {1,0,1,2,0,1,0,3,1,0,1};
        int[] arr1 = {2,0,0,0,2};
        int[] arr2 = {2,0,1,0,2,3,0,3};
        int[] arr3 = {3,0,0,0,0};
        System.out.println(trap(arr));
        System.out.println(trap(arr1));
        System.out.println(trap(arr2));
        System.out.println(trap(arr3));
    }
}
