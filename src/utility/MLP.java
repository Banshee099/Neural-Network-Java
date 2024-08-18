package utility;

import java.util.ArrayList;
import java.util.List;

public class MLP {

    public List<Layer> layers;

    public MLP(double nin, List<Double> nouts){
        List<Double> sz = new ArrayList<>();
        sz.add(nin);
        sz.addAll(nouts);
        layers=new ArrayList<>();
        for(int i=0;i<nouts.size();i++){
            layers.add(new Layer(sz.get(i),sz.get(i+1)));
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
