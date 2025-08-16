# Public Trades Feed API 📰

A Spring Boot REST API that fetches a list of the most recent public trades for a given cryptocurrency market from the public Quidax API. This service is suitable for displaying a live feed of market activity.

## Features

- Fetches a list of recent historical trades for a specific market pair (e.g., `btcngn`).
- Accurately models and parses the nested JSON response from the Quidax trades endpoint.
- Built with a clean, layered architecture (Controller, Service, DTO).

## Technologies Used

- Java 17
- Spring Boot 3
- Jackson Annotations (`@JsonProperty`)
- Maven
- Lombok

## API Endpoint

| Method | Endpoint                      | Description                                                |
|--------|-------------------------------|------------------------------------------------------------|
| GET    | `/api/v1/feeds/{market}/trades` | Get a list of the most recent trades for a specific market. |

## Export to Sheets

### Example Usage:

A GET request to `http://localhost:8080/api/v1/feeds/btcngn/trades` will return a JSON array of recent trades:

```json
[
    {
        "type": "sell",
        "price": "180321000.0",
        "timestamp": 1662552000,
        "tradeId": 12345,
        "baseVolume": "0.001",
        "quoteVolume": "180321.0"
    },
    {
        "type": "buy",
        "price": "180321001.0",
        "timestamp": 1662552005,
        "tradeId": 12346,
        "baseVolume": "0.002",
        "quoteVolume": "360642.0"
    }
]
```

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

- JDK (Java Development Kit) 17 or later
- Maven

### Installation & Running the App

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/public-trades-feed.git
   ```

2. Navigate to the project directory:

   ```bash
   cd public-trades-feed
   ```

3. Run the application using the Maven wrapper:

   On macOS/Linux:

   ```bash
   ./mvnw spring-boot:run
   ```

   On Windows:

   ```bash
   mvnw.cmd spring-boot:run
   ```

The application will start on `http://localhost:8080`.