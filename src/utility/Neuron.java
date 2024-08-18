package utility;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Neuron {
    List<Value> weights;
    Value bias;
    Random rand = new Random();

    public Neuron(double nin) {
        this.weights = new ArrayList<>();
        for(int i = 0; i < nin; i++) {
            this.weights.add(new Value(-1.0 +(1.0 - (-1)) * rand.nextDouble(-1,1)));
        }
        this.bias = new Value(rand.nextDouble(-1,1));
    }

    public Value call(List<Value> x) {
        Value act = new Value(0);
        for (int i=0;i<this.weights.size();i++) {
            act = act.add(this.weights.get(i).mul(x.get(i)));
        }
        act = act.add(this.bias);
        return act.tanh();
    }

    public List<Value> parameters() {
        List<Value> params = new ArrayList<>(this.weights);
        params.add(this.bias);
        return params;
    }
}
