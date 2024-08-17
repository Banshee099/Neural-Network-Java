package utility;
import java.lang.Math;
import java.util.*;
public class Value {
    public double data;
    public double grad;
    public Set<Value> _prev;
    public Runnable _backward;


    public Value(double data, Set<Value> _children) {
        this.data = data;
        this.grad = 0;
        this._prev = new HashSet<>(_children);
        this._backward = () -> {};
    }

    public Value(double data) {
        this(data, new HashSet<>());
    }



    // Equivalent to Python's __add__
    public Value add(Value other) {
        Value out = new Value(this.data + other.data, Set.of(this, other));
        out._backward = () -> {
            this.grad += out.grad;
            other.grad += out.grad;
        };
        return out;
    }

    public Value add(double other) {
        Value other_new = new Value(other,new LinkedHashSet<>());
        Value out = new Value(this.data + other_new.data,Set.of(this, other_new));
        out._backward = () -> {
            this.grad += out.grad;
            other_new.grad += out.grad;
        };
        return out;
    }

    public Value mul(Value other) {
        Value out = new Value(this.data * other.data,Set.of(this, other));
        out._backward = () -> {
            this.grad += other.data * out.grad;
            other.grad += this.data * out.grad;
        };
        return out;
    }

    public Value mul(double other) {
        Value other_new = new Value(other,new LinkedHashSet<>());
        Value out = new Value(this.data * other, Set.of(this, other_new));
        out._backward = () -> {
            this.grad += other_new.data * out.grad;
            other_new.grad += this.data * out.grad;
        };
        return out;
    }

    public Value div(double other) {
        return new Value(this.data * Math.pow(other,-1),new LinkedHashSet<>());
    }

    public Value sub(Value other) {
        return new Value(this.data + (-other.data),new LinkedHashSet<>());
    }

    public Value sub(double other) {
        return new Value(this.data + (-other), new LinkedHashSet<>());
    }

    public Value neg(Value this) {
        return new Value(this.data * (-1) ,new LinkedHashSet<>());
    }

    public Value tanh(){
        double x = this.data;
        double t = (Math.exp(2*x)-1)/(Math.exp(2*x)+1);
        Value out = new Value(t, Set.of(this));
        out._backward = () -> {
            this.grad += (1.0 - (Math.pow(t,2))) * out.grad;
        };
        return out;
    }

    public void backward() {
        Set<Value> visited = new HashSet<>();
        List<Value> topo = new ArrayList<>();
        buildTopo(this, visited, topo);

        this.grad = 1.0;
        for (int i = topo.size() - 1; i >= 0; i--) {
            topo.get(i)._backward.run();
        }
    }

    private void buildTopo(Value v, Set<Value> visited, List<Value> topo) {
        if (!visited.contains(v)) {
            visited.add(v);
            for (Value n : v._prev) {
                buildTopo(n, visited, topo);
            }
            topo.add(v);
        }
    }

    // Equivalent to Python's __str__
    @Override
    public String toString() {
        return "Value(data=" + data + ")";
    }
}
