SELECT customer_id, MAX(purchase_amount) AS "Highest Purchase Amount" FROM orders GROUP BY customer_id;

SELECT salesman_id, order_date, MAX(purchase_amount) AS "Highest Purchase Amount on 2012-08-17" FROM orders  
WHERE order_date=To_DATE('2012/08/17', 'YYYY/MM/DD') GROUP BY salesman_id, order_date;

SELECT customer_id, order_date, MAX(purchase_amount)  
    AS "Highest Purchase Amount within list [2030, 3450, 5760, 6000]" FROM orders 
GROUP BY customer_id, order_date 
HAVING MAX(purchase_amount) IN(2030, 3450, 5760, 6000);

