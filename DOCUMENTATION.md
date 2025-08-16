# Project Documentation: Public Trades Feed

## Project Overview
The Public Trades Feed is a Spring Boot microservice that provides a list of the most recent public trades for a specific cryptocurrency market. The service fetches this data from the Quidax API and presents it in a clean JSON format.

This project reinforces the architectural pattern of fetching a list of data from a nested API response and serving it through a simple, dynamic endpoint.

```
GET /api/v1/feeds/{market}/trades
```
Fetches a list of recent trades for a given market pair (e.g., btcngn).

## Core Dependencies
- **spring-boot-starter-web**: Provides all necessary components for building REST APIs, including an embedded Tomcat server and the Jackson JSON library.
- **lombok**: A utility library used to reduce boilerplate code like getters and setters.

## Project Structure and Components
The project uses a standard layered architecture to ensure a clear separation of concerns.

```
/dto/
 ├── Trade.java           (Models a single trade record)
 └── TradesResponse.java  (Wrapper for the API's top-level response)
/service/
 └── TradesService.java   (Business Logic and API communication)
/controller/
 └── TradesController.java (API Endpoint Layer)
```

## Detailed Class Explanations

### The DTO Layer (The Data Models)
The DTOs (Data Transfer Objects) are designed to exactly match the JSON structure from the Quidax API.

📄 **Trade.java**

**Purpose**: Represents the innermost object containing the details of a single historical trade.

**Code**:

```java
@Data
public class Trade {
    private String type;
    private String price;
    private long timestamp;
    @JsonProperty("trade_id")
    private int tradeId;
    @JsonProperty("base_volume")
    private String baseVolume;
    @JsonProperty("quote_volume")
    private String quoteVolume;
}
```

📄 **TradesResponse.java**

**Purpose**: Represents the top-level wrapper object for the `/trades/{market}` endpoint response. It contains the status and the list of trades.

**Code**:

```java
@Data
public class TradesResponse {
    private String status;
    private List<Trade> data;
}
```

### service/TradesService.java - The Business Logic
This class is responsible for calling the Quidax API and "unwrapping" the nested JSON to extract the list of trades.

#### getRecentTrades(String market) Method:
- **Action**: Calls the `https://api.quidax.com/api/v1/trades/{market}` endpoint.
- **Logic**: It tells RestTemplate to expect a TradesResponse object. It then drills down (`response.getData()`) to extract and return the final `List<Trade>`.

**Code**:

```java
@Service
public class TradesService {

    private final RestTemplate restTemplate;

    public TradesService() {
        this.restTemplate = new RestTemplate();
    }

    public List<Trade> getRecentTrades(String market) {
        String url = "https://api.quidax.com/api/v1/trades/" + market;

        TradesResponse response = restTemplate.getForObject(url, TradesResponse.class);

        if (response != null && "success".equals(response.getStatus())) {
            return response.getData();
        }

        return Collections.emptyList();
    }
}
```

### controller/TradesController.java - The API Layer
This class handles incoming HTTP requests and delegates the work to the TradesService.

#### getTradesForMarket(@PathVariable String market) Method:
- **Action**: Handles GET requests to `/api/v1/feeds/{market}/trades`.
- **Logic:** It uses the `@PathVariable` annotation to capture the market pair from the URL and passes it to the TradesService. It returns the list of trades provided by the service, which Spring Boot automatically converts to a JSON array.

**Code:**

```java
@RestController
@RequestMapping("/api/v1/feeds")
public class TradesController {

    private final TradesService tradesService;

    public TradesController(TradesService tradesService) {
        this.tradesService = tradesService;
    }

    @GetMapping("/{market}/trades")
    public List<Trade> getTradesForMarket(@PathVariable String market) {
        return tradesService.getRecentTrades(market);
    }
}
```