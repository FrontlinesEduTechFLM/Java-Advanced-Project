
<hr />

##  Prerequisites

Before running the project:

* **Node.js 18+**
* **npm or pnpm**
* **Java 17**
* **MySQL Server** installed and running

<hr />

## 🚀 Installation & Setup

### 1. **Clone the repository**

```bash
git clone https://github.com/Paul-110/Java-SaaS-POS-System.git
cd Java-SaaS-POS-System
```

---

## ⚛️ Frontend Setup (React + Vite)

### 2. Install dependencies

```bash
npm install
```

### 3. Start development server

```bash
npm run dev
```

The app will start at:

```
http://localhost:5173
```

---

## 🖥️ Backend Setup (Spring Boot)

### 4. Configure MySQL

Update your `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pospro
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
jwt.secret=your_jwt_secret
```

### 5. Run the backend

```bash
mvn spring-boot:run
```

Backend starts at:

```
http://localhost:8080
```
