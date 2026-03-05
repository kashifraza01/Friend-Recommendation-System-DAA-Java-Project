# 📌 Graph-Based Friend Recommendation System (Java Swing)

A desktop-based **Friend Recommendation System** built using **Java Swing** and **Graph Data Structures**.
This project simulates how social media platforms recommend friends using **mutual friend analysis**.

---

## 🚀 Features

* 📥 User inputs friendship relationships
* 🧠 Graph-based internal data structure (Adjacency List using HashMap & HashSet)
* 🤝 Finds mutual friends
* 📊 Recommends users sorted by highest mutual connections
* 🖥️ Simple and interactive GUI built with Java Swing

---

## 🛠️ Technologies Used

* **Java**
* **Java Swing (GUI)**
* **HashMap**
* **HashSet**
* **ArrayList**
* **Graph Theory (Undirected Graph)**

---

## 🧠 How It Works

1. Users enter friendship pairs in this format:

   ```
   Ali Ahmed
   Ahmed Sara
   Sara Bilal
   ```

2. The system:

   * Builds an **undirected graph**
   * Stores relationships using an adjacency list
   * Finds friends of friends
   * Excludes:

     * The current user
     * Existing friends

3. Recommendations are ranked based on:

   > Number of Mutual Friends

---

## 📷 User Interface Overview

### Input Panel

* Enter friendships in format:

  ```
  user1 user2
  ```

### Output Panel

* Displays recommended users with:

  ```
  Username -> mutual friends: X
  ```

---

## 📂 Project Structure

```
kashif/
 └── Kashif.java
```

Main Class:

```java
public class Kashif extends JFrame
```

Core Methods:

* `graphbanaya()` → Builds the graph
* `processshurukaro()` → Generates recommendations
* `mutualfriendcount()` → Counts mutual friends

---

## ▶️ How to Run

1. Open project in any Java IDE (IntelliJ, Eclipse, NetBeans)
2. Compile and run `Kashif.java`
3. Enter friendship data
4. Enter username
5. Click **Recommend**

---

## 📚 Concepts Implemented

* Graph Data Structure (Adjacency List)
* Undirected Graph
* Mutual Friend Algorithm
* Basic Sorting (Manual Comparison Sort)
* Event Handling in Swing
* GUI Layout Management

---

## 💡 Learning Outcome

This project demonstrates:

* Practical implementation of graph theory
* Real-world use case of mutual friend recommendation
* GUI application development in Java
* Data structure optimization using HashMap & HashSet

---

## 🔮 Future Improvements

* Add weighted recommendation system
* Replace manual sorting with Collections.sort()
* Add friend suggestion limit (Top 5)
* Add remove friend feature
* Store data in database (MySQL)
* Convert to web-based application (Spring Boot)

---

## 👨‍💻 Author

**Kashif Raza**
