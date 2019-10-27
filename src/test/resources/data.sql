TRUNCATE TABLE Brewers;
TRUNCATE TABLE Categories;
TRUNCATE TABLE Beers;
Truncate Table BeerOrders;
TRUNCATE TABLE BeerOrderItems;

INSERT INTO Brewers VALUES (1,'TestBrewer','TestStreet',1000,'TestCity',10000);
INSERT INTO Categories VALUES (1,'TestCategory');
INSERT INTO Beers VALUES (1,'TestBeer',1,1,2.75,100,7,0,NULL);
insert into BeerOrders values (1, 'TestOrder');
insert into BeerOrderItems values (1, 2, 1, 1);
