# customer-batch

CSVファイル（10,000件）を読み込み、customer_status_masterを参照してステータス名を補完し、processed_customersへ一括登録するバッチプログラムです。

## 動作環境

- Windows 11
- Java 21
- Maven 3.9 以上
- PostgreSQL 17

## 事前準備

- PostgreSQL 17 をインストール（Stack Builder不要）
- customerdb を作成
- create_table.sql を実行
- master_data.sql を実行
- application.properties の接続情報を環境に合わせて設定
  接続情報例
  db.url=jdbc:postgresql://localhost:5432/customerdb
  db.user=postgres
  db.password=********

## 入力ファイル

CSVファイルを以下に配置します。
sample/customer.csv

## ビルド

mvn clean package

## 実行

java -jar target/customer-batch-1.0.0-SNAPSHOT.jar

## 実行結果

- 処理対象          ：10,000件
- 登録先テーブル    ：processed_customers
- ステータス補完元  ：customer_status_master
- インポート件数    ： 10,000 件
- 実行時間          ： 0.820 秒
- DB検証            ： processed_customers テーブルに 10,000 件確認


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
