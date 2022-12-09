function getGraph = generateFunc (k, c, bound)

xVals = linspace(-bound, bound)
yVals = (xVals-k).^2 + c

plot(xVals, yVals, 'r-')
grid on

title('Parabola')
xlabel('x')
ylabel('f(x)')
set(gca, 'FontSize', 15)

endfunction
