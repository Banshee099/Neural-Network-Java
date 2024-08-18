package utility;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    public List<Neuron> neurons = new ArrayList<>();
    public Layer(double nin,double nout){
        for(int i=0;i<nout;i++){
            neurons.add(new Neuron(nin));
        }
    }

    public List<Value> call(List<Value> x){
        ArrayList<Value> outs = new ArrayList<>();
        for(Neuron n : neurons){
            outs.add(n.call(x));
        }
        return outs.size() == 1 ? List.of(outs.get(0)) : outs;
    }

    public List<Value> parameters(){
        ArrayList<Value> outs = new ArrayList<>();
        for (Neuron n : neurons){
            for (Value v : n.parameters()){
                outs.add(v);
            }
        }
        return outs;
    }
}
