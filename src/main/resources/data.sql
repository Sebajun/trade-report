INSERT INTO BROKER (name) values ('BROKER A');
INSERT INTO BROKER (name) values ('BROKER B');
INSERT INTO PRODUCT (name) values ('AUDNZD FRD Exp14Jul2021');
INSERT INTO PRODUCT (name) values ('AUDUSD FRD Exp15Jul2021');
INSERT INTO PRODUCT (name) values ('EURUSD FRD Exp15Jul2021');

INSERT INTO TRADE (broker_id,trade_ref, product_id, trade_date, qty, buy_sell, price) VALUES
  (1,'T-FWD-1',1,'20200408',1000000,'B',1.067591),
  (1,'T-FWD-2',1,'20200408',8000000,'S',0.7518301),
  (1,'T-FWD-3',1,'20200408',25000000,'B',1.186073),
  (2,'T-FWD-4',2,'20200408',1000000,'B',1.067591),
  (2,'T-FWD-5',2,'20200408',8000000,'S',0.7518301),
  (2,'T-FWD-6',2,'20200408',25000000,'B',1.186073),
  (1,'T-FWD-7',3,'20200408',1000000,'B',1.067591),
  (1,'T-FWD-8',3,'20200408',8000000,'S',0.7518301),
  (1,'T-FWD-9',3,'20200408',25000000,'B',1.186073);

INSERT INTO PRINTING_PATTERN (product_id, broker_id, fields_to_print, headers, separator) VALUES (1,1, 'tradeRef, product.id, product.name, tradeDate, qty,buySell, price', 'tradeRef,productId,productName,tradeDate,qty,buySell,price', ',');
INSERT INTO PRINTING_PATTERN (product_id, broker_id, fields_to_print, headers, separator) VALUES (2,2, 'tradeRef, product.id, product.name, tradeDate, qty,buySell, price', 'tradeRef,productId,productName,tradeDate,qty,buySell,price', ',');
INSERT INTO PRINTING_PATTERN (product_id, broker_id, fields_to_print, headers, separator) VALUES (1,2, 'tradeRef, product.id, product.name, tradeDate, qty,buySell, price', 'tradeRef,productId,productName,tradeDate,qty,buySell,price', ',');
INSERT INTO PRINTING_PATTERN (product_id, broker_id, fields_to_print, headers, separator) VALUES (2,1, 'tradeRef, product.id, product.name, tradeDate, qty,buySell, price', 'tradeRef,productId,productName,tradeDate,qty,buySell,price', ',');