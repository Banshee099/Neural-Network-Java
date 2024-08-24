package utility;
import java.util.*;

public class Value {
    private double data;
    private double grad;
    private Runnable _backward;
    private Set<Value> _prev;
    private String _op;
    private String label;

    public Value(double data) {
        this(data, new HashSet<>(), "", "");
    }

    public Value(double data, Set<Value> _children, String _op) {
        this(data, _children, _op, "");
    }

    public Value(double data, Set<Value> _children, String _op, String label) {
        this.data = data;
        this.grad = 0;
        this._backward = () -> {};
        this._prev = _children;
        this._op = _op;
        this.label = label;
    }

    @Override
    public String toString() {
        return "Value(data=" + data + ")";
    }

    public Value add(Value other) {
        Value out = new Value(this.data + other.data, new HashSet<>(Arrays.asList(this, other)), "+");

        out._backward = () -> {
            this.grad += 1.0 * out.grad;
            other.grad += 1.0 * out.grad;
        };

        return out;
    }

    public Value add(double other) {
        return add(new Value(other));
    }

    public Value mul(Value other) {
        Value out = new Value(this.data * other.data, new HashSet<>(Arrays.asList(this, other)), "*");

        out._backward = () -> {
            this.grad += other.data * out.grad;
            other.grad += this.data * out.grad;
        };

        return out;
    }

    public Value mul(double other) {
        return mul(new Value(other));
    }

    public Value neg() {
        return this.mul(-1);
    }

    public Value sub(Value other) {
        return this.add(other.neg());
    }

    public Value pow(double other) {
        Value out = new Value(Math.pow(this.data, other), new HashSet<>(Collections.singletonList(this)), "**" + other);

        out._backward = () -> {
            this.grad += other * Math.pow(this.data, other - 1);
        };

        return out;
    }

    public Value div(Value other) {
        return this.mul(other.pow(-1));
    }

    public Value tanh() {
        double t = (Math.exp(2 * this.data) - 1) / (Math.exp(2 * this.data) + 1);
        Value out = new Value(t, new HashSet<>(Collections.singletonList(this)), "tanh");

        out._backward = () -> {
            this.grad += (1.0 - t * t) * out.grad;
        };

        return out;
    }

    public Value exp() {
        double e = Math.exp(this.data);
        Value out = new Value(e, new HashSet<>(Collections.singletonList(this)), "exp");

        out._backward = () -> {
            this.grad += out.data * out.grad;
        };

        return out;
    }

    public void backward() {
        Set<Value> visited = new HashSet<>();
        List<Value> topo = new ArrayList<>();
        buildTopo(this, visited, topo);
        this.grad = 1.0;

        Collections.reverse(topo);
        for (Value node : topo) {
            node._backward.run();
        }
    }

    private void buildTopo(Value v, Set<Value> visited, List<Value> topo) {
        if (!visited.contains(v)) {
            visited.add(v);
            for (Value child : v._prev) {
                buildTopo(child, visited, topo);
            }
            topo.add(v);
        }
    }

    // Getter and Setter methods for data and grad
    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }

    public double getGrad() {
        return grad;
    }

    public void setGrad(double grad) {
        this.grad = grad;
    }
    }