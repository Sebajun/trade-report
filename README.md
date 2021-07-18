# Trading reports

Sample project technical excercise.

There is no database needed, as the project is using an embedded one. To modify the database before starting the app, just modify the `data.sql` script

### Prerequisites
Intellij or Eclipse.

## Getting Started

This projet opens an API to be consumed to print trading reports through an HTTP GET request.

The only endpoint is ``localhost:8080/trade?product=xxx&broker=xxx&date=YYYYMMDD``

| Param | Description |
|---|---|
|`product`  | Product's name<br> |
|`broker`  | Broker's name
|`date`  | Date in <b>YYYYMMDD</b> format<br> |

## Screenshot
An example using postman :

![Postman screenshot](https://i.ibb.co/V2gNHrY/tg.png)

## Installing and Running

Simply import the project in one of the above IDE, and launch the spring boot app.

To generate the report make a get request with the 3 params :

```http://localhost:8080/trade?product=AUDNZD FRD Exp14Jul2021&broker=BROKER A&date=20200408```

<b> Important Note :</b> parameters with space are usually not encourged, but the url will be encoded so it is safe. Otherwise we could also make it work using body in a POST request for more solidity.

## Core Printing System

The core of the printing system can be found in the Printer class ``/src/main/java/com/example/demo/util/Printer.class``, more specifically the printTrades method.

To generates any report we need a list of trades and a printing pattern. The pattern and the trades are retrieve using spring boot layered services. Read the code for more in depth understanding.

```java

/**
    * Print a list of trade given a print pattern and a list of trades
    * 
    * @param pattern
    * @param trades
    * @return
    */
public static String printTrades(PrintingPattern pattern, Stream<Trade> trades) {

    if (pattern == null || trades == null) {
        return "";
    }

    List<String> fieldsToPrint = filterTradeList(pattern.getFieldsToPrint());

    StringBuilder sb = new StringBuilder();
    trades.forEach(trade -> {
        JSONObject tradeAsJson = new JSONObject(trade);
        for (String field : fieldsToPrint) {
            sb.append(getFieldFromJson(tradeAsJson, field));
            sb.append(pattern.getSeparator());
        }
        sb.deleteCharAt(sb.length() - 1);
        if (!sb.isEmpty()) {
            sb.append("\n");
        }
    });
    sb.insert(0, pattern.getHeaders() + "\n");
    return sb.toString();
}
```

This approach is efficient because we can later develop a simple front end to create new printing pattern to produce the desired printing report.

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
|`id`  | Product id |
|`name`  | Product name

### Broker
```
broker : {
    name, 
    id
}
```
| Field | Description |
|---|---|
|`id`  | Broker id |
|`name`  |  Broker name

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
|`id`  | Trade id<br> |
|`tradeRef`  | Trade reference
|`tradeDate`  | Trade date<br> |
|`qty`  | Quantity
|`buySell`  | Buy or sell<br> |
|`price`  | Trade price
|`product`  | Reference to <b>Product</b> object<br> |
|`broker`  | Reference to <b>Broker</b> object


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
|`fieldsToPrint`  | the list of fields from the <b>Trade</b> object you want to print, seperated by a comma
|`headers`  | the first line you want to print in your report, most often you will list the fields name<br> |
|`separator`  | the separator you want to use between every field of the trade
|`product`  | reference to <b>Product</b> object<br> |
|`broker`  | reference to <b>Broker</b> object

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
	fieldsToPrint : "tradeRef, product.id, product.name, tradeDate, qty, buySell, price",
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

## Junit

Unit test cases are provdided in the test folder.



## Built With

* [Maven](https://maven.apache.org/)
* [Spring](https://spring.io/) 
* [Hibernate](https://hibernate.org/)
* [H2](https://www.h2database.com/html/main.html)

## Authors

* **Camille** - *Initial work* - [Github Profile](https://github.com/Sebajun)

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE.md](LICENSE.md) file for details
