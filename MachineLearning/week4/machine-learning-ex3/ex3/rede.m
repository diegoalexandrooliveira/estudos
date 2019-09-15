function g = rede()
  
  
  pesos1 = ones(3,3);
  a1 = [1;0;0];
  % vetorizado
  a2 = sigmoid(pesos1*a1);
  
  

% sem vectorizacao

##a2 = zeros(3,1);
##for i=1:3
##   for j=1:3
##     a2(i) = a2(i) + a1(j)*pesos1(i,j);
##   endfor
##   a2(i) = sigmoid(a2(i));
##endfor
  
##  pesos2 = ones(4,1);
##  
##  a3 = sigmoid(pesos2'*a2);
##  
##  display(a3);
a2 = [1;a2];
  g = a2;

end
