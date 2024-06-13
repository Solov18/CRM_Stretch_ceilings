package MyCeiling_.task_list.task_list.domain;

public enum TaskStatus {
    NOT_STARTED(){
        @Override
        public String toString() {
            return "Не выполняется";
        }
    },
    EXPIRED(){
        @Override
        public String toString() {
            return "Просрочено";
        }
    },
    IN_PROGRESS(){
        @Override
        public String toString() {
            return "Выполняется";
        }
    },
    COMPLETED(){
        @Override
        public String toString() {
            return "Выполнено";
        }
    };

}
