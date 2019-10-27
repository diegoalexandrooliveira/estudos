 function [movies, users] = loadMoviesAndUsers()
   
n = 35;  % Total number of movies 
m = 12; % Total number of users
movies = cell(n, 1);
users = cell(m, 1);
   
fid = fopen('movies.txt');

% Store all movies in cell array movie


for i = 1:n
    movies(i) = fgets(fid);
end
fclose(fid);

fid = fopen('users.txt');
for i = 1:n
    users(i) = fgets(fid);
end
fclose(fid);
   
   
 endfunction
