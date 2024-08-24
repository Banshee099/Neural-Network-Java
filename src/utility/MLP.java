package utility;
import java.util.ArrayList;
import java.util.List;

public class MLP {
    private List<Layer> layers;

    public MLP(int nim, List<Integer> nouts) {
        List<Integer> sz = new ArrayList<>();
        sz.add(nim);
        sz.addAll(nouts);

        layers = new ArrayList<>();
        for (int i = 0; i < nouts.size(); i++) {
            layers.add(new Layer(sz.get(i), sz.get(i + 1)));
        }
    }

    public List<Value> call(List<Value> x) {
        for (Layer layer : layers) {
            x = layer.call(x);
        }
        return x;
    }

    public List<Value> parameters() {
        List<Value> params = new ArrayList<>();
        for (Layer layer : layers) {
            params.addAll(layer.parameters());
        }
        return params;
    }
}