%% ================== Part 7: Learning Movie Ratings ====================
%  Now, you will train the collaborative filtering model on a movie rating 
%  dataset of 1682 movies and 943 users
%

fprintf('\nTraining collaborative filtering...\n');

%  Load data
%load('ex8_movies.mat');

% [Y, R] = loadRates();

Ynorm = [1 1 2 2.5 4.5 3;2 2 4 4 5 0];
R = [1 1 1 1 1 1;1 1 1 1 1 0];

%  Y is a 18x4 matrix, containing ratings (1-5) of 18 movies by 
%  4 users
%
%  R is a 18x4 matrix, where R(i,j) = 1 if and only if user j gave a
%  rating to movie i

%  Normalize Ratings
%[Ynorm, Ymean] = normalizeRatings(Y, R);

%  Useful Values
num_users = size(Ynorm, 2);
num_movies = size(Ynorm, 1);
num_features = 2;

% Set Initial Parameters (Theta, X)
X = randn(num_movies, num_features);
Theta = randn(num_users, num_features);

initial_parameters = [X(:); Theta(:)];

% Set options for fmincg
options = optimset('GradObj', 'on', 'MaxIter', 100);

% Set Regularization
lambda = 0.001;
theta = fmincg (@(t)(cofiCostFunc(t, Ynorm, R, num_users, num_movies, ...
                                num_features, lambda)), ...
                initial_parameters, options);

% Unfold the returned theta back into U and W
X = reshape(theta(1:num_movies*num_features), num_movies, num_features);
Theta = reshape(theta(num_movies*num_features+1:end), ...
                num_users, num_features);

fprintf('Recommender system learning completed.\n');


%% ================== Part 8: Recommendation for you ====================
%  After training the model, you can now make recommendations by computing
%  the predictions matrix.
%

[movies, users] = loadMoviesAndUsers();

p = X * Theta';
%my_predictions = p(:,1) + Ymean;

##fprintf('Predicting rating for user 1\n');
##display(p(:,1) + Ymean);
##fprintf('Predicting rating for user 2\n');
##display(p(:,2) + Ymean);
for j=1:size(users)
  [r, ix] = sort(p(:,j), 'descend');
   fprintf('\nTop recommendations for %s', users{j});
    for i=1:10
      m = ix(i);
      fprintf('%s: Theta: %.1f Median: %.1f \n',strtrim(movies{m}), p(m,j), Ymean(m));
end
endfor


##pause();
##%movieList = loadMovieList();
##
##[r, ix] = sort(my_predictions, 'descend');
##fprintf('\nTop recommendations for you:\n');
##for i=1:10
##    j = ix(i);
##    fprintf('Predicting rating %.1f for movie %s\n', my_predictions(j), ...
##            movieList{j});
##end
##
##fprintf('\n\nOriginal ratings provided:\n');
##for i = 1:length(my_ratings)
##    if my_ratings(i) > 0 
##        fprintf('Rated %d for %s\n', my_ratings(i), ...
##                 movieList{i});
##    end
##end