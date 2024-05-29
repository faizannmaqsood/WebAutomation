# Use an official Maven image with OpenJDK 19
FROM maven:3.8.6-openjdk-19-slim

# Install dependencies
RUN apt-get update && apt-get install -y \
    xvfb \
    && rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . .

# Run tests
CMD ["sh", "-c", "xvfb-run -a mvn clean test"]
