ALTER TABLE letters_status
DROP CONSTRAINT status_letter_check;

ALTER TABLE letters_status
    ADD CONSTRAINT status_letter_check
        CHECK (status_letter IN (
                                 'RECEIVED',
                                 'IN_PROGRESS',
                                 'DONE',
                                 'REJECTED',
                                 'WAITING_APPROVAL'
            ));
