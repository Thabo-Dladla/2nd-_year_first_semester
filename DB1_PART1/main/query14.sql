SELECT officeCode AS officeCode,COUNT(employees.employeeNumber) AS numEmps FROM employees GROUP BY officeCode;
