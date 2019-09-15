function [J grad] = nnCostFunction(nn_params, ...
                                   input_layer_size, ...
                                   hidden_layer_size, ...
                                   num_labels, ...
                                   X, y, lambda)
%NNCOSTFUNCTION Implements the neural network cost function for a two layer
%neural network which performs classification
%   [J grad] = NNCOSTFUNCTON(nn_params, hidden_layer_size, num_labels, ...
%   X, y, lambda) computes the cost and gradient of the neural network. The
%   parameters for the neural network are "unrolled" into the vector
%   nn_params and need to be converted back into the weight matrices. 
% 
%   The returned parameter grad should be a "unrolled" vector of the
%   partial derivatives of the neural network.
%

% Reshape nn_params back into the parameters Theta1 and Theta2, the weight matrices
% for our 2 layer neural network
Theta1 = reshape(nn_params(1:hidden_layer_size * (input_layer_size + 1)), ...
                 hidden_layer_size, (input_layer_size + 1));

Theta2 = reshape(nn_params((1 + (hidden_layer_size * (input_layer_size + 1))):end), ...
                 num_labels, (hidden_layer_size + 1));

% Setup some useful variables
m = size(X, 1);
         
% You need to return the following variables correctly 
J = 0;
Theta1_grad = zeros(size(Theta1));
Theta2_grad = zeros(size(Theta2));

% ====================== YOUR CODE HERE ======================
% Instructions: You should complete the code by working through the
%               following parts.
%
% Part 1: Feedforward the neural network and return the cost in the
%         variable J. After implementing Part 1, you can verify that your
%         cost function computation is correct by verifying the cost
%         computed in ex4.m

%adicionando bias
X = [ones(m, 1) X];

% calcular hidden_layer
hidden_layer1 = sigmoid(X*Theta1');

%adicionando bias
hidden_layer1 = [ones(m, 1) hidden_layer1];


% calcular output_layer
output_layer = sigmoid(hidden_layer1*Theta2');


% organizando o Y
y_adaptado = zeros(m,num_labels);
for i=1:m
y_adaptado(i,y(i))=1;
endfor


J = sum(sum(-y_adaptado' .* log(output_layer') - (1 - y_adaptado') .* log(1 - output_layer'))) / m;

t1 = Theta1(:,2:size(Theta1,2));
t2 = Theta2(:,2:size(Theta2,2));

% Regularization
Reg = lambda  * (sum( sum ( t1.^ 2 )) + sum( sum ( t2.^ 2 ))) / (2*m);

% Regularized cost function
J = J + Reg;


%
% Part 2: Implement the backpropagation algorithm to compute the gradients
%         Theta1_grad and Theta2_grad. You should return the partial derivatives of
%         the cost function with respect to Theta1 and Theta2 in Theta1_grad and
%         Theta2_grad, respectively. After implementing Part 2, you can check
%         that your implementation is correct by running checkNNGradients
%
%         Note: The vector y passed into the function is a vector of labels
%               containing values from 1..K. You need to map this vector into a 
%               binary vector of 1's and 0's to be used with the neural network
%               cost function.
%
%         Hint: We recommend implementing backpropagation using a for-loop
%               over the training examples if you are implementing it for the 
%               first time.

##for t=1:m
  %parte 1
##  a1 = X(t,:);  %1x401
  a1 = X;  %5000x401

% calcular hidden_layer
  z2 = a1*Theta1';
##  a2 = sigmoid(z2);%1X25
a2 = sigmoid(z2);%5000X25

%adicionando bias
##  a2 = [1 a2];%1X26
  a2 = [ones(m,1) a2];%5000X26


% calcular output_layer
  z3 = a2*Theta2';
##  a3 = sigmoid(z3);%1X10
  a3 = sigmoid(z3);%5000X10
  
  %parte 2
##  delta3 = a3 - y_adaptado(t,:); %1X10
  delta3 = a3 .- y_adaptado; %5000X10
  
  %parte 3
##  z2 = [1 z2]; %1X26
  z2 = [ones(m,1) z2]; %5000X26
  delta2 = (delta3 * Theta2) .* sigmoidGradient(z2); %5000X26
  
  %parte 4
##  delta2 = delta2(2:end);
    delta2 = delta2(:, 2:end); %5000X25
##  Theta2_grad = Theta2_grad + delta3' * a2;
##  Theta1_grad = Theta1_grad + delta2' * a1;

  Theta2_grad = delta3' * a2;
  Theta1_grad = delta2' * a1;
  
##endfor
Theta1_grad = Theta1_grad / m;

Theta2_grad = Theta2_grad / m;
%
% Part 3: Implement regularization with the cost function and gradients.
%
%         Hint: You can implement this around the code for
%               backpropagation. That is, you can compute the gradients for
%               the regularization separately and then add them to Theta1_grad
%               and Theta2_grad from Part 2.
%

Theta1_grad(:, 2:end) = Theta1_grad(:, 2:end) + ((lambda/m)* Theta1(:, 2:end));

Theta2_grad(:, 2:end) = Theta2_grad(:, 2:end) + ((lambda/m)* Theta2(:, 2:end));














% -------------------------------------------------------------

% =========================================================================

% Unroll gradients
grad = [Theta1_grad(:) ; Theta2_grad(:)];


end
