function getSmoothGraph = generateSmoothFunc (k, c, bound, saltAmount)

xVals = linspace(-bound, bound)
yVals = (xVals-k).^2 + c

saltedVals = 0

for i = 1:100

  if(mod(i, 2) == 0)
    saltAmount = -saltAmount
  endif

   saltedVals(i) = yVals(i) + saltAmount

endfor

smoothVals = 0

for i = 1:99

   leftVal = saltedVals(i)
   rightVal = saltedVals(i+1)

   smoothVals(i) = (leftVal+rightVal) / 2

endfor

smoothVals(100) = (leftVal+rightVal) / 2

plot(xVals, smoothVals, 'r-')
grid on

title('Smooth Parabola')
xlabel('x')
ylabel('f(x)')
set(gca, 'FontSize', 15)

endfunction
