package driver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utility.MLP;
import utility.Value;

public class Main {
    public static void main(String[] args) {
        MLP n = new MLP(3, Arrays.asList(4, 4, 1));

        List<List<Double>> xs = Arrays.asList(
                Arrays.asList(2.0, 3.0, -1.0),
                Arrays.asList(3.0, -1.0, 0.5),
                Arrays.asList(0.5, 1.0, 1.0),
                Arrays.asList(1.0, 1.0, -1.0)
        );

        List<Double> ys = Arrays.asList(1.0, -1.0, -1.0, 1.0);

        for (int k = 0; k < 20; k++) {
            List<Value> ypred = new ArrayList<>();

            // Forward pass
            for (List<Double> x : xs) {
                List<Value> xValues = new ArrayList<>();
                for (Double xi : x) {
                    xValues.add(new Value(xi));
                }
                ypred.add(n.call(xValues).get(0));
            }

            Value loss = new Value(0.0);
            for (int i = 0; i < ys.size(); i++) {
                Value diff = ypred.get(i).sub(new Value(ys.get(i)));
                loss = loss.add(diff.pow(2));
            }

            for (Value p : n.parameters()) {
                p.setGrad(0.0);
            }

            // Backward pass
            loss.backward();

            // Update
            for (Value p : n.parameters()) {
                p.setData(p.getData() - 0.05 * p.getGrad());
            }

            System.out.println(k + " = " + loss.getData());
        }
    }
}