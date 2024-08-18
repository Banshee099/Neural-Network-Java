package driver;
import utility.MLP;
import utility.Value;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MLP n = new MLP(3.0,List.of(4.0,4.0,1.0));

        List<List<Double>> xs = Arrays.asList(
                Arrays.asList(2.0, 3.0, -1.0),
                Arrays.asList(3.0, -1.0, 0.5),
                Arrays.asList(0.5, 1.0, 1.0),
                Arrays.asList(1.0, 1.0, -1.0)
        );
        List<Double> ys = List.of(1.0,-1.0,-1.0,1.0);
        Value loss = new Value(0.0);

        for(int i=0;i<20;i++){
            List<Value> ypred = new ArrayList<>();
            for(List<Double> x : xs) {
                List<Value> newX = new ArrayList<>();
                for (Double x1 : x) {
                    newX.add(new Value(x1));
                }
                ypred.addAll(n.call(newX));
            }

            for(int j=0;j<ypred.size();j++){
                loss = loss.add(Math.pow((ypred.get(j).data - ys.get(j)),2));
            }
            for(Value p : n.parameters()) {
                p.grad = 0.0;
            }

            loss.backward();

            for(Value p : n.parameters()) {
                p.data += -0.1*p.grad;

            }
            System.out.println(i +" "+ loss.data);
        }
    }
}
