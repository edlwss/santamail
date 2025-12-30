CREATE SEQUENCE IF NOT EXISTS gift_catalog_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS assembly_order_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS assembly_order_item_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE gift_catalog (
                              id BIGINT PRIMARY KEY DEFAULT nextval('gift_catalog_id_seq'),
                              name_gift VARCHAR(255) NOT NULL,
                              category VARCHAR(100) NOT NULL,
                              price DOUBLE PRECISION NOT NULL,
                              stock_total INTEGER NOT NULL,
                              stock_reserved INTEGER NOT NULL
);

CREATE TABLE assembly_orders (
                                 id BIGINT PRIMARY KEY DEFAULT nextval('assembly_order_id_seq'),
                                 letter_id BIGINT NOT NULL,
                                 status VARCHAR(30) NOT NULL,
                                 CONSTRAINT assembly_order_status_check
                                     CHECK (status IN ('CREATED', 'IN_PROGRESS', 'DONE', 'CANCELLED'))
);

CREATE TABLE assembly_order_items (
                                      id BIGINT PRIMARY KEY DEFAULT nextval('assembly_order_item_id_seq'),
                                      order_id BIGINT NOT NULL,
                                      gift_id BIGINT NOT NULL,
                                      quantity INTEGER NOT NULL,

                                      CONSTRAINT fk_order_item_order
                                          FOREIGN KEY (order_id) REFERENCES assembly_orders(id) ON DELETE CASCADE,

                                      CONSTRAINT fk_order_item_gift
                                          FOREIGN KEY (gift_id) REFERENCES gift_catalog(id)
);
