import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Encrypter {

    private Random random;
    private Integer shiftingAmount;
    private Integer permutationSize;
    private ArrayList<Integer> order;
    private String key;

    public Encrypter() {
        random = new Random();
        setShiftingAmount(random.nextInt(94) + 1);
        setPermutationSize(random.nextInt(5) + 5);
        order = new ArrayList<>();
        for (int i=0; i<getPermutationSize(); i++) {
            order.add(i);
        }
        Collections.shuffle(order);
    }

    public String encrypt(String text) {
        String encryptedText;
        char [] encryptedCharArr = new char[text.length()];
        char [] tempPermutationArr = new char[getPermutationSize()];
        int tempExtra = text.length()%permutationSize;
        if (tempExtra != 0) {
            for (int i=0; i<tempExtra; i++) {
                text+=" ";
            }
        }
        for ()
    }

    public String generateKey() {
        int temp = getShiftingAmount()*1000;
        key = null;
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