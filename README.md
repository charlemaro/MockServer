# FireMock – Lightweight Mock Server

A small Java mock server built on [Spark](https://sparkjava.com/). It returns canned responses defined in JSON mapping files, so you can test against APIs (for example a payment gateway) that aren't available or that you don't want to call.

## How it works

- On startup, every JSON file in `src/main/resources/mappings/` is loaded.
- Incoming `POST` requests are matched on **URL**, **method** and optional **body content**.
- The matching mapping's status code and body are returned. If nothing matches, the server returns `400 {"error":"No matching record found"}`.

## Mapping format

```json
{
  "request":  { "method": "POST", "url": "/payment" },
  "response": {
    "status": 400,
    "body": "{ \"message\": \"Invalid card details\", \"status\": \"FAILED\" }"
  }
}
```

Bundled examples: `payment-success.json`, `payment-fail.json`.

## Run

**Prerequisites:** JDK 11+, Maven

```bash
cd eclipse-workspace/MockServer
mvn compile exec:java -Dexec.mainClass=FireMock.Main
```

The server starts on `http://localhost:9999`. Check it with `GET /`.

```bash
curl -X POST http://localhost:9999/payment -H "Content-Type: application/json" -d '{}'
```

## Tech Stack

Java · Spark Java · Maven

## Roadmap

- Support GET/PUT/DELETE
- Configurable port
- Response delays for failure simulation
