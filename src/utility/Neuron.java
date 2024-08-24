package utility;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Neuron {
    private List<Value> w;
    private Value b;

    public Neuron(int nim) {
        Random random = new Random();
        w = new ArrayList<>();
        for (int i = 0; i < nim; i++) {
            w.add(new Value(random.nextDouble() * 2 - 1));
        }
        b = new Value(random.nextDouble() * 2 - 1);
    }

    public Value call(List<Value> x) {
        Value act = b;
        for (int i = 0; i < w.size(); i++) {
            act = act.add(w.get(i).mul(x.get(i)));
        }
        return act.tanh();
    }

    public List<Value> parameters() {
        List<Value> params = new ArrayList<>(w);
        params.add(b);
        return params;
    }
}