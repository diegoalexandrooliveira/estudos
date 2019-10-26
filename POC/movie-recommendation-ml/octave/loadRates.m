 function [Y, R] = loadRates()
   
n = 18;  % Total number of movies 
m = 4; % Total number of users
Y = zeros(n,m);
R = zeros(n,m);
   
fid = fopen('movies_rate_score.txt');

% Store all movies in cell array movie


for i = 1:n
    line = strsplit(fgets(fid),',');
    for j = 1:4
      o = j + 1;
      Y(i,j) = str2double(line{o}(1));
    endfor    
end
fclose(fid);

fid = fopen('movies_rate_matrix.txt');
for i = 1:n
    line = strsplit(fgets(fid),',');
    for j = 1:4
      o = j + 1;
      R(i,j) = str2double(line{o}(1));
    endfor    
end
fclose(fid);
   
   
 endfunction
