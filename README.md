# customer-batch

PostgreSQL バッチインポートのサンプルプロジェクトです。

## Build

- Use Maven: `mvn clean package`

## Run

- Set database access環境変数:

```powershell
set DB_URL=jdbc:postgresql://localhost:5432/customerdb?reWriteBatchedInserts=true
set DB_USER=postgres
set DB_PASSWORD=yourpassword
```

- Run:

```powershell
mvn exec:java -Dexec.mainClass="com.company.batch.Main"
```

## Expected tables

- `customer_status_master(status_code, status_name)`
- `processed_customers(customer_id, customer_name, email, status_code, status_name)`

## Project structure

- `pom.xml`
- `src/main/java/com/company/batch/Main.java`
- `src/main/java/com/company/batch/service/CustomerImportService.java`
- `src/main/java/com/company/batch/dao/CustomerDao.java`
- `src/main/java/com/company/batch/dao/StatusMasterDao.java`
- `src/main/java/com/company/batch/model/Customer.java`
- `src/main/java/com/company/batch/model/StatusMaster.java`
- `src/main/java/com/company/batch/util/CsvReaderUtil.java`
- `src/main/java/com/company/batch/util/DbUtil.java`
- `sample/customer.csv`
