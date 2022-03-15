public class Utils {

    public static boolean repeatNumSearcher(int numb){
        char[] chars = String.valueOf(numb).toCharArray();
        for (int i=0; i<chars.length; i++){
            int n = 0;
            for (int j=i; j<chars.length; j++){
                if (chars[i] == chars[j]){
                    n++;
                }
            }
            if (n==2){
                return true;
            }
        }
        return false;
    }
}

