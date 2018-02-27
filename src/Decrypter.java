import java.lang.reflect.Array;

public class Decrypter {

    private String key;
    private int shiftingAmount;
    private int permutationSize;
    private int [] order;

    public Decrypter(String key) {
        this.key = key;
        char [] keyCharArr = key.toCharArray();
        if (((int)keyCharArr[0] - 65)< 32) {
            shiftingAmount = 126 - ((int)keyCharArr[0] - 65);
        }
        shiftingAmount = ((int) keyCharArr[0]) - 65;
        permutationSize = ((int) keyCharArr[1]) - 65;
        order = new int[permutationSize];
        for (int i=0; i<permutationSize; i++) {
            order[i] = ((int) keyCharArr[2+i]) - 65;
        }
    }

    public String decrypt(String text) {

        String decryptedText = "";
        char [] encryptedCharArr = text.toCharArray();
        int [] encryptedAsciiArr = new int[encryptedCharArr.length];
        int [] shiftedAsciiArr = new int[encryptedCharArr.length];
        char [] shiftedCharArr = new char[encryptedAsciiArr.length];
        char [] decryptedCharArr = new char[encryptedCharArr.length];
        char [] tempPermutationArr = new char[permutationSize];

//        System.out.println(permutationSize);
//        for(int i=0; i<order.length; i++) {
//            System.out.print(order[i] + " ");
//        }
//        System.out.println();


        for (int i=0; i<encryptedCharArr.length; i++) {
            encryptedAsciiArr[i] = (int) encryptedCharArr[i];
            int temp = encryptedAsciiArr[i] - shiftingAmount;
            if (temp < 32) {
                shiftedAsciiArr[i] = 126 - (32-temp);
            } else {
                shiftedAsciiArr[i] =encryptedAsciiArr[i] - shiftingAmount;
            }
            shiftedCharArr[i] = (char) shiftedAsciiArr[i];
        }

//        for(int i=0; i<encryptedAsciiArr.length; i++) {
//            System.out.print(encryptedAsciiArr[i] + " ");
//            System.out.print(encryptedCharArr[i] + " ");
//        }
//        System.out.println();
//
//        for(int i=0; i<shiftedAsciiArr.length; i++) {
//            System.out.print(shiftedCharArr[i] + " ");
//            System.out.print(shiftedAsciiArr[i] + " ");
//        }
//        System.out.println();

//        System.out.println(shiftedCharArr.length);
//        System.out.println(permutationSize);
//        System.out.println(shiftedCharArr.length - permutationSize);

        for(int i=0; i<order.length; i++) {
            System.out.print(order[i]);
        }

        for (int i=0; i<=(shiftedCharArr.length - permutationSize); i+=permutationSize) {
            for (int j=0; j<permutationSize; j++) {

                tempPermutationArr[j] = shiftedCharArr[i+indexOf(order, j)];
                System.out.println(tempPermutationArr[j]);
            }
            for (int k=0; k<tempPermutationArr.length; k++) {
                decryptedCharArr[i+k] = tempPermutationArr[k];
                decryptedText += decryptedCharArr[i+k];
            }
        }
        System.out.println(decryptedText);
        return decryptedText;
    }

    public int indexOf(int [] arr, int element) {
        for(int i=0; i<arr.length; i++) {
            if (arr[i] == element) {
                return i;
            }
        }
        return Integer.parseInt(null);
    }

}
