function getSaltedGraph = generateSaltedFunc (k, c, bound, saltAmount)

xVals = linspace(-bound, bound)
yVals = (xVals-k).^2 + c

saltedVals = 0

for i = 1:100

  if(mod(i, 2) == 0)
    saltAmount = -saltAmount
  endif

   saltedVals(i) = yVals(i) + saltAmount

endfor


plot(xVals, saltedVals, 'r-')
grid on

title('Salted Parabola')
xlabel('x')
ylabel('f(x)')
set(gca, 'FontSize', 15)

endfunction
