package driver;
import utility.Value;

public class Main {
    public static void main(String[] args) {
        Value x1 = new Value(2.0);
        Value x2 = new Value(0.0);
        Value w1 =new Value(-3.0);
        Value w2 =new Value(1.0);
        Value b = new Value(6.88137358);
        Value x1w1 = x1.mul(w1);
        Value x2w2 = x2.mul(w2);
        Value x1w1x2w2 = x1w1.add(x2w2);
        Value n = x1w1x2w2.add(b);
        Value o = n.tanh();
    }
}
