function plotData(X, y)
%PLOTDATA Plots the data points X and y into a new figure 
%   PLOTDATA(x,y) plots the data points with + for the positive examples
%   and o for the negative examples. X is assumed to be a Mx2 matrix.

% Create New Figure
figure; hold on;

% ====================== YOUR CODE HERE ======================
% Instructions: Plot the positive and negative examples on a
%               2D plot, using the option 'k+' for the positive
%               examples and 'ko' for the negative examples.
%


positivos = find(y==1);
negativos = find(y==0);

plot(X(positivos, 1), X(positivos,2), 'k', 'Marker', '+', 'LineStyle', 'none', 'MarkerSize', 7, 'MarkerEdgeColor', 'k');
plot(X(negativos, 1), X(negativos,2), 'r', 'Marker', 'o', 'LineStyle', 'none', 'MarkerSize', 7, 'MarkerEdgeColor', 'r');






% =========================================================================



hold off;

end
