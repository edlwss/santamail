-- SEQUENCES
CREATE SEQUENCE IF NOT EXISTS role_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS user_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS let_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS gift_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS status_id_seq START WITH 1 INCREMENT BY 1;

-- ROLE
CREATE TABLE role (
                      id BIGINT PRIMARY KEY DEFAULT nextval('role_id_seq'),
                      name_role VARCHAR(50) NOT NULL UNIQUE
);

-- USERS
CREATE TABLE users (
                       id BIGINT PRIMARY KEY DEFAULT nextval('user_id_seq'),
                       login VARCHAR(50) NOT NULL,
                       password VARCHAR(50) NOT NULL,
                       role_id BIGINT NOT NULL,
                       CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES role(id)
);

-- LETTERS (ОБЯЗАТЕЛЬНО ДО gifts / status)
CREATE TABLE letters (
                         id BIGINT PRIMARY KEY DEFAULT nextval('let_id_seq'),
                         age INTEGER NOT NULL,
                         city VARCHAR(100) NOT NULL,
                         text_letter TEXT NOT NULL,
                         last_name VARCHAR(100) NOT NULL,
                         first_name VARCHAR(100) NOT NULL,
                         patronymic VARCHAR(100)
);

-- GIFTS
CREATE TABLE gifts (
                       id BIGINT PRIMARY KEY DEFAULT nextval('gift_id_seq'),
                       name_gift VARCHAR(255) NOT NULL,
                       price DOUBLE PRECISION NOT NULL,
                       letter_id BIGINT NOT NULL,
                       CONSTRAINT fk_gifts_letter
                           FOREIGN KEY (letter_id) REFERENCES letters(id) ON DELETE CASCADE
);

-- LETTER STATUS
CREATE TABLE letters_status (
                                id BIGINT PRIMARY KEY DEFAULT nextval('status_id_seq'),
                                status_letter VARCHAR(30) NOT NULL,
                                letter_id BIGINT NOT NULL,
                                CONSTRAINT fk_status_letter
                                    FOREIGN KEY (letter_id) REFERENCES letters(id) ON DELETE CASCADE,
                                CONSTRAINT status_letter_check
                                    CHECK (status_letter IN ('получено', 'в обработке', 'исполнено', 'отклонено'))
);

-- ELFS
CREATE TABLE elfs (
                      user_id BIGINT PRIMARY KEY,
                      name_elf VARCHAR(100) NOT NULL,
                      CONSTRAINT fk_elf_user
                          FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
