create table user (
	id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100),
    phone VARCHAR(20),
    password VARCHAR(255),
    image_url VARCHAR(255),
    name VARCHAR(100),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
)