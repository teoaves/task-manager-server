package org.task.taskserver.dto;

public class SortField {
    public enum Field {
        TASK_NAME("i.name"),
        TASK_REF("i.ref"),
        TASK_LOT("i.lot"),
        TASK_MANUFACTURER("i.manufacturer"),
        TASK_PURCHASE_DATE("i.purchase_date"),
        TASK_SERIES_CODE("os.instrument_series_qr_code"),
        ID("u.user_id"),
        FIRST_NAME("u.first_name"),
        LAST_NAME("u.last_name"),
        USERNAME("u.username"),
        EMAIL("u.email"),
        STATUS("u.is_active"),
        LOCKED("u.is_not_locked");

        private String value;

        Field(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
