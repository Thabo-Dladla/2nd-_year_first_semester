 SELECT orders.orderNumber, orders.comments,customers.customerName FROM orders JOIN customers ON orders.customerNumber = customers.customerNumber where status = 'Disputed';
