ALTER TABLE letters_status
    ADD COLUMN elf_id BIGINT;

ALTER TABLE letters_status
    ADD CONSTRAINT fk_status_elf
        FOREIGN KEY (elf_id)
            REFERENCES elfs(user_id)
            ON DELETE SET NULL;

ALTER TABLE letters_status
    ADD CONSTRAINT uq_status_letter UNIQUE (letter_id);

ALTER TABLE letters_status
DROP CONSTRAINT status_letter_check;

ALTER TABLE letters_status
    ADD CONSTRAINT status_letter_check
        CHECK (status_letter IN (
                                 'RECEIVED',
                                 'IN_PROGRESS',
                                 'DONE',
                                 'REJECTED'
            ));
