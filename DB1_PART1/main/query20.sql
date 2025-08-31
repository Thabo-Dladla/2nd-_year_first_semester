SELECT 'YES' AS anyProblems FROM orderdetails WHERE orderdetails.priceEach = 0
UNION SELECT 'YES' AS anyProblems FROM products WHERE products.buyPrice = 0;

