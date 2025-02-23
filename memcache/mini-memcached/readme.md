# 🚀 Mini-Memcached: A Simple Distributed Cache in Java

Mini-Memcached is a **lightweight, in-memory distributed cache** built in **Java**. It supports:
- **LRU-based eviction** for efficient memory management
- **Consistent Hashing** for distributed caching across multiple nodes
- **Client-Server Architecture** to separate cache storage from access
- **Interactive REPL CLI** for real-time cache operations

---

## Features
**In-Memory Cache** with **LRU Eviction**  
**Consistent Hashing** for distributed storage  
**Client-Server Model** for separation of concerns  
**Interactive CLI (REPL Mode)** for easy testing  
**Basic Cache Invalidation (DELETE support)**

---

## Installation & Setup

### **1. Clone the Repository**
```sh
git clone https://github.com/yourusername/mini-memcached.git
cd mini-memcached
```

### **2️. Build the Project Using Maven**
```sh
mvn clean package
```

---

## Running the Cache System

### **1️. Start the Cache Server**
```sh
java -cp target/mini-memcached.jar server.CacheServer
```
Server starts on **port 8080** and waits for client connections.

### **2️. Start the CLI (Interactive Mode)**
```sh
java -cp target/mini-memcached.jar main.Main
```

---

## Using the CLI (REPL Mode)
Once the CLI is running, you can interact with the cache using commands:

### **💩 Example Commands**
```sh
> PUT name Yudhisthir
✔ Stored: name

> GET name
🔍 Value: Yudhisthir

> DELETE name
🛢 Deleted: name

> GET name
❌ Key not found.

> EXIT
Exiting CLI...
```


---

##  Components Explanation

### **1️. LRU Cache (cache/LRUCache.java)**
- Stores key-value pairs in **RAM**
- Uses **Least Recently Used (LRU) eviction** to remove old entries
- Uses a **HashMap + Doubly Linked List** for O(1) operations

### **2️. Consistent Hashing (cache/ConsistentHashing.java)**
- Spreads data **evenly across cache nodes**
- Prevents **data loss** when adding/removing nodes
- Uses **Virtual Nodes** for load balancing

### **3️. Cache Node (cache/CacheNode.java)**
- Represents a **single cache instance**
- Stores key-value pairs

### **4️. Cache Server (server/CacheServer.java)**
- Listens for **client connections**
- Manages cache **storage and retrieval**
- Uses **multi-threading** for concurrent access

### **5️. Client Handler (server/ClientHandler.java)**
- Handles **incoming client requests**
- Parses **commands (GET, PUT, DELETE)**
- Communicates with **cache server**

### **6️. Client API (client/CacheClient.java)**
- Connects to **cache server**
- Provides **GET, PUT, DELETE** functions
- Handles **network errors gracefully**

### **7️. Interactive CLI (main/Main.java)**
- Starts **command-line interface**
- Allows users to interactively **store, fetch, and delete** keys
- Uses a **Scanner** to read input

---

## Example Scenarios

### **Scenario 1: Storing Data**
```sh
> PUT city Indraprastha
✔ Stored: city

> PUT country India
✔ Stored: country
```

### **Scenario 2: Retrieving Data**
```sh
> GET city
🔍 Value: Indraprastha

> GET country
🔍 Value: India
```

### **Scenario 3: Deleting Data**
```sh
> DELETE city
🛢 Deleted: city

> GET city
❌ Key not found.
```

### **Scenario 4: Exiting**
```sh
> EXIT
Exiting CLI...
```

---

## 🔧 Future Enhancements
- [ ] **Persistent Storage (Disk-based Caching)**
- [ ] **Multi-threaded Client Support**
- [ ] **Cache Expiration (TTL Support)**
- [ ] **Cluster Mode (Multiple Cache Servers)**

---

## 🔧 Troubleshooting

### **1️⃣ Getting `Connection Refused`?**
✅ **Solution:** Make sure the **cache server** is running before starting the client.
```sh
java -cp target/mini-memcached.jar server.CacheServer
```

### **2️⃣ Getting `ClassNotFoundException`?**
✅ **Solution:** Run the **Maven build command** first.
```sh
mvn clean package
```

---
