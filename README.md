## Neural Network Implementation in Java

This repository contains a basic neural network implementation written in Java. The project is currently under development and is intended for educational purposes, providing a clear and straightforward example of how to build a neural network from scratch without relying on external libraries.

## Project Status
Status: Under Development 🚧

This project is still in the early stages of development. Features may be incomplete, and the implementation may change as it evolves.

## Features

- Feedforward Neural Network: Basic implementation of a fully connected feedforward neural network.
- Backpropagation Algorithm: Initial implementation of the backpropagation algorithm for training the network.
- Activation Functions: Includes simple activation functions like Sigmoid and ReLU.

Since the project is under development, it is not yet packaged for installation. To use the code, you will need to clone the repository and manually compile and run the code.

## Example
Here's a simple example of how to define and use the neural network:

```java

package driver;
import utility.Value;

public class Main {
    public static void main(String[] args) {
        Value x1 = new Value(2.0);
        Value x2 = new Value(0.0);
        Value w1 =new Value(-3.0);
        Value w2 =new Value(1.0);
        Value b = new Value(6.88137358);
        Value x1w1 = x1.mul(w1);
        Value x2w2 = x2.mul(w2);
        Value x1w1x2w2 = x1w1.add(x2w2);
        Value n = x1w1x2w2.add(b);
        Value o = n.tanh();
    }
}
```

## Testing

There are currently no automated tests for this project. Manual testing is being done as features are developed.

## Contributions

Contributions are welcome, but please note that the project is in an early development stage. If you'd like to contribute, feel free to fork the repository, create a new branch, and submit a pull request. Any suggestions or feedback are greatly appreciated!

## Contact
For any questions, suggestions, or feedback, please contact:

- Email: manasbisht2507@gmail.com
- GitHub: Banshee099
