function [C, sigma] = dataset3Params(X, y, Xval, yval)
%DATASET3PARAMS returns your choice of C and sigma for Part 3 of the exercise
%where you select the optimal (C, sigma) learning parameters to use for SVM
%with RBF kernel
%   [C, sigma] = DATASET3PARAMS(X, y, Xval, yval) returns your choice of C and 
%   sigma. You should complete this function to return the optimal C and 
%   sigma based on a cross-validation set.
%

% You need to return the following variables correctly.
C = 0;
sigma = 0;

% ====================== YOUR CODE HERE ======================
% Instructions: Fill in this function to return the optimal C and sigma
%               learning parameters found using the cross validation set.
%               You can use svmPredict to predict the labels on the cross
%               validation set. For example, 
%                   predictions = svmPredict(model, Xval);
%               will return the predictions on the cross validation set.
%
%  Note: You can compute the prediction error using 
%        mean(double(predictions ~= yval))
%
menor_erro = 10000000^2;

C_valores = [0.01;0.03;0.1;0.3;1;3;10;30];
sigma_valores = [0.01;0.03;0.1;0.3;1;3;10;30];

for i=1:length(C_valores)
  C_test = C_valores(i);  
  for j=1:length(sigma_valores)
    sigma_test = sigma_valores(j);
    model= svmTrain(X, y, C_test, @(x1, x2) gaussianKernel(x1, x2, sigma_test));    
    predictions = svmPredict(model, Xval);
    erro = mean(double(predictions ~= yval));
    if erro < menor_erro
      menor_erro = erro;
      C = C_test;
      sigma = sigma_test;
    endif
  endfor
endfor



% =========================================================================

end
