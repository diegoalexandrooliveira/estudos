function J = computeCost(X, y, theta)
%COMPUTECOST Compute cost for linear regression
%   J = COMPUTECOST(X, y, theta) computes the cost of using theta as the
%   parameter for linear regression to fit the data points in X and y

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta
%               You should set J to the cost.

%somatoria = 0;
%for i=1:m
    %hipotese = theta(1) + (theta(2)*X(i, 2));
    %parcial = hipotese-y(i);
    %parcial = parcial .^2;
%    somatoria = somatoria + parcial;
%end;
%J = 1/(2*m) * sum(somatoria);

J = 1/(2*m)*sum(((X(:,2) * theta(2)) + theta(1) - y).^2); % vetorização?


% =========================================================================

end
