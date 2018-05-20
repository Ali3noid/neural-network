# neural-network
Application that allows user to upload a file to learn the network and
then predicate a result for other input data set. 

It is a very simple implementation for neural network. Network will always contain two layers: input and output, with no hidden layers at all.
<p align="center">
  <img src="https://raw.githubusercontent.com/Ali3noid/neural-network/images/example-network.png">
</p>

Structure of data file is described below.
<p align="center">
  <img src="https://raw.githubusercontent.com/Ali3noid/neural-network/images/example-data-file.png">
</p>

Meaning of each parameter (from right to left):
1. number of input neurons
2. number of output neurons
3. number of rows with train data
4. number of rows with data to predict
5. number of training iterations

Thanks to two-layer structure, those network are able only predict, with high success rate, sets that have linearly separable patterns. Other words, problems like XOR recognition can't be solve with this approach. 