-- ==========================================
-- 顧客データ取込バッチ
-- テーブル作成SQL
-- ==========================================

-- ステータスマスタ
CREATE TABLE IF NOT EXISTS customer_status_master (
status_code VARCHAR(10) PRIMARY KEY,
status_name VARCHAR(50) NOT NULL
);

COMMENT ON TABLE customer_status_master IS '顧客ステータスマスタ';
COMMENT ON COLUMN customer_status_master.status_code IS 'ステータスコード';
COMMENT ON COLUMN customer_status_master.status_name IS 'ステータス名';

-- 処理済顧客テーブル
CREATE TABLE IF NOT EXISTS processed_customers (
customer_id INT PRIMARY KEY,
customer_name VARCHAR(100) NOT NULL,
email VARCHAR(255) NOT NULL,
status_code VARCHAR(10) NOT NULL,
status_name VARCHAR(50) NOT NULL,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE processed_customers IS '処理済顧客テーブル';

COMMENT ON COLUMN processed_customers.customer_id IS '顧客ID';
COMMENT ON COLUMN processed_customers.customer_name IS '顧客名';
COMMENT ON COLUMN processed_customers.email IS 'メールアドレス';
COMMENT ON COLUMN processed_customers.status_code IS 'ステータスコード';
COMMENT ON COLUMN processed_customers.status_name IS 'ステータス名';
COMMENT ON COLUMN processed_customers.updated_at IS '更新日時';

-- ステータスマスタに初期データを挿入
INSERT INTO customer_status_master
(status_code, status_name)
VALUES
('ST01', '新規'),
('ST02', 'アクティブ'),
('ST03', '非アクティブ'),
('ST04', '退会');

COMMIT;