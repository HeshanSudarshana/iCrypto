import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Encrypter {

    private Random random;
    private int shiftingAmount;
    private int permutationSize;
    private ArrayList<Integer> order;

    public Encrypter() {
        random = new Random();
        setShiftingAmount(random.nextInt(92) + 1);
        setPermutationSize(random.nextInt(5) + 5);
        order = new ArrayList<>();
        for (int i=0; i<getPermutationSize(); i++) {
            order.add(i);
        }
        Collections.shuffle(order);
    }

    public String encrypt(String text) {
        String encryptedText = "";
        int tempExtra = text.length()%permutationSize;
        System.out.println(tempExtra);
        if (tempExtra != 0) {
            tempExtra = permutationSize-tempExtra;
            for (int i=0; i<tempExtra; i++) {
                text+=" ";
            }
        }
        char [] encryptedCharArr = new char[text.length()];
        char [] permutatedCharArr = new char[text.length()];
        char [] textCharArr = text.toCharArray();
        char [] tempPermutationArr = new char[getPermutationSize()];
        int [] permutatedAscii = new int[text.length()];
        int [] shiftedAscii = new int[text.length()];

        System.out.println(getPermutationSize());

        for (int i=0; i<=(text.length()-getPermutationSize()); i+=getPermutationSize()) {
            for (int j=0; j<getPermutationSize(); j++) {
                tempPermutationArr[order.get(j)] = textCharArr[i+j];
            }
            for (int k=0; k<tempPermutationArr.length; k++) {
                permutatedCharArr[i+k] = tempPermutationArr[k];
            }
        }

        for (int i=0; i<permutatedCharArr.length; i++) {
            permutatedAscii[i] = (int) permutatedCharArr[i];
            int temp = permutatedAscii[i]+getShiftingAmount();
            if (temp>126) {
                shiftedAscii[i] = 32 + (temp-126);
            } else {
                shiftedAscii[i] = temp;
            }
            encryptedCharArr[i] = (char)shiftedAscii[i];
            encryptedText += encryptedCharArr[i];
        }
        System.out.println("Encrypted - " + encryptedText);
        return encryptedText;
    }

    public String generateKey() {
        String key = "";
        if ((getShiftingAmount()+65) > 126) {
            key += (char)(32+ (126- (getShiftingAmount() + 65)));
        } else {
            key += (char) (getShiftingAmount()+65);
        }
        key += (char)(getPermutationSize()+65);
        for (int i=0; i<order.size(); i++) {
            key += (char)(order.get(i)+65);
        }
        return key;
    }


    public Integer getShiftingAmount() {
        return shiftingAmount;
    }

    public void setShiftingAmount(Integer shiftingAmount) {
        this.shiftingAmount = shiftingAmount;
    }

    public Integer getPermutationSize() {
        return permutationSize;
    }

    public void setPermutationSize(Integer permutationSize) {
        this.permutationSize = permutationSize;
    }

    public ArrayList<Integer> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<Integer> order) {
        this.order = order;
    }
}