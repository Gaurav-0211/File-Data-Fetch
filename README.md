
# 📁 Spring Boot Applicaton for Excel Upload & Download Application, also save the entire data as it is in SQL based format to database.

This Spring Boot application allows users to:

- ✅ Upload Excel files (`.xlsx`)
- 📥 Fetch and parse data from Excel files
- 💾 Save the extracted data to a database
- 📤 Store the file on the server
- 🔗 Enable users to download uploaded files

---

## 🚀 Features

- Upload Excel file (`.xlsx`) via REST API
- Parse rows and convert them into Java objects
- Save parsed data into the database
- Store the uploaded file in the file system
- Provide file download API

---

## 📦 Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- PostgreSQL
- Apache POI (for Excel operations)
- Maven
- Swagger
- Lombok
- Spring Security

---

## 📂 Project Structure

```
src/
├── controller/
│   └── FileController.java
├── service/
│   └── FileService.java
├── helper/
│   └── ExcelHelper.java
├── model/
│   └── Product.java
├── repository/
│   └── ProductRepository.java
└── ...
```

---

## 📤 How to Upload Excel File

**Endpoint:**

```
POST /api/excel/upload
```

**Form-Data Body:**

- `file`: (type = `file`) Upload your `.xlsx` file here.

---

## 📥 How to Download Uploaded File

**Endpoint:**

```
GET /api/excel/download/{filename}
```

**Response:** The file will be downloaded from the server location.

---

## 🔍 How to Fetch Data from Database

**Endpoint:**

```
GET /api/excel/products
```

**Response:** JSON array of all records saved from Excel.

---

## ✅ Excel Format Required

Your Excel file must follow this header format in the first row:

| productId | productName | productDesc | productPrice |
|-----------|-------------|--------------|--------------|

---

## 🛠️ How to Run Locally

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-repo/springboot-excel-upload.git
   cd springboot-excel-upload
   ```

2. **Configure DB (optional)**

   - For H2 (default): No setup needed
   - For PostgreSQL: Edit `application.properties`

3. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

4. **Access API Docs :**

   ```
   http://localhost:8080/swagger-ui/index.html
   ```

---

## 📎 Sample cURL Commands

### Upload Excel File

```bash
curl -X POST http://localhost:8080/api/excel/upload \
  -H "Content-Type: multipart/form-data" \
  -F "file=@/path/to/products.xlsx"
```

### Download Uploaded File

```bash
curl -O http://localhost:8080/api/excel/download/products.xlsx
```

---

## 💡 Tips

- Make sure your Excel file starts with a valid header row.
- Keep uploaded files secure on the server.
- You can extend this project to support CSV, PDF, etc.

---

## 📬 Contact

For improvements or bug reports, please fell free to suggest what additional I can add and make this application more robust.
