SELECT officeCode AS officeCode,COUNT(employees.employeeNumber) AS numReps FROM employees WHERE jobTitle='Sales Rep' GROUP BY officeCode;
