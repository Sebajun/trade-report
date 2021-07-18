# Trading reports

Sample project technical excercise.

There is no database needed, as the project is using an embedded one. To modify the database before starting the app, just modify the `data.sql` script

## Getting Started

The app allows you to print trading reports.

Assuming we have these 3 types of object  :

### Product
```
product : {
    id,
    name
}
```

| Field | Description |
|---|---|
|`id`  | product id |
|`name`  |  product name

### Broker
```
broker : {
    name, 
    id
}
```
| Field | Description |
|---|---|
|`id`  | broker id |
|`name`  |  broker name

### Trade
```
trade : {
    id,
    tradeRef,
	tradeDate,
	qty,
	buySell,
	price,
    product : {
        name,
        id
    }
    broker  : {
        name, 
        id
    }
}
```

| Field | Description |
|---|---|
|`id`  | trade id<br> |
|`tradeRef`  | trade reference
|`tradeDate`  | trade date<br> |
|`qty`  | quantity
|`buySell`  | buy or sell<br> |
|`price`  | trade price
|`product`  | reference to <b>product</b> object<br> |
|`broker`  | reference to <b>broker</b> object


it is possible to create an infinite number of printing pattern by defining a priting pattern object like this :
### Printing Pattern
```
printing_pattern : {
    id, 
	fieldsToPrint,
	headers,
	separator,
    product : {
        name,
        id
    }
	broker : {
        name, 
        id
    }
}
```

| Field | Description |
|---|---|
|`id`  | pattern id<br> |
|`fieldsToPrint`  | the list of fields from the trade object you want to print, seperated by a comma
|`headers`  | the first line you want to print in your report, most often you will list the fields name<br> |
|`separator`  | the separator you want to use between every field of the trade
|`product`  | reference to <b>product</b> object<br> |
|`broker`  | reference to <b>broker</b> object

### Example
Given this trade and printing pattern 

```
trade : {
    id: 1,
    tradeRef : "T-FWD-1",
	tradeDate: "20200408",
	qty: 1000000 ,
	buySell: "B" ,
	price: 1.067591 ,
    product : {
        id : 1,
        name : "AUDNZD FRD Exp14Jul2021"
    }
    broker : {
        id : 1,
        name : "Broker A"
    }
}
```
```
printing_pattern : {
    id: 1, 
	fieldsToPrint : "tradeRef, product.id, product.name, tradeDate, qty,buySell, price",
	headers : "tradeRef;productId;productName;tradeDate;qty;buySell;price",
	separator : ";" ,
    product : {
        id : 1,
        name : "AUDNZD FRD Exp14Jul2021"
    }
    broker : {
        id : 1,
        name : "Broker A"
    }
}
```

We would obtain the following report : 

```
tradeRef;productId;productName;tradeDate;qty;buySell;price
T-FWD-1;1;AUDNZD FRD Exp14Jul2021;20200408;1000000;B;1.067591
```

This approach is efficient because we can later develop a simple front end to create new printing pattern to produce the desired printing report.

### Prerequisites

Intellij or Eclipse.

### Installing

Simply import the project in one of the above IDE, and launch the spring boot app.

To generate the report make a get request with 3 params :
```http://localhost:8080/trade?product=AUDNZD FRD Exp14Jul2021&broker=BROKER A&date=20200408```

| Param | Description |
|---|---|
|`product`  | Product's name<br> |
|`broker`  | Broker's name
|`date`  | date in <b>DDMMYYYY</b> format<br> |

<b> Important Note :</b> parameters with space are usually not encourged, but the url will be encoded so it is safe. Otherwise we could also make it work using body in a POST request for more solidity.

## Built With

* [Maven](https://maven.apache.org/)
* [Spring](https://spring.io/) 
* [Hibernate](https://hibernate.org/)
* [H2](https://www.h2database.com/html/main.html)

## Authors

* **Camille** - *Initial work* - [Github Profile](https://github.com/Sebajun)

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE.md](LICENSE.md) file for details
