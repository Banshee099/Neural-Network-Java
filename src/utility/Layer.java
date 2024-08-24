package utility;
import java.util.ArrayList;
import java.util.List;

public class Layer {
    private List<Neuron> neurons;

    public Layer(int nim, int nout) {
        neurons = new ArrayList<>();
        for (int i = 0; i < nout; i++) {
            neurons.add(new Neuron(nim));
        }
    }

    public List<Value> call(List<Value> x) {
        List<Value> outs = new ArrayList<>();
        for (Neuron neuron : neurons) {
            outs.add(neuron.call(x));
        }
        return outs.size() == 1 ? outs.subList(0, 1) : outs;
    }

    public List<Value> parameters() {
        List<Value> params = new ArrayList<>();
        for (Neuron neuron : neurons) {
            params.addAll(neuron.parameters());
        }
        return params;
    }
}