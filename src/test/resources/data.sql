TRUNCATE TABLE Brewers;
TRUNCATE TABLE Categories;
TRUNCATE TABLE Beers;
TRUNCATE TABLE BeerOrders;
TRUNCATE TABLE BeerOrderItems;
​
INSERT INTO Brewers VALUES (1,'TestBrewer','TestStreet',1000,'TestCity',10000);
INSERT INTO Categories VALUES (1,'TestCategory');
INSERT INTO Beers VALUES (1,'TestBeer',1,1,2.75,100,7,0,NULL);
Insert Into BeerOrders Values (1, 'TestOrder');
Insert Into BeerOrderItems Values (1, 1, 7, 4);
