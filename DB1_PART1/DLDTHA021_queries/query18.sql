SELECT (SELECT COUNT(country) FROM offices WHERE country = 'USA')*100/(SELECT COUNT(country) FROM offices) AS percentUSA;
