 function [Y, R] = loadRates()
   
n = 35;  % Total number of movies 
m = 12; % Total number of users
Y = zeros(n,m);
R = zeros(n,m);
   
fid = fopen('rates.csv');

% Store all movies in cell array movie


for i = 1:n
    line = strsplit(fgets(fid),',');
    for j = 1:m
      Y(i,j) = str2double(line{j}(1));
    endfor    
end
fclose(fid);

fid = fopen('matrix.csv');
for i = 1:n
    line = strsplit(fgets(fid),',');
    for j = 1:m
      R(i,j) = str2double(line{j}(1));
    endfor    
end
fclose(fid);
   
   
 endfunction
