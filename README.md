# ETL-Template
![Codecov](https://img.shields.io/codecov/c/gh/akboyd88/etl-template?style=for-the-badge)![Codacy grade](https://img.shields.io/codacy/grade/a25c3c8880e04b82b69d32a1e0f2bb5f?style=for-the-badge)![GitHub branch checks state](https://img.shields.io/github/checks-status/akboyd88/etl-template/master?style=for-the-badge)![GitHub](https://img.shields.io/github/license/akboyd88/etl-template?style=for-the-badge)

## What is ETL?

ETL stands for extract, transform and load. This template provides boilerplate services, connections and simple examples to allow for quickly spinning up something to handle this kind of load. One such example is extracting power generation and consumption data from solar panels, weather data from co located weather stations, calculating streaming statistics such as standard deviation, and saving to a data store such as influxdb and visualizing the data in grafana.

## Contributing

If you're interested in contributing to this template please see the contribution guidelines (TODO: insert link).

## How to use

To use this template simply click the Use Template button in the upper right and start customizing as needed for desired use. This is provided AS IS under the MIT license with no warranty.

## Services included
  
- Kafka
  - Message Bus / Streams
- InfluxDB
  - Time Series Data Store
- PostgresSQL
  - Tradational Data Store
  - Quartz Data Store
- Cassandra
  - NoSQL Highly Scaleable Store
- Grafana
  - Charting/Visualization

## Libraries/Tools included
  
- Spring Boot
- Swagger/OpenAPI3
- Gradle
- Docker and Docker-Compose
